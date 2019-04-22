package com.vironit.businessauction.exception;

public class LoginAlredyExistException extends RuntimeException {
    public LoginAlredyExistException() {
    }

    public LoginAlredyExistException(String message) {
        super(message);
    }

    public LoginAlredyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAlredyExistException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " is already exist!";
    }
}
