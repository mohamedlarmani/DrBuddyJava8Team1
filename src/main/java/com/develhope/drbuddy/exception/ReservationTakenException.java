package com.develhope.drbuddy.exception;

public class ReservationTakenException extends RuntimeException{

    private static final long serialVersionUID = -7962064812360328194L;

    public ReservationTakenException() {
        super();
    }

    public ReservationTakenException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ReservationTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReservationTakenException(String message) {
        super(message);
    }

    public ReservationTakenException(Throwable cause) {
        super(cause);
    }
}
