package com.ecommerce.model;

import java.util.HashSet;
import java.util.Set;

import com.ecommerce.domain.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@Column(unique=true)
	private String emailId;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private USER_ROLE userRole=USER_ROLE.ROLE_CUSTOMER;
	@OneToMany
	private Set<Address>addresse=new HashSet<>();
	@ManyToMany
	@JsonIgnore
	private Set<Coupon>usedCoupons=new HashSet<>();
}
