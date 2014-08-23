package com.knet51.courses.jpa.services.trade;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.teacher.ConsultCart;

public interface ConsultCartService {
	ConsultCart create(ConsultCart consultCart);
	ConsultCart update(ConsultCart consultCart);
	void delete(Long id);
	Page<ConsultCart> findAll(int pageNumber,int pageSize);
}
