package com.ecommerce.model;

import com.ecommerce.domain.AccountStatus;
import com.ecommerce.domain.USER_ROLE;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
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
	private AccountStatus accountStatus=AccountStatus.PENDING_VERIFICATION;
	
	@ManyToOne
	private Product product;
}
