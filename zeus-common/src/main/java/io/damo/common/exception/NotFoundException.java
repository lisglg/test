package io.damo.common.exception;

/** 未找到请求 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }
    public NotFoundException(String msg) {
        super(msg);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
