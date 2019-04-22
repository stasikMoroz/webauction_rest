package com.vironit.businessauction.exception;

public class WalletException extends RuntimeException {
    public WalletException() {
    }

    public WalletException(String message) {
        super(message);
    }

    public WalletException(String message, Throwable cause) {
        super(message, cause);
    }

    public WalletException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "The sum can't be negative!";
    }
}
