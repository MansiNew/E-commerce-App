package com.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.domain.USER_ROLE;
import com.ecommerce.model.User;
import com.ecommerce.repo.UserRepository;
import com.ecommerce.request.SignUpRequest;
import com.ecommerce.response.AuthResponse;
import com.ecommerce.service.AuthService;
import com.ecommerce.serviceImpl.AuthServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final UserRepository userRepository;
	private final AuthServiceImpl authService;
	@PostMapping("/signup")
public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignUpRequest request){
		
	String jwtToken = authService.createUserHandler(request);
	System.out.println("JWT TOKE"+   jwtToken);
	AuthResponse response=new AuthResponse();
	response.setMessage("user created successfully");
	response.setRole(USER_ROLE.ROLE_CUSTOMER);
	response.setJwtToken(jwtToken);
	return ResponseEntity.ok(response);
		//return ResponseEntity.ok(request);
}
}
