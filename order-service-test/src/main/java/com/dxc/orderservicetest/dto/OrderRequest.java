package com.dxc.orderservicetest.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
	
	
	private Float totalAmount;
	private String customerName;
	private String orderStatus;
	
	private List<OrderLineItemsDto> orderLineItemsDtoList;
	
}
