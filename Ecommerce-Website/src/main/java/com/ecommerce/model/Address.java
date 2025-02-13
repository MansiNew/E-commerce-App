package com.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Address {
	@Id
	private Long id;
}
