package com.ecommerce.serviceImpl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.config.JwtProvider;
import com.ecommerce.domain.AccountStatus;
import com.ecommerce.domain.USER_ROLE;
import com.ecommerce.mapper.SellerMapper;
import com.ecommerce.model.Address;
import com.ecommerce.model.Product;
import com.ecommerce.model.Seller;
import com.ecommerce.repo.AddressRepository;
import com.ecommerce.repo.SellerRepository;
import com.ecommerce.request.AddressRequest;
import com.ecommerce.request.SellerRequest;
import com.ecommerce.response.SellerResponse;
import com.ecommerce.service.SellerService;

import lombok.RequiredArgsConstructor;

/*
 * Sellers related activities.
 * 
 * @date 28-03-25
 */
@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
	private final SellerRepository sellerRepo;
	private final JwtProvider jwtProvider;
	private final AddressRepository addressRepo;
	//private final ProductRepository productRepo;
	private final SellerMapper sellerMapper;
	private PasswordEncoder passwordEncofer;

	@Override
	public SellerResponse createSeller(SellerRequest request) throws Exception {

		try {
			Seller seller=sellerRepo.findByEmail(request.getEmail());
			if(seller!=null) {
				throw new Exception("seller already exist");
			}
			Address address=new Address();
			AddressRequest addressRequest=request.getPickupAddress();
			address.setAddress(addressRequest.getAddress());
			address.setCity(addressRequest.getCity());
			address.setName(addressRequest.getName());
			addressRepo.save(address);
			
			Seller  createdSeller=new Seller();
			createdSeller.setAccountStatus(request.getAccountStatus());
			createdSeller.setBankDetails(request.getBankDetails());
			createdSeller.setBuisnessDetails(request.getBuisnessDetails());
			createdSeller.setEmail(request.getEmail());
			createdSeller.setGstIn(request.getGstIn());
			createdSeller.setIsEmailVerified(request.getIsEmailVerified());
			createdSeller.setMobileNo(request.getMobileNo());
			createdSeller.setPassword(request.getPassword());
			createdSeller.setRole(USER_ROLE.ROLE_SELLER);
			createdSeller.setSellerName(request.getSellerName());
			
			createdSeller.setPickupAddress(address);
			Product product=new Product();
			createdSeller.setProduct(product);
			sellerRepo.save(createdSeller);
			
			return null;
		} catch (Exception e) {
			
			throw new Exception("s");
		}
	}

	@Override
	public SellerResponse findSellerByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SellerResponse getSellerProfileByJwt(String jwt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SellerResponse> getAllSellers(AccountStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SellerResponse updateSeller(Long sellerId, SellerRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SellerResponse getSellerById(Long sellerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSeller(Long sellerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public SellerResponse verifySeller(String email, String otp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SellerResponse updateSellerStatus(Long sellerId, AccountStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

}
