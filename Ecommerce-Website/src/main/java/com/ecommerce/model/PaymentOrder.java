package com.ecommerce.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ecommerce.domain.PaymentMethod;
import com.ecommerce.domain.PaymentOrderStatus;

import lombok.Data;

@Entity
@Data
public class PaymentOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long paymentOrderId;
	private Double amount;
	private String paymentLinkId;
	private PaymentMethod paymentMethod;
	private PaymentOrderStatus paymentOrderStatus=PaymentOrderStatus.PENDING;
	//one user can do many oredrs payment
	@ManyToOne
	private User user;
	//ex. we have 2 product of difrent brand and sell by diffrenet seller ,so We have to do saperate payment for both products
	@ManyToOne
	private Order orders;
	
}
