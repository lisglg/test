package io.damo.sms.constant;

/**
 * 短信服务常量类
 */
public class SmsConstants {

    /** 短信类型 */
    public final class Type {

        /** 短信类型：注册验证码 */
        public static final String VERIFICATION_REGISTER = "0";

        /** 短信类型：忘记密码验证码 */
        public static final String VERIFICATION_FORGET = "1";

        /** 短信类型：重置交易密码验证码 */
        public static final String VERIFICATION_TRADE = "2";

        /** 短信类型：绑定邮箱验证码 */
        public static final String VERIFICATION_EMAIL = "3";
        /** 短信类型：登录手势验证码 */
        public static final String VERIFICATION_LOGIN_GESTURE = "4";

        /** 短信类型：交易手势验证码 */
        public static final String VERIFICATION_TRADE_GESTURE = "5";

        /** 短信类型：通用 */
        public static final String VERIFICATION_COMMON = "6";
    }

    public final class Template {
        /** 短信模板：注册验证码 */
        public static final String VERIFICATION_REGISTER = "您正在注册新账号，验证码%@%。妥善保管，请勿转发";

        /** 短信模板：忘记密码验证码 */
        public static final String VERIFICATION_FORGET = "您正在进行忘记密码操作，验证码%@%。妥善保管，请勿转发";

        /** 短信模板：重置交易密码验证码 */
        public static final String VERIFICATION_TRADE = "您正在进行重置交易密码操作，验证码%@%。妥善保管，请勿转发";

        /** 短信模板：绑定邮箱验证码 */
        public static final String VERIFICATION_EMAIL = "您正在进行设置绑定邮箱操作，验证码%@%。妥善保管，请勿转发";

        /** 短信模板：登录手势验证码 */
        public static final String VERIFICATION_LOGIN_GESTURE = "您正在进行设置登录手势操作，验证码%@%。妥善保管，请勿转发";

        /** 短信模板：交易手势验证码 */
        public static final String VERIFICATION_TRADE_GESTURE = "您正在进行设置交易手势操作，验证码%@%。妥善保管，请勿转发";

        /** 短信模板：通用 */
        public static final String VERIFICATION_COMMON = "验证码%@%。妥善保管，请勿转发";
    }
}
