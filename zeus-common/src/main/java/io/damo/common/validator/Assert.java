package io.damo.common.validator;

import com.aliyun.oss.ServiceException;
import io.damo.common.exception.BadRequestException;
import io.damo.common.exception.RRException;
import io.damo.common.utils.RegexUtil;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }

    public static void assertMobile(String mobile) throws ServiceException {
        assertCase(mobile == null || mobile.trim().length() == 0, "请输入手机号码！");
        assertCase(!RegexUtil.checkMobile(mobile), "手机号格式不正确，请重新输入！");
    }

    public static void assertCase(boolean b, String message) {
        if(b) {
            throw new RRException(message);
        }
    }

    public static void assertParam(boolean b, String message) throws BadRequestException {
        if(b) {
            throw new BadRequestException(message);
        }
    }

    public static <T extends Enum<T>> T assertEnumParam(Class<T> clazz, String name, String message) {
        try {
            return Enum.valueOf(clazz, name);
        } catch (IllegalArgumentException var4) {
            throw new BadRequestException(message);
        }
    }

    public static void assertCase(boolean b, String message, Throwable cause) throws ServiceException {
        if(b) {
            throw new ServiceException(message, cause);
        }
    }
}
