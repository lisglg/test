package io.damo.common.exception;

import com.aliyun.oss.ServiceException;

public class BadRequestException extends ServiceException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
