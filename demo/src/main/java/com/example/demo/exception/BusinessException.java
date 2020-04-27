package com.example.demo.exception;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> exception(Exception exception) {
		System.out.println("please correct it.");
		System.out.println("this is second line.");
		return new ResponseEntity<>("Product not  found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = FileNotFoundException.class)
	public ResponseEntity<Object> fileNotFoundException(FileNotFoundException exception) {
		return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ClassCastException.class)
	public ResponseEntity<Object> classCastException(ClassCastException exception) {
		return new ResponseEntity<>("Class cast exception", HttpStatus.NOT_FOUND);
	}

}
