package com.dxc.orderservicetest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
	
	private Long id;
	private String skuCode;
	private float price;
	private float quantity;
	private Float totalAmount;
	
}
