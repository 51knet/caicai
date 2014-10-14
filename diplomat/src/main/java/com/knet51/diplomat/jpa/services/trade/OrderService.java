package com.knet51.diplomat.jpa.services.trade;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.UserOrder;
import com.knet51.ccweb.jpa.entities.User;


public interface OrderService {
	UserOrder findOne(Long id);
	UserOrder createOrder(UserOrder order);
	UserOrder updateOrder(UserOrder order);
	Page<UserOrder> findAll(int pageNumber, int pageSize);
	Page<UserOrder> findOrderByUser(int pageNumber, int pageSize, User user);
}
