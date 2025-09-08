package com.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.domain.USER_ROLE;
import com.ecommerce.model.User;
import com.ecommerce.model.VerificationCode;
import com.ecommerce.repo.UserRepository;
import com.ecommerce.request.LoginRequest;
import com.ecommerce.request.SignUpRequest;
import com.ecommerce.request.VerificationOtpRequest;
import com.ecommerce.response.AuthResponse;
import com.ecommerce.response.SendOtpResponse;
import com.ecommerce.service.AuthService;
import com.ecommerce.serviceImpl.AuthServiceImpl;
import com.ecommerce.utility.LoginConstants;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final UserRepository userRepository;
	private final AuthServiceImpl authService;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignUpRequest request) throws Exception {

		String jwtToken = authService.createUserHandler(request);
		AuthResponse response = new AuthResponse();
		response.setMessage("user created successfully");
		response.setRole(USER_ROLE.ROLE_CUSTOMER);
		response.setJwtToken(jwtToken);
		return ResponseEntity.ok(response);

	}

	@PostMapping("/sent-otp")
	public ResponseEntity<SendOtpResponse> sendOtpHandler(@RequestBody VerificationOtpRequest request)
			throws Exception {
		authService.sendLoginOtp(request.getEmail());
		SendOtpResponse response = new SendOtpResponse();
		response.setMessage(LoginConstants.OTP_SUCCESSFULL_MESSAGE);
		return ResponseEntity.ok(response);

	}

	@PostMapping("/signin-user")
	public ResponseEntity<AuthResponse> userSignin(@RequestBody LoginRequest request) throws Exception {
		AuthResponse response = authService.userSignin(request);

		return ResponseEntity.ok(response);

	}
	@PostMapping("/save-user")
 	public ResponseEntity<String> userSignin(@RequestBody User request) throws Exception{
 	userRepository.save(request);
 	
 	return ResponseEntity.ok("save");
 	
 	
 }
}