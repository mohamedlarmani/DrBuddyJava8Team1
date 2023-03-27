package com.develhope.drbuddy.exception.advice;

import com.develhope.drbuddy.entities.dto.BaseResponse;
import com.develhope.drbuddy.exception.InvalidActivationCodeException;
import com.develhope.drbuddy.exception.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

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

}
