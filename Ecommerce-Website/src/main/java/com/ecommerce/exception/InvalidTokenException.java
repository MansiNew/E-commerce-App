package com.ecommerce.exception;

import io.jsonwebtoken.JwtException;
import lombok.Data;

@Data
public class InvalidTokenException extends RuntimeException {
	private String message;

	public InvalidTokenException(String message) {
		super();
		this.message = message;
	}

	

}
