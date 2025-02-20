package com.ecommerce.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.ecommerce.domain.ACCOUNT_STATUS;
import com.ecommerce.domain.USER_ROLE;

import lombok.Data;
@Entity
@Data
public class Seller {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sellerId;
	private String sellerName;
	@NotNull
	@Column(nullable=true,unique=true)
	private String email;
	private String password;
	private String mobileNo;
	private String gstIn;
	/*
	 *@Embedded is used to indicate that a specific class should be treated as an "embedded" object,
	 * meaning its data will be stored within the same database table as the parent entity, 
	 * essentially treating the embedded object's attributes as if they were directly part of the parent entity's 
	 */
	@Embedded
	private BankDetails bankDetails=new BankDetails();
	@Embedded
	private BuisnessDetails buisnessDetails;
	@OneToOne(cascade=CascadeType.ALL)
	private Address pickupAddress;
	private Boolean isEmailVerified=Boolean.FALSE;
	private USER_ROLE role=USER_ROLE.ROLE_SELLER;
	private ACCOUNT_STATUS accountStatus=ACCOUNT_STATUS.PENDING_VERIFICATION;
	
	@ManyToOne
	private Product product;
}
