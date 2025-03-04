package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
