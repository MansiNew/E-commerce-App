package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartItemId;
	// not need this cart data in UI
	@ManyToOne
	@JsonIgnore
	private Cart cart;
	private Integer quantity;
	private String size;
	private Double sellingPrice;
	private Double mrpPrice;
	@ManyToOne
	private Product product;
	//private Long userId;
}
