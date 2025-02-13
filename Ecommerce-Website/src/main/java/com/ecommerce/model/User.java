package com.ecommerce.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ecommerce.domain.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String emailId;
	private String firstName;
	private String lastNmae;
	private String mobileNo;
	private USER_ROLE userRole=USER_ROLE.ROLE_CUSTOMER;
//	private Set<Address>addresse=new HashSet();
	//private Set<Coupon>usedCoupons=new HashSet<>();
}
