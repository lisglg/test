package io.damo.sms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.damo.sms.entity.SmsCaptchaEntity;
import org.apache.ibatis.annotations.Param;


/**
 * 验证码信息
 * 
 * @author ives
 * @date 2018-03-07 14:14:47
 */
public interface SmsCaptchaDao extends BaseMapper<SmsCaptchaEntity> {
    /**
     * 根据验证码和手机号查询记录数量(30分钟内)
     * @param mobile
     * @param code
     * @return
     */
    int getCntByCaptchaAndMobile(@Param("mobile") String mobile, @Param("code") String code);

    int getCntByCaptcha(@Param("areaCode") String areaCode, @Param("mobile") String mobile, @Param("code") String code);

    /**
     * 新增短信验证码记录
     * @param smsCaptcha
     * @return
     */
    int addSmsCaptcha(SmsCaptchaEntity smsCaptcha);

    /**
     * 更新用户的短信验证码(创建新的短信验证码)
     * @param smsCaptcha
     * @return
     */
    int updateSmsCaptcha(SmsCaptchaEntity smsCaptcha);

    /**
     * 将手机对应的验证码设为无效
     * @param mobile
     * @return
     */
    int updateSmsCaptchaDisable(@Param("mobile") String mobile);

    /**
     * 通过手机号获取1分钟内可用的短信验证码数量
     * @param mobile
     * @return
     */
    int getAvailableCaptchaCntByMobile(@Param("mobile") String mobile);

    /**
     * 查询验证码
     */
    SmsCaptchaEntity querySmsCaptcha(@Param("mobile") String mobile);
}
