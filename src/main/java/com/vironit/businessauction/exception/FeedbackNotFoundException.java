package com.vironit.businessauction.exception;

public class FeedbackNotFoundException extends EntityNotFoundException {
    public FeedbackNotFoundException() {
    }

    public FeedbackNotFoundException(String message) {
        super(message);
    }

    public FeedbackNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeedbackNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Feedback" + super.getMessage();
    }
}
