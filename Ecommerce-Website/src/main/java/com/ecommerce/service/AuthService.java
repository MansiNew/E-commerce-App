package com.ecommerce.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.model.User;
import com.ecommerce.request.LoginRequest;
import com.ecommerce.request.SignUpRequest;
import com.ecommerce.response.AuthResponse;

public interface AuthService {
	public String createUserHandler(@RequestBody SignUpRequest request) throws Exception;
	public void sendLoginOtp(String email) throws Exception	;
	public AuthResponse userSignin(LoginRequest request);
	}

