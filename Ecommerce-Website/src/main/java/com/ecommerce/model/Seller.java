package com.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
@Entity
@Data
public class Seller {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sellerId;
	@ManyToOne
	private Product product;
}
