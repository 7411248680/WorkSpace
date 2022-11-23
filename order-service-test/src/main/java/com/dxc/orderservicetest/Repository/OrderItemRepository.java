package com.dxc.orderservicetest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.orderservicetest.model.OrderLineItems;

public interface OrderItemRepository extends JpaRepository<OrderLineItems, Long> {

}
