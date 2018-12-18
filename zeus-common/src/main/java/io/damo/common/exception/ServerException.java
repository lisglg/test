package io.damo.common.exception;

/**
 * Created by pinshiern on 2017/7/31.
 */
public class ServerException extends RuntimeException {
    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause, true, false);
    }
}
