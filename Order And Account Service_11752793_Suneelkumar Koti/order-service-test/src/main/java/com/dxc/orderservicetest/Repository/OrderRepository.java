package com.dxc.orderservicetest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.orderservicetest.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
