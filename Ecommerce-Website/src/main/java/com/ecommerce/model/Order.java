package com.ecommerce.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.ecommerce.domain.OrderStatus;
import com.ecommerce.domain.PaymentStatus;

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
	//private PaymentStatus paymentStatus=PaymentStatus.PENDING;

	private LocalDateTime orderDate=LocalDateTime.now();
	private LocalDateTime deliveredDate=orderDate.plusDays(7);
	private Long sellerId;
	
	
	
	//private Long sellerId;
	
}
