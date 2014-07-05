package com.graphene.web.service.achievement;

import java.util.List;

import org.springframework.data.domain.Page;

import com.graphene.web.jpa.entity.teacher.Teacher;
import com.graphene.web.jpa.entity.teacher.TeacherPatent;
import com.graphene.web.jpa.entity.user.User;

public interface TeacherPatentService {
	
	TeacherPatent save(TeacherPatent teacherPatent,User user);

	TeacherPatent update(TeacherPatent teacherPatent);

	TeacherPatent findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherPatent> getAllPatentById(Long Id); 
	
	Page<TeacherPatent> findAllPatentByUser(int pageNum, int pageSize, User user);
}
