package com.knet51.ccweb.jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.knet51.ccweb.jpa.entities.UserOrder;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.repository.OrderRepository;

@Transactional
@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public UserOrder findOne(Long id) {
		return orderRepository.findOne(id);
	}

	@Override
	public UserOrder createOrder(UserOrder order) {
		// TODO Auto-generated method stub
		return orderRepository.save(order);
	}

	@Override
	public UserOrder updateOrder(UserOrder order) {
		// TODO Auto-generated method stub
		return orderRepository.save(order);
	}

	@Override
	public Page<UserOrder> findAll(int pageNumber, int pageSize) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<UserOrder> onePage = orderRepository.findAll(dateDesc);
		return onePage;
	}

	@Override
	public Page<UserOrder> findOrderByUser(int pageNumber, int pageSize, User user) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<UserOrder> onePage = orderRepository.findOrderByUser(user, pageable);
		return onePage;
	}
	
}
