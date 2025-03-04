package com.ecommerce.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class WishList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long wishListId;
	@OneToOne
	private User user;
	//wishlist can havemany product
	@ManyToMany
	private Set<Product> producr=new HashSet<>();
	
}
