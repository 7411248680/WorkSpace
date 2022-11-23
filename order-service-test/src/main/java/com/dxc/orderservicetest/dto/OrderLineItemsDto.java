package com.dxc.orderservicetest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {

	private Long id;
	private String skuCode;
	private float price;
	private float quantity;
	private Float totalAmount;
}
