package com.knet51.ccweb.jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.knet51.ccweb.jpa.entities.Order;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.repository.OrderRepository;

@Transactional
@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order findOne(Long id) {
		return orderRepository.findOne(id);
	}

	@Override
	public Order createOrder(Order order) {
		// TODO Auto-generated method stub
		return orderRepository.save(order);
	}

	@Override
	public Order updateOrder(Order order) {
		// TODO Auto-generated method stub
		return orderRepository.save(order);
	}

	@Override
	public Page<Order> listAll(int pageNumber, int pageSize) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<Order> onePage = orderRepository.findAll(dateDesc);
		return onePage;
	}

	@Override
	public Page<Order> findAllbyUser(int pageNumber, int pageSize, User user) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<Order> onePage = orderRepository.findByUser(user, dateDesc);
		return onePage;
	}
	
}
