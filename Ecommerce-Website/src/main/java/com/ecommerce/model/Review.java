package com.ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
