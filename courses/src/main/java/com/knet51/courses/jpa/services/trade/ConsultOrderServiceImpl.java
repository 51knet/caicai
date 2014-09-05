package com.knet51.courses.jpa.services.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.consult.ConsultOrder;
import com.knet51.ccweb.jpa.repository.consult.ConsultOrderRepository;
@Service("consultOrderService")
public class ConsultOrderServiceImpl implements ConsultOrderService {
	@Autowired
	private ConsultOrderRepository orderRepository;
	@Override
	public ConsultOrder create(ConsultOrder consultOrder) {
		return orderRepository.saveAndFlush(consultOrder);
	}

	@Override
	public ConsultOrder update(ConsultOrder consultOrder) {
		return orderRepository.saveAndFlush(consultOrder);
	}

	@Override
	public void delete(Long id) {
		orderRepository.delete(id);
	}

	@Override
	public Page<ConsultOrder> findAll(int pageNumber,int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return orderRepository.findAll(pageable);
	}

	@Override
	public Page<ConsultOrder> findAllByTeacher(int pageNumber, int pageSize,
			User teacher) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return orderRepository.findAllByTeacher(pageable, teacher);
	}

	@Override
	public Page<ConsultOrder> findAllByConsulter(int pageNumber, int pageSize,
			User consulter) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return orderRepository.findAllByConsulter(pageable, consulter);
	}

}
