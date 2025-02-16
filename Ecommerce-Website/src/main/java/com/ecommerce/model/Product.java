package com.ecommerce.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	private Integer discountPercent;
	private Double sellingPrice;
	private Double mrpPrice;
	private String title;
	private String description;
	private Integer quantity;
	private String color;
	private String numRatings;
	// @ElementCollection ,create saperate table for images
	@ElementCollection
	private List<String> images = new ArrayList<>();
	@ManyToOne
	private Category category;
	@ManyToOne
	private Seller seller;
	private LocalDate createdDate;

}
