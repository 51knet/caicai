package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.EnterpriseTeacher;
import com.knet51.ccweb.jpa.entities.User;
@Transactional
public interface EnterpriseTeacherRepository  extends JpaRepository<EnterpriseTeacher, Long>, JpaSpecificationExecutor<EnterpriseTeacher>{
	
	Page<EnterpriseTeacher> findTeacherByUser(User enterprise, Pageable pageable);
	List<EnterpriseTeacher> findTeacherByUser(User enterprise);
}
