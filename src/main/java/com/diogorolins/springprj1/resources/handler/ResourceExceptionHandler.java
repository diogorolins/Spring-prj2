package com.diogorolins.springprj1.resources.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.diogorolins.springprj1.exceptions.AuthorizationException;
import com.diogorolins.springprj1.exceptions.DatabaseException;
import com.diogorolins.springprj1.exceptions.FileException;
import com.diogorolins.springprj1.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), 
				HttpStatus.NOT_FOUND.value(), 
				"Recurso não encontrado", 
				e.getMessage(), 
				request.getRequestURI());		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> databaseIntegrity(DatabaseException e, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), 
				HttpStatus.BAD_REQUEST.value(), 
				"Problema de integridade de dados", 
				e.getMessage(), 
				request.getRequestURI());		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(System.currentTimeMillis(), 
				HttpStatus.BAD_REQUEST.value(), 
				"Erro de validação.", 
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
	
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<StandardError> validation(AccessDeniedException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(System.currentTimeMillis(), 
				HttpStatus.FORBIDDEN.value(), 
				"Access Denied", 
				e.getMessage(), 
				request.getRequestURI());	
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> validation(AuthorizationException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(System.currentTimeMillis(), 
				HttpStatus.FORBIDDEN.value(), 
				"Access Denied", 
				e.getMessage(), 
				request.getRequestURI());	
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> validation(FileException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(System.currentTimeMillis(), 
				HttpStatus.FORBIDDEN.value(), 
				"Error in File", 
				e.getMessage(), 
				request.getRequestURI());	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonException(AmazonServiceException e, HttpServletRequest request) {
		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
		ValidationError err = new ValidationError(System.currentTimeMillis(), 
				code.value(), 
				"Error in File", 
				e.getMessage(), 
				request.getRequestURI());	
		return ResponseEntity.status(code).body(err);
	}
	
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(System.currentTimeMillis(), 
				HttpStatus.BAD_REQUEST.value(), 
				"Error in File", 
				e.getMessage(), 
				request.getRequestURI());	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3Exception(AmazonS3Exception e, HttpServletRequest request) {
		ValidationError err = new ValidationError(System.currentTimeMillis(), 
				HttpStatus.BAD_REQUEST.value(), 
				"Error in File", 
				e.getMessage(), 
				request.getRequestURI());	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
}
