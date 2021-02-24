package com.bank.poc.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import com.bank.poc.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalException {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest webRequest){

		String errorMessageDescription = ex.getLocalizedMessage();

		if(errorMessageDescription == null) errorMessageDescription = ex.toString();

		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());

		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value= {NullPointerException.class, AccountServiceException.class, AccountNotFoundException.class})
	public ResponseEntity<Object> handleSpecificException(RuntimeException ex, WebRequest webRequest) {
		String errorMessageDescription = ex.getLocalizedMessage();

		if(errorMessageDescription == null) errorMessageDescription = ex.toString();

		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());

		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}