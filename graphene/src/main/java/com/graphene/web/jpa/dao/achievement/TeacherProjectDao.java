package com.graphene.web.jpa.dao.achievement;

import java.util.List;

import com.graphene.web.jpa.entity.teacher.TeacherProject;


public interface TeacherProjectDao {
	
	TeacherProject save(TeacherProject teacherProject);

	TeacherProject update(TeacherProject teacherProject);

	TeacherProject findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherProject> getAllProjectById(Long Id); 
}
