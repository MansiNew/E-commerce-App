package com.ecommerce.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long reviewId;
	@Column(nullable=false)
	private String reviewMessage;
	@Column(nullable=false)
	private Double rating;
	@JoinColumn(name ="user_id",nullable=false,referencedColumnName = "userId")
	@ManyToOne
	private User user;
	@JsonIgnore
	@JoinColumn(name ="product_id",nullable=false,referencedColumnName = "productId")
	@ManyToOne
	private Product product;
	//@ElementCollection
	//@CollectionTable(name = "product_images")
	//private List<String>productImages=new ArrayList<>();
	@Column(nullable=false)
	private LocalDateTime creationDate=LocalDateTime.now();
}
