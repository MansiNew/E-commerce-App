package com.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
	public VerificationCode findByEmail(String email);
}
