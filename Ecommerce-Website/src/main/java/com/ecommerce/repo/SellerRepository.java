package com.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Seller;

public interface SellerRepository extends JpaRepository<Seller,Long> {
	public Seller findByEmail(String email);

}
