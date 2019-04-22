package com.vironit.businessauction.exception;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "User" + super.getMessage();
    }
}
