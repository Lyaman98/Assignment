package com.media.solutions.task.exceptions;

public class HeaderNotFoundException extends RuntimeException {

    public HeaderNotFoundException() {
        super();
    }

    public HeaderNotFoundException(String message) {
        super(message);
    }

    public HeaderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HeaderNotFoundException(Throwable cause) {
        super(cause);
    }

    protected HeaderNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
