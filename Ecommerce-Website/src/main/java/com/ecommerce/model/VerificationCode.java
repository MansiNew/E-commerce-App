package com.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class VerificationCode {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long verificationId;
	private String otp;
	@Column(unique=true)
	private String email;
	@OneToOne
	private Seller seller;
	@OneToOne
	private User user;
}
