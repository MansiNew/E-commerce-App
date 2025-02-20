package com.ecommerce.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
//not entity
public class BankDetails {
	private String accountNumber;
	private String accountHolderName;
	private String ifscCode;
	private String bankName;
	private String branchNmae;

}
