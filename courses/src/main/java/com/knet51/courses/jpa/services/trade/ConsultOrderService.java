package com.knet51.courses.jpa.services.trade;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.consult.ConsultOrder;

public interface ConsultOrderService {
	ConsultOrder create(ConsultOrder consultOrder);
	ConsultOrder update(ConsultOrder consultOrder);
	void delete(Long id);
	Page<ConsultOrder> findAll(int pageNumber,int pageSize);
	Page<ConsultOrder> findAllByTeacher(int pageNumber,int pageSize, User teacher);
	Page<ConsultOrder> findAllByConsulter(int pageNumber, int pageSize, User consulter);
}
