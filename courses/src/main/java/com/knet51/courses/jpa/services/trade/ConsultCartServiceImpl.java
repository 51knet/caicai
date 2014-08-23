package com.knet51.courses.jpa.services.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.teacher.ConsultCart;
import com.knet51.ccweb.jpa.repository.ConsultCartRepository;
@Service("consultCartService")
public class ConsultCartServiceImpl implements ConsultCartService {
	@Autowired
	private ConsultCartRepository cartRepository;
	@Override
	public ConsultCart create(ConsultCart consultCart) {
		return cartRepository.saveAndFlush(consultCart);
	}

	@Override
	public ConsultCart update(ConsultCart consultCart) {
		return cartRepository.saveAndFlush(consultCart);
	}

	@Override
	public void delete(Long id) {
		cartRepository.delete(id);
	}

	@Override
	public Page<ConsultCart> findAll(int pageNumber,int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return cartRepository.findAll(pageable);
	}

}
