package com.ecommerce.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ecommerce.domain.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartId;
	@OneToOne
	private User user;
	private Integer totalItem;
	private Double totalSellingPrice;
	private String couponCode;
	private Double totalMrpPrice;
	private Double dicount;
	// orphanRemoval ,whenever remove any cart item from cartItem table it will
	// remove from set also

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CartItems> cartItems = new HashSet<>();

}
