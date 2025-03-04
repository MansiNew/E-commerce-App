package com.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Deal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dealId;
	private Integer discount;
	//discount is giving on which category
	@OneToOne
	private HomeCategory homeCategory;
}
