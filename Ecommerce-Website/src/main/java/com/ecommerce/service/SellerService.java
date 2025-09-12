package com.ecommerce.service;

import java.util.List;

import com.ecommerce.domain.AccountStatus;
import com.ecommerce.request.SellerRequest;
import com.ecommerce.response.SellerResponse;

public interface SellerService {
	public SellerResponse createSeller(SellerRequest request) throws Exception;

	public SellerResponse findSellerByEmail(String email) throws Exception;

	public SellerResponse getSellerProfileByJwt(String jwt);

	public List<SellerResponse> getAllSellers(AccountStatus status);

	public SellerResponse updateSeller(Long sellerId, SellerRequest request) throws Exception;

	public SellerResponse getSellerById(Long sellerId) throws Exception;

	public void deleteSeller(Long sellerId);

	public SellerResponse verifySeller(String email, String otp) throws Exception;

	public SellerResponse updateSellerStatus(Long sellerId, AccountStatus status) throws Exception;
}
