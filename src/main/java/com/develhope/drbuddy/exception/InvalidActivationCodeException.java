package com.develhope.drbuddy.exception;

public class InvalidActivationCodeException extends RuntimeException {

	/**
	 *SerialVersionUID is a unique identifier for each class
	 */

	private static final long serialVersionUID = -6855870110957747045L;

	public InvalidActivationCodeException() {
		super();
	}

	public InvalidActivationCodeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidActivationCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidActivationCodeException(String message) {
		super(message);
	}

	public InvalidActivationCodeException(Throwable cause) {
		super(cause);
	}

}
