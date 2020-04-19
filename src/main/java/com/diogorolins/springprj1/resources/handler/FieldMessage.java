package com.diogorolins.springprj1.resources.handler;

import java.io.Serializable;

public class FieldMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String error;
	private String message;
	
	public FieldMessage() {
		
	}

	public FieldMessage(String error, String message) {
		super();
		this.error = error;
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
