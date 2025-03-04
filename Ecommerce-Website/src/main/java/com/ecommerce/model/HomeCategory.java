package com.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ecommerce.domain.HomeCategorySection;

import lombok.Data;

@Entity
@Data
public class HomeCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String categoryId;
	private String name;
	private String image;
	private HomeCategorySection section;
}
