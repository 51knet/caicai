package com.knet51.ccweb.jpa.dao.teacherAchievement;

import java.util.List;

import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;

public interface TeacherProjectDao {
	
	TeacherProject save(TeacherProject teacherProject);

	TeacherProject update(TeacherProject teacherProject);

	TeacherProject findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherProject> getAllProjectById(Long Id); 
}
