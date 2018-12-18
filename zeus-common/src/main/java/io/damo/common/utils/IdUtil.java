package io.damo.common.utils;

import java.util.UUID;

/**
 * Id生成工具类
 */
public class IdUtil {
    public IdUtil() {
    }

    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
