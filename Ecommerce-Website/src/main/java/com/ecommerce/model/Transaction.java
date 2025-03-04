package com.ecommerce.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	@OneToOne
	private User customer;
	@OneToOne
	private Order order;
	@ManyToOne
	private Seller seller;
	private LocalDateTime transactionDate =LocalDateTime.now();
	

}
