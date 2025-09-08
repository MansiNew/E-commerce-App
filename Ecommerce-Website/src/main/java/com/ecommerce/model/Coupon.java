package com.ecommerce.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long couponId;
	private String code;
	private Double discountPercentage;
	private LocalDate validityStartDate;
	private LocalDate validityEndDate;
	private Double minOrderValue;
	private Boolean isActive = Boolean.TRUE;
	@ManyToMany(mappedBy = "usedCoupons")
	private Set<User> usedUsers = new HashSet<>();

}
