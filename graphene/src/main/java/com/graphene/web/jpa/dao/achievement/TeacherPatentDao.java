package com.graphene.web.jpa.dao.achievement;

import java.util.List;

import com.graphene.web.jpa.entity.teacher.TeacherPatent;


public interface TeacherPatentDao {
	
	TeacherPatent save(TeacherPatent teacherPatent);

	TeacherPatent update(TeacherPatent teacherPatent);

	TeacherPatent findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherPatent> getAllPatentById(Long Id); 
}
