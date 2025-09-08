package com.ecommerce.exception;

public class UserNotFoundException extends RuntimeException {

	private String message;

	public UserNotFoundException(String message) {
		super();
		this.message = message;
	}

}
