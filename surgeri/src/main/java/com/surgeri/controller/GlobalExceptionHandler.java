package com.surgeri.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.surgeri.exception.AuthException;
import com.surgeri.exception.NotFoundException;
import com.surgeri.model.ApiFailureResponse;

/**
 * @author amitb
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AuthException.class)
	public ResponseEntity<ApiFailureResponse> handleAuthException(AuthException e){
		return new ResponseEntity<>(e.getApiFailureResponse(), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiFailureResponse> handleNotFoundException(NotFoundException e){
		return new ResponseEntity<>(e.getApiFailureResponse(), HttpStatus.NOT_FOUND);
	}

}
