package com.vironit.businessauction.exception;

public class LotNotHavePauseOrNewStatusException extends RuntimeException {
    public LotNotHavePauseOrNewStatusException() {
    }

    public LotNotHavePauseOrNewStatusException(String message) {
        super(message);
    }

    public LotNotHavePauseOrNewStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public LotNotHavePauseOrNewStatusException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Lot with the id=" + super.getMessage() + " doesn't have status PAUSED or NEW!";
    }
}
