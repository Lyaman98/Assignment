package com.media.solutions.task.exceptions;

public class TransactionDetailsNotFoundException extends RuntimeException {

    public TransactionDetailsNotFoundException() {
    }

    public TransactionDetailsNotFoundException(String message) {
        super(message);
    }

    public TransactionDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public TransactionDetailsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
