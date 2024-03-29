package com.dxc.orderservicetest.service;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dxc.orderservicetest.Repository.OrderItemRepository;
import com.dxc.orderservicetest.Repository.OrderRepository;
import com.dxc.orderservicetest.common.Account;
import com.dxc.orderservicetest.dto.OrderLineItemsDto;
import com.dxc.orderservicetest.dto.OrderRequest;
import com.dxc.orderservicetest.dto.OrderResponse;
import com.dxc.orderservicetest.exception.AccountBalanceException;
import com.dxc.orderservicetest.model.Order;
import com.dxc.orderservicetest.model.OrderLineItems;
import com.dxc.orderservicetest.util.EmailDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private static final String getAcc = "http://localhost:8082/api/billing/";
	private static final String updateAccBalance= "http://localhost:8082/api/billing/update/{id}";
	static RestTemplate restTemplate=new RestTemplate();
	
	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	
	@Autowired
	private EmailService1 emailService;
	
	private static String success="sucess";
	private static String failed="failed";
	private static String orderstatus="";
	
	public String placeOrder(OrderRequest orderRequest,Long id) throws JsonMappingException, JsonProcessingException
	{
		HttpHeaders headers =new  HttpHeaders();
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity= new HttpEntity<>(headers);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(getAcc+id, HttpMethod.GET, entity, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		
		Account account = mapper.readValue(responseEntity.getBody().toString(), Account.class);		
		

		Order order=new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss aa");
		//Setting the time zone
		dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		order.setDate(dateTimeInGMT.format(new Date()));
		order.setTxn_id(UUID.randomUUID().toString());
				
		
		List<OrderLineItems> orderLineItems= orderRequest.getOrderLineItemsDtoList()
		.stream()
		.map(this::mapToDto)
		.collect(Collectors.toList());
		
		order.setCustomerName(orderRequest.getCustomerName());
		
		for (OrderLineItems orderLineItems2 : orderLineItems) {
			if(orderLineItems2.getTotalAmount()<=account.getBalance())
			{
			Float totalBalanceInAcccount= account.getBalance()-orderLineItems2.getTotalAmount();	

			updateAccBalance(totalBalanceInAcccount);
			order.setOrderStatus(success);
			 orderstatus="Order Placed Successfull";
			 
			 String body = "Hi,"+"\n"+order.getCustomerName()+"\n\n"+"Order details\n\n" +
                     "Order number : " + order.getOrderNumber()+
                     "\nTotal Amount : " + orderLineItems2.getTotalAmount()+
//                     "\nEmail : "+ save.getEmail() +
                     "\n\n\nThanks and Regards,\nSuneelKumar & Team";
    emailService.sendSimpleMail(new EmailDetails("kotisuneelkumar@gmail.com", body, "Order Created Succefully!"));
			 			 
			}
			else
			{
				order.setOrderStatus(failed);
				throw new AccountBalanceException("No Enough Balance");
				//orderstatus="Order Failed";
			}
		}
		
		order.setOrderLineItems(orderLineItems);
		orderRepository.save(order);
		
		return orderstatus;
			
	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		
		OrderLineItems orderLineItems=new  OrderLineItems();
		
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		orderLineItems.setTotalAmount(orderLineItemsDto.getPrice()*orderLineItemsDto.getQuantity());
		
		return orderLineItems;
	}
	
	
    public List<OrderResponse> getAllItems() {
        List<OrderLineItems> orderLineItems = orderItemRepository.findAll();
        
        
        
        return  orderLineItems.stream().map(this::mapToOrderResponse).collect(Collectors.toList());
    }
	
    private OrderResponse mapToOrderResponse(OrderLineItems orderLineItems) {
    //	System.out.println(orderLineItems.getTotalAmount()+"test");
    	
        return OrderResponse.builder()
                .id(orderLineItems.getId())
                .skuCode(orderLineItems.getSkuCode())
                .price(orderLineItems.getPrice())
                .quantity(orderLineItems.getQuantity())
                .totalAmount(orderLineItems.getTotalAmount())
                .build();
    }
    
    private void updateAccBalance(float remainBalance) {
        Map < String, String > params = new HashMap < String, String > ();
        params.put("id", "2");
        Account updatedBalance = new Account();
        updatedBalance.setBalance(remainBalance);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(updateAccBalance, updatedBalance, params);
    }    

	
}
