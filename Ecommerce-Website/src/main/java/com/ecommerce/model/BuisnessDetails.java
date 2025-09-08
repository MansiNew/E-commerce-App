package com.ecommerce.model;

import lombok.Data;

@Data
@jakarta.persistence.Embeddable
public class BuisnessDetails {
	private String buisnessName;
	private String buisnessEmail;
	private String buisnessMobile;
	private String buisnessAddress;
	private String logo;
	private String banner;

}
