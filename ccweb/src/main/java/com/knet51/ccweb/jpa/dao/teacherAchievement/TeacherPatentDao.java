package com.knet51.ccweb.jpa.dao.teacherAchievement;

import java.util.List;

import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;

public interface TeacherPatentDao {
	
	TeacherPatent save(TeacherPatent teacherPatent);

	TeacherPatent update(TeacherPatent teacherPatent);

	TeacherPatent findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherPatent> getAllPatentById(Long Id); 
}
