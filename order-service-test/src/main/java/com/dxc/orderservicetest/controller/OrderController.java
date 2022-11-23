package com.dxc.orderservicetest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.dxc.orderservicetest.dto.OrderRequest;
import com.dxc.orderservicetest.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.dxc.orderservicetest.dto.OrderResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	

	private final OrderService orderService;
	
	@PostMapping("/accountid/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public String placeOrder(@RequestBody OrderRequest orderRequest,@PathVariable Long id) throws JsonMappingException, JsonProcessingException
	{	
		
		return orderService.placeOrder(orderRequest,id);
	}
	
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllProducts() {
        return orderService.getAllItems();        
    }
	
}
