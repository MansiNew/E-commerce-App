package com.ecommerce.model;

import com.ecommerce.domain.PaymentMethod;
import com.ecommerce.domain.PaymentOrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
