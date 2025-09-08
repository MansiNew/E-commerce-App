package com.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.model.User;
import com.ecommerce.model.VerificationCode;
import com.ecommerce.repo.UserRepository;
import com.ecommerce.repo.VerificationCodeRepository;
import com.ecommerce.request.LoginRequest;
import com.ecommerce.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserServiceImpl userService;
	private final UserRepository userRepo;
	private final VerificationCodeRepository veriCode;

	@GetMapping("/user/profile")
	public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwtToken) {
		User user = userService.findUserByJwtToken(jwtToken);
		return ResponseEntity.ok(user);
	}
	
	/*@GetMapping("/getUserByEmail")
	public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
		User user=userRepo.findByEmailId(email);
		System.out.println("========"+  user);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/code")
	public ResponseEntity<VerificationCode> getCode(@RequestParam String email) {
		VerificationCode user=veriCode.findByEmail(email);
		System.out.println("========"+  user);
		return ResponseEntity.ok(user);
	}*/
}
