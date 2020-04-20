package com.diogorolins.springprj1.resources.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.diogorolins.springprj1.exceptions.DatabaseException;
import com.diogorolins.springprj1.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), 
				HttpStatus.NOT_FOUND.value(), 
				"Resource not found", 
				e.getMessage(), 
				request.getRequestURI());		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> databaseIntegrity(DatabaseException e, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), 
				HttpStatus.BAD_REQUEST.value(), 
				"Integrity Problem", 
				e.getMessage(), 
				request.getRequestURI());		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(System.currentTimeMillis(), 
				HttpStatus.BAD_REQUEST.value(), 
				"Validation Error", 
				e.getMessage(), 
				request.getRequestURI());	
		for(FieldError fe : e.getBindingResult().getFieldErrors()) {
			err.addErrors(fe.getField(), fe.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<StandardError> validation(IllegalStateException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(System.currentTimeMillis(), 
				HttpStatus.BAD_REQUEST.value(), 
				"Validation Error", 
				e.getMessage(), 
				request.getRequestURI());	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
}
