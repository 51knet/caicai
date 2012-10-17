package com.knet51.ccweb.jpa.services.teacherAchievement;

import java.util.List;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;

public interface TeacherThesisService {
	
	TeacherThesis save(TeacherThesis teacherThesis,Teacher teacher);

	TeacherThesis update(TeacherThesis teacherThesis);

	TeacherThesis findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherThesis> getAllThesisById(Long Id); 

}
