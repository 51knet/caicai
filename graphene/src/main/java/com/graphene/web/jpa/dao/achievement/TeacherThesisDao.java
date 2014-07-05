package com.graphene.web.jpa.dao.achievement;

import java.util.List;

import com.graphene.web.jpa.entity.teacher.TeacherThesis;


public interface TeacherThesisDao {
	
	TeacherThesis save(TeacherThesis teacherThesis);

	TeacherThesis update(TeacherThesis teacherThesis);

	TeacherThesis findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherThesis> getAllThesisById(Long Id); 
}
