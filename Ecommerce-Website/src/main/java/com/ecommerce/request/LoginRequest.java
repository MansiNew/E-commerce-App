package com.ecommerce.request;

import lombok.Data;

@Data
public class LoginRequest {
private String otp;
private String email;
}
