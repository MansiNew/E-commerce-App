package com.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class SellerReport {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sellerReportId;
	@OneToOne
	private Seller seller;
	private Long totalEarnings;
	private Long totalSales;
	private Long totalRefuneds;
	private Long totalTax;
	private Long netEarnings;
	private Integer totalOrders;
	private Integer canceledOrders;
	private Integer totalTransactions;
	
}
