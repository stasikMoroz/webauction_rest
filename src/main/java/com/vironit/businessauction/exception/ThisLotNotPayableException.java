package com.vironit.businessauction.exception;

public class ThisLotNotPayableException extends RuntimeException {

    public ThisLotNotPayableException() {
    }

    public ThisLotNotPayableException(String message) {
        super(message);
    }

    public ThisLotNotPayableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThisLotNotPayableException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Lot with id: " + super.getMessage() + " can not be payed!";
    }
}
