package com.example.orderservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.exception.AccountBalanceException;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@RequiredArgsConstructor
@Builder
public class OrderService {
	
	private static final Logger LOG = LoggerFactory.getLogger(Order.class);

    private final OrderRepository orderRepository;
   
	public void createOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderNumber(orderRequest.getOrderNumber())
                .itemName(orderRequest.getItemName())
                .price(orderRequest.getPrice())
                .build();

        orderRepository.save(order);
        LOG.debug("Product {} is saved", order.getId());
    }

	
    public List<OrderResponse> getAllOrders() {
        List<Order> order = orderRepository.findAll();

        return  order.stream().map(this::mapToOrderResponse).collect(Collectors.toList());
    }

    
    private OrderResponse mapToOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .itemName(order.getItemName())
                .price(order.getPrice())
                .build();
    }
    


	

}