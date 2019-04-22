package com.vironit.businessauction.exception;

public class TradeException extends RuntimeException {
    public TradeException() {
    }


    public TradeException(String message) {
        super(message);
    }

    public TradeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TradeException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Your price is less than current price of lot with id: " +  super.getMessage();
    }
}
