package com.dxc.orderservicetest.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String>  accountNotFoundException(HttpClientErrorException accountNumberException) {
		String message=accountNumberException.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}

}
