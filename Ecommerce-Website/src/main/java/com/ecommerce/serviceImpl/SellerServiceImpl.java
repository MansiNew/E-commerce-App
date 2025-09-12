package com.ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	// private final ProductRepository productRepo;
	private final SellerMapper sellerMapper;
	private PasswordEncoder passwordEncofer;

	private static final Logger logger = LoggerFactory.getLogger(SellerService.class);

	@Override
	public SellerResponse createSeller(SellerRequest request) throws Exception {

		try {
			Seller seller = sellerRepo.findByEmail(request.getEmail());
			if (seller != null) {
				logger.error("seller already exist=======");
				throw new Exception("seller already exist");
			}
			Seller createdSeller = new Seller();
			logger.info("seller creation process start===");
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

			Address address = new Address();

			AddressRequest addressRequest = request.getPickupAddress();
			address.setAddress(addressRequest.getAddress());
			address.setCity(addressRequest.getCity());
			address.setName(addressRequest.getName());
			address.setLocality(addressRequest.getLocality());
			address.setMobileNo(addressRequest.getMobileNo());
			address.setState(addressRequest.getState());
			address.setPincode(addressRequest.getPincode());
			address.setStreet(addressRequest.getStreet());
			logger.info("seller address details=======" + address);
			addressRepo.save(address);

			createdSeller.setPickupAddress(address);
			Product product = new Product();
			// this is pending for now
			createdSeller.setProduct(product);
			logger.info("seller a details=======" + createdSeller);
			sellerRepo.save(createdSeller);
			logger.info("seller save successfuly");
			return null;
		} catch (Exception e) {

			throw new Exception("s");
		}
	}

	@Override
	public SellerResponse findSellerByEmail(String email) throws Exception {
		Seller seller = sellerRepo.findByEmail(email);
		SellerResponse response = new SellerResponse();
		if (seller != null) {

			response.setAccountStatus(seller.getAccountStatus());
			response.setBankDetails(seller.getBankDetails());
			response.setBuisnessDetails(seller.getBuisnessDetails());
			response.setEmail(seller.getEmail());
			response.setGstIn(seller.getGstIn());
			response.setIsEmailVerified(seller.getIsEmailVerified());
			response.setMobileNo(seller.getMobileNo());
			// response.setPickupAddress(seller.getPickupAddress());
			response.setRole(seller.getRole());
			response.setSellerName(seller.getSellerName());
		} else {
			throw new Exception("Seller is not exist");
		}
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	public SellerResponse getSellerProfileByJwt(String jwt) {
		String email = jwtProvider.getEmailFromJwt(jwt);
		Seller seller = sellerRepo.findByEmail(email);
		SellerResponse response = new SellerResponse();
		response.setAccountStatus(seller.getAccountStatus());
		response.setBankDetails(seller.getBankDetails());
		response.setBuisnessDetails(seller.getBuisnessDetails());
		response.setEmail(seller.getEmail());
		response.setGstIn(seller.getGstIn());
		response.setIsEmailVerified(seller.getIsEmailVerified());
		response.setMobileNo(seller.getMobileNo());
		// response.setPickupAddress(seller.getPickupAddress());
		response.setRole(seller.getRole());
		response.setSellerName(seller.getSellerName());
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	public List<SellerResponse> getAllSellers(AccountStatus status) {
		List<SellerResponse> responseList = new ArrayList<SellerResponse>();

		try {
			// TODO Auto-generated method stub
			List<Seller> sellerList = sellerRepo.findByAccountStatus(status);
			if (!sellerList.isEmpty()) {
				for (Seller seller : sellerList) {
					SellerResponse response = new SellerResponse();
					response.setAccountStatus(seller.getAccountStatus());
					response.setBankDetails(seller.getBankDetails());
					response.setBuisnessDetails(seller.getBuisnessDetails());
					response.setEmail(seller.getEmail());
					response.setGstIn(seller.getGstIn());
					response.setIsEmailVerified(seller.getIsEmailVerified());
					response.setMobileNo(seller.getMobileNo());
					// response.setPickupAddress(seller.getPickupAddress());
					response.setRole(seller.getRole());
					response.setSellerName(seller.getSellerName());
					responseList.add(response);
				}
			} else {
				logger.debug("list is empty" + sellerList);
			}

		} catch (Exception e) {
			logger.error("something went wrong" + e);
		}
		return responseList;
	}

	@Override
	public SellerResponse updateSeller(Long sellerId, SellerRequest request) throws Exception {
		SellerResponse response = new SellerResponse();
		Optional<Seller> sellerExist = sellerRepo.findById(sellerId);
		if (sellerExist.isPresent()) {
			Seller seller = sellerExist.get();
			Seller updateSeller = new Seller();
			updateSeller.setAccountStatus(request.getAccountStatus());
			updateSeller.setSellerId(sellerId);
			sellerRepo.save(updateSeller);
			response.setAccountStatus(updateSeller.getAccountStatus());
		} else {
			throw new Exception("Seller not exist");
		}
		// TODO Auto-generated method stub

		return response;
	}

	@Override
	public SellerResponse getSellerById(Long sellerId) throws Exception {
		SellerResponse response = new SellerResponse();
		// TODO Auto-generated method stub
		Seller seller = sellerRepo.findById(sellerId).orElseThrow(() -> new Exception("seler not found"));
		response.setAccountStatus(seller.getAccountStatus());
		response.setBankDetails(seller.getBankDetails());
		response.setBuisnessDetails(seller.getBuisnessDetails());
		response.setEmail(seller.getEmail());
		response.setGstIn(seller.getGstIn());
		response.setIsEmailVerified(seller.getIsEmailVerified());
		response.setMobileNo(seller.getMobileNo());
		// response.setPickupAddress(seller.getPickupAddress());
		response.setRole(seller.getRole());
		response.setSellerName(seller.getSellerName());
		return response;
	}

	@Override
	public void deleteSeller(Long sellerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public SellerResponse verifySeller(String email, String otp) throws Exception {

		Seller seller = sellerRepo.findByEmail(email);
		SellerResponse response = new SellerResponse();
		if (seller != null) {
			logger.info("verify seller by email");
			seller.setIsEmailVerified(Boolean.TRUE);
			sellerRepo.save(seller);
			response.setAccountStatus(seller.getAccountStatus());
			response.setBankDetails(seller.getBankDetails());
			response.setBuisnessDetails(seller.getBuisnessDetails());
			response.setEmail(seller.getEmail());
			response.setGstIn(seller.getGstIn());
			response.setIsEmailVerified(seller.getIsEmailVerified());
			response.setMobileNo(seller.getMobileNo());
			// response.setPickupAddress(seller.getPickupAddress());
			response.setRole(seller.getRole());
			response.setSellerName(seller.getSellerName());
		} else {
			throw new Exception("Seller is not exist");
		}
		return response;
	}

	@Override
	public SellerResponse updateSellerStatus(Long sellerId, AccountStatus status) throws Exception {
		SellerResponse response = new SellerResponse();
		Optional<Seller> sellerExist = sellerRepo.findById(sellerId);
		if (sellerExist.isPresent()) {
			Seller seller = sellerExist.get();
			Seller updateSellerAccount = new Seller();
			updateSellerAccount.setAccountStatus(status);
			updateSellerAccount.setSellerId(sellerId);
			sellerRepo.save(updateSellerAccount);
			response.setAccountStatus(updateSellerAccount.getAccountStatus());
		} else {
			throw new Exception("Seller not exist");
		}
		// TODO Auto-generated method stub
		return null;
	}

}
