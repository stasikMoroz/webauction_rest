package com.vironit.businessauction.exception;

public class BidNotFoundException extends EntityNotFoundException {
    public BidNotFoundException() {
    }

    public BidNotFoundException(String message) {
        super(message);
    }

    public BidNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BidNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Bid" + super.getMessage();
    }
}
