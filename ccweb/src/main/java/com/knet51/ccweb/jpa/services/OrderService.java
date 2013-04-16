package com.knet51.ccweb.jpa.services;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.Order;
import com.knet51.ccweb.jpa.entities.User;


public interface OrderService {
	Order findOne(Long id);
	Order createOrder(Order order);
	Order updateOrder(Order order);
	Page<Order> listAll(int pageNumber, int pageSize);
	Page<Order> findAllbyUser(int pageNumber, int pageSize, User user);
}
