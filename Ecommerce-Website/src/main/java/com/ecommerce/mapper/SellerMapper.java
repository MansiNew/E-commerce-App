package com.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.ecommerce.model.Seller;
import com.ecommerce.request.SellerRequest;
import com.ecommerce.response.SellerResponse;

@Mapper(componentModel = "spring")
public interface SellerMapper {

	// Convert Seller Entity to SellerDTO

	Seller sellerRequestToSeller(SellerRequest seller);

	// Convert SellerDTO to Seller Entity

	public SellerResponse sellerToSellerResponse(Seller seller);
}