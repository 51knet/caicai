package com.knet51.ccweb.jpa.dao.teacherAchievement;

import java.util.List;

import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;

public interface TeacherThesisDao {
	
	TeacherThesis save(TeacherThesis teacherThesis);

	TeacherThesis update(TeacherThesis teacherThesis);

	TeacherThesis findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherThesis> getAllThesisById(Long Id); 
}
