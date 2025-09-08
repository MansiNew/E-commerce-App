package com.ecommerce.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.domain.OrderStatus;
import com.ecommerce.domain.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="ORDER_TABLE")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ordId;
	private String orderId;
	private Double totalMrpPrice;
	private Double totalSellingPrice;
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<OrderItem>orderItems=new ArrayList<>();
	@ManyToOne
	private User user;
	@ManyToOne
	private Address shippingAddress;
	@Embedded
	private PaymentDetails paymentDetails;
	private Double discount;
	private Integer totalItem;
	private OrderStatus orderStatus;
	//@Enumerated(EnumType.STRING)
	//@Column(name = "payment_status", insertable = false, updatable = false)
	private PaymentStatus orderPaymentStatus=PaymentStatus.PENDING;

	private LocalDateTime orderDate=LocalDateTime.now();
	private LocalDateTime deliveredDate=orderDate.plusDays(7);
	private Long sellerId;
	
	
	
	//private Long sellerId;
	
}
