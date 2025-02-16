package com.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long addressId;
	private String address;
	private String city;
	private String state;
	private String pincode;
	private String street;
	private String mobileNo;
	private String name;
	private String locality;
	
}
