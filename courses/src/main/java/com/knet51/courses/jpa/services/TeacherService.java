package com.knet51.courses.jpa.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.Teacher;

public interface TeacherService {
	Teacher findOne(Long id);
	Teacher createTeacher(Teacher usr);
	Teacher updateTeacher(Teacher usr);
	List<Teacher> findAllTeacher();
	Page<Teacher> findAll(int pageNum, int pageSize);
	List<Teacher> findTeacherByIsEnterprise();
	List<Teacher> findEnterpriseByisEnterprise(String isEnterprise);
	
	Page<Teacher> findAllEnterpriseByisEnterprise(int pageNum, int pageSize, String isEnterprise);
	Page<Teacher> findAllTeacher(int pageNum, int pageSize);
}
