package com.vironit.businessauction.exception;

public class LoginOrPasswordIncorrectException extends RuntimeException {
    public LoginOrPasswordIncorrectException() {
    }

    public LoginOrPasswordIncorrectException(String message) {
        super(message);
    }

    public LoginOrPasswordIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginOrPasswordIncorrectException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Login or password incorrect!";
    }
}
