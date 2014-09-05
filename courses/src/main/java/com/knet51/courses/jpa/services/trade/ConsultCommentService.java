package com.knet51.courses.jpa.services.trade;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.consult.ConsultComment;
import com.knet51.ccweb.jpa.entities.consult.ConsultOrder;

public interface ConsultCommentService {
	ConsultComment findOne(Long id);
	ConsultComment findOneByOrderAndConsulter(User consulter, ConsultOrder order);
	ConsultComment create(ConsultComment comment);
	void deleteComment(Long id);
	Page<ConsultComment> findAllByConsulter(User consulter, int pageNumber, int pageSize);
	Page<ConsultComment> findAllByTeacher(User teacher,  int pageNumber, int pageSize);
	Page<ConsultComment> findAllByOrder(ConsultOrder order,  int pageNumber, int pageSize);
}
