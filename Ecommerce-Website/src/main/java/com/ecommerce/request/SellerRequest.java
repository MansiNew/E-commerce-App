package com.ecommerce.request;

import com.ecommerce.domain.AccountStatus;
import com.ecommerce.domain.USER_ROLE;
import com.ecommerce.model.BankDetails;
import com.ecommerce.model.BuisnessDetails;
import com.ecommerce.model.Product;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class SellerRequest {

	private String sellerName;
	private String email;
	private String password;
	private String mobileNo;
	private String gstIn;
	private BankDetails bankDetails=new BankDetails();
	private BuisnessDetails buisnessDetails;
	private AddressRequest pickupAddress;
	private Boolean isEmailVerified=Boolean.FALSE;
	private USER_ROLE role=USER_ROLE.ROLE_SELLER;
	private AccountStatus accountStatus=AccountStatus.PENDING_VERIFICATION;
	private Product product;

}
