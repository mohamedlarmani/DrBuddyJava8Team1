package com.develhope.drbuddy.exception.advice;

import com.develhope.drbuddy.entities.dto.BaseResponse;
import com.develhope.drbuddy.exception.InvalidActivationCodeException;
import com.develhope.drbuddy.exception.ReservationTakenException;
import com.develhope.drbuddy.exception.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * This method is an exception handler for InvalidActivationCodeException.
	 * It returns a BaseResponse object with an error message and sets the HTTP status to BAD_REQUEST.
	 * @param e The InvalidActivationCodeException object to handle.
	 * @return The BaseResponse object with the error message
	 */

	@ExceptionHandler(InvalidActivationCodeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse handleInvalidActivationCodeException(InvalidActivationCodeException e) {
		BaseResponse br = new BaseResponse();
		br.setStatus(BaseResponse.Status.KO);
		if(e.getMessage() != null) {
			br.setErrorMessage(e.getMessage());
		} else {
			br.setErrorMessage("INVALID_ACTIVATION_CODE");
		}
		return br;
	}

	/**
	 * Exception handler for UserNotFoundException. Returns a BaseResponse with a KO status and an error message.
	 * If the exception message is not null, it will be used as the error message.
	 * Instead, the default message "USER_NOT_FOUND" will be used.
	 * @param e UserNotFoundException object thrown when a user is not found in the system
	 * @return a BaseResponse object with KO status and an error message
	 */
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse handleUserNotFoundException(UserNotFoundException e) {
		BaseResponse br = new BaseResponse();
		br.setStatus(BaseResponse.Status.KO);
		if(e.getMessage() != null) {
			br.setErrorMessage(e.getMessage());
		} else {
			br.setErrorMessage("USER_NOT_FOUND");
		}
		return br;
	}

	/**
	 * Exception handler for ReservationTakenException. Returns a BaseResponse with a KO status and an error message.
	 * If the exception message is not null, it will be used as the error message.
	 * Instead, the default message "RESERVATION_TAKEN" will be used.
	 * @param e ReservationTakenException object thrown when a reservation is not taken in the system
	 * @return a BaseResponse object with KO status and an error message
	 */
	@ExceptionHandler(ReservationTakenException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse handleReservationTakerException (ReservationTakenException e){
		BaseResponse br = new BaseResponse();
		br.setStatus(BaseResponse.Status.KO);
		if(e.getMessage() != null) {
			br.setErrorMessage(e.getMessage());
		} else {
			br.setErrorMessage("RESERVATION_TAKEN");
		}
		return br;
	}

}
