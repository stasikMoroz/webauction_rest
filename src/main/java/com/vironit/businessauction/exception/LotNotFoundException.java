package com.vironit.businessauction.exception;

public class LotNotFoundException extends EntityNotFoundException {

    public LotNotFoundException() {
    }

    public LotNotFoundException(String message) {
        super(message);
    }

    public LotNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LotNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Lot" + super.getMessage();
    }
}
