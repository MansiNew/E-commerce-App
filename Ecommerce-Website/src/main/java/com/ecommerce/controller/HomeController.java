package com.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class HomeController {
	@GetMapping("/getMessage")
	@Operation(summary = "Say hello", description = "Returns a greeting message to the user"

	)
	public String myApp() {
		return "wellcome in ecommerce app";
	}

}
