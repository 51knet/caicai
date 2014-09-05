package com.knet51.ccweb.jpa.repository.consult;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.consult.ConsultOrder;

public interface ConsultOrderRepository extends JpaRepository<ConsultOrder, Long>,JpaSpecificationExecutor<ConsultOrder> {
	Page<ConsultOrder> findAllByTeacher(Pageable pageable, User teacher);
	Page<ConsultOrder> findAllByConsulter(Pageable pageable, User consulter);
}
