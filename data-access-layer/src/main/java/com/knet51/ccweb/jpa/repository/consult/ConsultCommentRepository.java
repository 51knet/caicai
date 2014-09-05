package com.knet51.ccweb.jpa.repository.consult;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.consult.ConsultComment;
import com.knet51.ccweb.jpa.entities.consult.ConsultOrder;

public interface ConsultCommentRepository extends JpaRepository<ConsultComment, Long>,JpaSpecificationExecutor<ConsultComment> {
	Page<ConsultComment> findAllByConsulter(User consulter, Pageable pageable);
	Page<ConsultComment> findAllByTeacher(User teacher, Pageable pageable);
	Page<ConsultComment> findAllByOrder(ConsultOrder order, Pageable pageable);
	ConsultComment findByConsulterAndOrder(User consulter, ConsultOrder order);
}
