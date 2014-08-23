package com.knet51.courses.jpa.services.achievement;

import java.util.List;

import org.springframework.data.domain.Page;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;

public interface TeacherPatentService {
	
	TeacherPatent save(TeacherPatent teacherPatent,Teacher teacher);

	TeacherPatent update(TeacherPatent teacherPatent);

	TeacherPatent findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherPatent> getAllPatentById(Long Id); 
	
	Page<TeacherPatent> findAllPatentByTeacher(int pageNum, int pageSize, Teacher teacher);
}
