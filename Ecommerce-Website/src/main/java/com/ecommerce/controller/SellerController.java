package com.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.User;
import com.ecommerce.request.SellerRequest;
import com.ecommerce.response.SellerResponse;
import com.ecommerce.service.SellerService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {
	private final SellerService sellerService;

	@PostMapping("/seller/profile")
	public ResponseEntity<SellerResponse> createSeller(@RequestBody SellerRequest request) throws Exception {
		SellerResponse response = sellerService.createSeller(request);
		return ResponseEntity.ok(response);
	}
}
