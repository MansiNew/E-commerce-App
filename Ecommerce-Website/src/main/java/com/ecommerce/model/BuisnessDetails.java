package com.ecommerce.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class BuisnessDetails {
	private String buisnessName;
	private String buisnessEmail;
	private String buisnessMobile;
	private String buisnessAddress;
	private String logo;
	private String banner;

}
