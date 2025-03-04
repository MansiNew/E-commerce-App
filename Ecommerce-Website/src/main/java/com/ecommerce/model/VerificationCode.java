package com.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class VerificationCode {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long verificationId;
	private String otp;
	private String email;
	@OneToOne
	private Seller seller;
	@OneToOne
	private User user;
}
