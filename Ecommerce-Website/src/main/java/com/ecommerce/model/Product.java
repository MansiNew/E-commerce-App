package com.ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	private LocalDateTime createdDate;
	private String sizes;
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Review> reviews=new ArrayList<>();
	

}
