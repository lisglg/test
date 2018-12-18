package io.damo.sms.service;

import com.baomidou.mybatisplus.service.IService;
import io.damo.sms.entity.SmsCaptchaEntity;

/**
 * 验证码信息
 *
 * @author ives
 * @date 2018-03-07 14:14:47
 */
public interface SmsCaptchaService extends IService<SmsCaptchaEntity> {

    /**
     * 发送短信验证码
     * @param  areaCode 国际区号(86,852)
     * @param mobile 手机号
     * @param type 验证码类型：SmsConstants.Type.[VERIFICATION_REGISTER,VERIFICATION_FORGET]
     */
    void sendSmsCaptcha(String areaCode, String mobile, String type);

    void sendMessage(String areaCode, String mobile, String captcha, String msg);

    void sendMessage(String areaCode, String mobile, String msg);
    /**
     * 获取验证码消息
     * @param type 验证码类型：SmsConstants.Type.[VERIFICATION_REGISTER,VERIFICATION_FORGET]
     * @param captcha 6位数字验证码
     * @return String 消息内容
     */
    String getCaptchaMsg(String type, String captcha);

    /**
     * 替换消息模板参数
     *
     * @param template 短信模板
     * @param paramVal 参数(验证码值)
     * @return
     */
    StringBuffer replaceParam(StringBuffer template, String paramVal);

    /**
     * 检查是否允许发送短信验证码(1分钟以内只能发送一条短信)
     * @param mobile 手机号
     * @return boolean
     */
    boolean isAllowCaptcha(String mobile);

    /**
     * 检查手机短信验证码是否有效(30分钟)
     * @param mobile 手机号
     * @param captcha 验证码
     * @return boolean
     */
    boolean checkCaptcha(String mobile, String captcha);

    boolean checkCaptcha(String areaCode, String mobile, String captcha);

    SmsCaptchaEntity querySmsCaptcha(String mobile);

    /**
     * 检查验证码是否校验通过
     * @param mobile  手机号码
     * @param code    验证码
     * @return     通过：true  失败：false
     */
    boolean querySmsCaptchaIsPass(String mobile,String code);

    /**
     * 检查手机短信验证码是否有效(30分钟)
     * @param mobile 手机号
     * @param captcha 验证码
     * @return boolean
     */
    int checkSmsCode(String mobile, String captcha);

}

