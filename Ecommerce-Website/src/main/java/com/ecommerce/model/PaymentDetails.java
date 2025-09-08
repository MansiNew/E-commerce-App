package com.ecommerce.model;

import com.ecommerce.domain.PaymentStatus;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PaymentDetails {
	private String paymentId;
	private String razorPaymentLinkId;
	private String razorPaymentLinkReferenceId;
	private String razorPaymentLinkStatus;
	private String razorPaymentIdZWSP;
	private PaymentStatus paymentStatus;
	

}
