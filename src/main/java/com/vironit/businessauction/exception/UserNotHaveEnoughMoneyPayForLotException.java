package com.vironit.businessauction.exception;

public class UserNotHaveEnoughMoneyPayForLotException extends RuntimeException {
    public UserNotHaveEnoughMoneyPayForLotException() {
    }

    public UserNotHaveEnoughMoneyPayForLotException(String message) {
        super(message);
    }

    public UserNotHaveEnoughMoneyPayForLotException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotHaveEnoughMoneyPayForLotException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "User with id: " + super.getMessage() + " doesn't have enough money to pay for the lot!";
    }
}
