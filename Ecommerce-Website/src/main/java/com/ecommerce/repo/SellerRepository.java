package com.ecommerce.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.domain.AccountStatus;
import com.ecommerce.model.Seller;

public interface SellerRepository extends JpaRepository<Seller,Long> {
	public Seller findByEmail(String email);
public Optional<Seller> findById(Long sellerId);
public List<Seller> findByAccountStatus(AccountStatus accountStatus);
}
