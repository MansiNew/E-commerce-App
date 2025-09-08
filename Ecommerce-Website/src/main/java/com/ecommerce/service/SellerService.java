package com.ecommerce.service;

import java.util.List;

import com.ecommerce.domain.AccountStatus;
import com.ecommerce.request.SellerRequest;
import com.ecommerce.response.SellerResponse;

public interface SellerService {
	public SellerResponse createSeller(SellerRequest request) throws Exception;

	public SellerResponse findSellerByEmail(String email);

	public SellerResponse getSellerProfileByJwt(String jwt);

	public List<SellerResponse> getAllSellers(AccountStatus status);

	public SellerResponse updateSeller(Long sellerId, SellerRequest request);

	public SellerResponse getSellerById(Long sellerId);

	public void deleteSeller(Long sellerId);

	public SellerResponse verifySeller(String email, String otp);

	public SellerResponse updateSellerStatus(Long sellerId, AccountStatus status);
}
