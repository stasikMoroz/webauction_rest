package com.vironit.businessauction.exception;

public class LotCanNotBeActivatedException extends RuntimeException {
    public LotCanNotBeActivatedException() {
    }

    public LotCanNotBeActivatedException(String message) {
        super(message);
    }

    public LotCanNotBeActivatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LotCanNotBeActivatedException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Lot has status " + super.getMessage() + ", but must have status NEW, PAUSERD, SOLD_BUT_NOT_PAID to be activated!";
    }
}
