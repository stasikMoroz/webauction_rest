package com.vironit.businessauction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AdminNotFoundException extends EntityNotFoundException {
    public AdminNotFoundException() {
    }

    public AdminNotFoundException(String message) {
        super(message);
    }

    public AdminNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Admin" + super.getMessage();
    }
}
