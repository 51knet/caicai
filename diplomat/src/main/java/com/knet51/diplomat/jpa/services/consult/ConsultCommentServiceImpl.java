package com.knet51.diplomat.jpa.services.consult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.consult.ConsultComment;
import com.knet51.ccweb.jpa.entities.consult.ConsultOrder;
import com.knet51.ccweb.jpa.repository.consult.ConsultCommentRepository;
@Service("consultCommentService")
public class ConsultCommentServiceImpl implements ConsultCommentService {
	@Autowired
	private ConsultCommentRepository commentRepository;
	@Override
	public ConsultComment findOne(Long id) {
		return commentRepository.findOne(id);
	}

	@Override
	public ConsultComment create(ConsultComment comment) {
		return commentRepository.saveAndFlush(comment);
	}

	@Override
	public void deleteComment(Long id) {
		commentRepository.delete(id);
	}

	@Override
	public Page<ConsultComment> findAllByConsulter(User consulter,
			int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return commentRepository.findAllByConsulter(consulter, pageable);
	}

	@Override
	public Page<ConsultComment> findAllByTeacher(User teacher,
			int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return commentRepository.findAllByTeacher(teacher, pageable);
	}

	@Override
	public Page<ConsultComment> findAllByOrder(ConsultOrder order,
			int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return commentRepository.findAllByOrder(order, pageable);
	}

	@Override
	public ConsultComment findOneByOrderAndConsulter(User consulter,
			ConsultOrder order) {
		return commentRepository.findByConsulterAndOrder(consulter, order);
	}

}
