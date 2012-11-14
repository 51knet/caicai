package com.knet51.ccweb.jpa.services.teacherAchievement;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;

public interface TeacherThesisService {
	
	TeacherThesis save(TeacherThesis teacherThesis,Teacher teacher);

	TeacherThesis update(TeacherThesis teacherThesis);

	TeacherThesis findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherThesis> getAllThesisById(Long Id); 
	
	Page<TeacherThesis> findAllThesisByTeacher(int pageNum, int pageSize, Teacher teacher );

}
