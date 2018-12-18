package io.damo.sms.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import io.damo.common.exception.ServerException;
import io.damo.common.utils.StringUtil;
import io.damo.sms.constant.SmsConstants;
import io.damo.sms.request.SmsSendRequest;
import io.damo.sms.response.SmsSendResponse;
import io.damo.sms.utils.ChuangLanSmsUtil;
import io.damo.sms.utils.SmsConstant;
import io.damo.common.utils.IdUtil;
import io.damo.common.utils.RandomUtil;
import io.damo.common.validator.Assert;
import io.damo.sms.dao.SmsCaptchaDao;
import io.damo.sms.entity.SmsCaptchaEntity;
import io.damo.sms.service.SmsCaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("smsCaptchaService")
public class SmsCaptchaServiceImpl extends ServiceImpl<SmsCaptchaDao, SmsCaptchaEntity> implements SmsCaptchaService {
    private static final Logger logger = LoggerFactory.getLogger(SmsCaptchaServiceImpl.class);

    private static final String SUCCESS = "0";
    /** 验证码占位符 */
    private static final String PARAM = "%@%";
    /** 短信验证码长度 */
    private static final int SMS_CAPTCHA_LENGTH = 6;

    @Autowired
    private SmsCaptchaDao smsCaptchaDao;
    
    /**
     * 发送短信验证码
     * @param areaCode 国际区号
     * @param mobile 手机号
     * @param type 短信类型
     */
    public void sendSmsCaptcha(String areaCode, String mobile, String type) {
        Assert.assertMobile(mobile); //验证手机号格式

        // 1.检查是否连续发送短信验证码
        Assert.assertCase(!isAllowCaptcha(mobile), "一分钟内只允许发送一条短信");

        // 2.生成新的短信验证码
        String captcha = RandomUtil.getCode(SMS_CAPTCHA_LENGTH);
        SmsCaptchaEntity smsCaptcha = new SmsCaptchaEntity(IdUtil.getUuid(), mobile, captcha);
        smsCaptcha.setAreaCode(areaCode);
        smsCaptcha.setType(type);

        // 3.获得短信内容
        String msg = this.getCaptchaMsg(type, captcha);
        try {
            sendMessage(areaCode,mobile,captcha,msg);
        } catch (Exception e) {
            throw new ServerException("调用发送短信接口错误", e);
        }

        // 5.存储短信记录
        int cnt = this.smsCaptchaDao.updateSmsCaptcha(smsCaptcha);
        if(cnt == 0) {
            this.smsCaptchaDao.addSmsCaptcha(smsCaptcha);
        }
    }


    public void sendMessage(String areaCode, String mobile, String msg){
        //组装请求参数
        String url = SmsConstant.urlGW;
        String account = SmsConstant.accountGW;
        String pswd = SmsConstant.passwordGW;
        String signEN = SmsConstant.signEN;
        if(StringUtil.isEmpty(areaCode) || "86".equals(areaCode) ){
            url = SmsConstant.urlGL;
            account = SmsConstant.accountGL;
            pswd = SmsConstant.passwordGL;
        }

        SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, mobile,"true");
        String requestJson = JSON.toJSONString(smsSingleRequest);
        String response = ChuangLanSmsUtil.sendSmsByPost(url, requestJson);
        logger.info("response after request result is :" + response);
        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
        logger.info("response  toString is :" + smsSingleResponse);

    }


    public void sendMessage(String areaCode, String mobile, String captcha, String msg){

        //组装请求参数
        String url = SmsConstant.urlGW;
        String account = SmsConstant.accountGW;
        String pswd = SmsConstant.passwordGW;
        String signEN = SmsConstant.signEN;
        if(StringUtil.isEmpty(areaCode) || "86".equals(areaCode) ){
            url = SmsConstant.urlGL;
            account = SmsConstant.accountGL;
            pswd = SmsConstant.passwordGL;
        }

        SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, mobile,"true");
        String requestJson = JSON.toJSONString(smsSingleRequest);
        String response = ChuangLanSmsUtil.sendSmsByPost(url, requestJson);
        logger.info("response after request result is :" + response);
        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
        logger.info("response  toString is :" + smsSingleResponse);

    }

    /**
     * 获取验证码消息
     * @param type 验证码类型：SmsConstants.Type.[VERIFICATION_REGISTER,VERIFICATION_FORGET]
     * @param captcha 6位数字验证码
     * @return String 消息内容
     */
    public String getCaptchaMsg(String type, String captcha) {
        StringBuffer template = null;
        if (SmsConstants.Type.VERIFICATION_REGISTER.equals(type)) {
            template = new StringBuffer(SmsConstants.Template.VERIFICATION_REGISTER);
        } else if (SmsConstants.Type.VERIFICATION_FORGET.equals(type)) {
            template = new StringBuffer(SmsConstants.Template.VERIFICATION_FORGET);
        } else if (SmsConstants.Type.VERIFICATION_TRADE.equals(type)) {
            template = new StringBuffer(SmsConstants.Template.VERIFICATION_TRADE);
        } else if (SmsConstants.Type.VERIFICATION_EMAIL.equals(type)) {
            template = new StringBuffer(SmsConstants.Template.VERIFICATION_EMAIL);
        } else if (SmsConstants.Type.VERIFICATION_TRADE_GESTURE.equals(type)) {
            template = new StringBuffer(SmsConstants.Template.VERIFICATION_TRADE_GESTURE);
        } else if (SmsConstants.Type.VERIFICATION_LOGIN_GESTURE.equals(type)) {
            template = new StringBuffer(SmsConstants.Template.VERIFICATION_LOGIN_GESTURE);
        }else if(SmsConstants.Type.VERIFICATION_COMMON.equals(type)){
            template = new StringBuffer(SmsConstants.Template.VERIFICATION_COMMON);
        }
        return replaceParam(template, captcha).toString();
    }

    /**
     * 替换消息模板参数
     *
     * @param template 短信模板
     * @param paramVal 参数(验证码值)
     * @return StringBuffer
     */
    public StringBuffer replaceParam(StringBuffer template, String paramVal) {
        int idx = template.indexOf(PARAM);
        return template.replace(idx, idx + PARAM.length(), paramVal);
    }

    /**
     * 检查是否允许发送短信验证码(1分钟以内只能发送一条短信)
     * @param mobile 手机号
     * @return boolean
     */
    public boolean isAllowCaptcha(String mobile) {
        return this.smsCaptchaDao.getAvailableCaptchaCntByMobile(mobile) == 0;
    }


    /**
     * 检查手机短信验证码是否有效(30分钟)
     * @param mobile 手机号
     * @param captcha 验证码
     * @return int
     */
    public int checkSmsCode(String mobile, String captcha) {
        return this.smsCaptchaDao.getCntByCaptchaAndMobile(mobile, captcha);
    }

    /**
     * 检查手机短信验证码是否有效(30分钟)
     * @param mobile 手机号
     * @param captcha 验证码
     * @return boolean
     */
    public boolean checkCaptcha(String mobile, String captcha) {
        return this.smsCaptchaDao.getCntByCaptchaAndMobile(mobile, captcha) > 0;
    }

    public boolean checkCaptcha(String areaCode,String mobile, String captcha) {
        return this.smsCaptchaDao.getCntByCaptcha(areaCode, mobile, captcha) > 0;
    }

    @Override
    public SmsCaptchaEntity querySmsCaptcha(String mobile) {
        return this.smsCaptchaDao.querySmsCaptcha(mobile);
    }

    /**
     * 校验手机号码是否通过
     * @param mobile  手机号码
     * @param code    验证码   通过：true  失败：false
     * @return
     */
    @Override
    public boolean querySmsCaptchaIsPass(String mobile,String code) {
        SmsCaptchaEntity smsCaptchaEntity = querySmsCaptcha(mobile);
        if (StringUtils.checkValNull(smsCaptchaEntity)) {
            return false;
        }
        return smsCaptchaEntity.getCode().equals(code);
    }

}
