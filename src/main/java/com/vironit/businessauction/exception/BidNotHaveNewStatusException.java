package com.vironit.businessauction.exception;

public class BidNotHaveNewStatusException extends RuntimeException {
    public BidNotHaveNewStatusException() {
    }

    public BidNotHaveNewStatusException(String message) {
        super(message);
    }

    public BidNotHaveNewStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public BidNotHaveNewStatusException(Throwable cause) {
        super(cause);
    }
}
