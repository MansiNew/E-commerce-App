package com.ecommerce.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.model.User;
import com.ecommerce.request.SignUpRequest;

public interface AuthService {
	public String createUserHandler(@RequestBody SignUpRequest request);
		
	}

