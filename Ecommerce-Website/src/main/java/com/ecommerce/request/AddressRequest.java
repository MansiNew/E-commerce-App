package com.ecommerce.request;

import lombok.Data;

@Data
public class AddressRequest {
	private String address;
	private String city;
	private String state;
	private String pincode;
	private String street;
	private String mobileNo;
	private String name;
	private String locality;
}
