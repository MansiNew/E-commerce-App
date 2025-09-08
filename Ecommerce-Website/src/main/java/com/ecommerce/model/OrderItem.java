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
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderItemId;
	@ManyToOne
	@JsonIgnore
	private Order order;
	@ManyToOne
	private Product product;
	private Integer quantity;
	private Double mrpPrice;
	private Double sellingPrice;
	private String size;
	private Long userId;
}
