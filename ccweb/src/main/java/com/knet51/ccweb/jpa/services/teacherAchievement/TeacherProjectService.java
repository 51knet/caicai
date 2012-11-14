package com.knet51.ccweb.jpa.services.teacherAchievement;

import java.util.List;

import org.springframework.data.domain.Page;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;

public interface TeacherProjectService {
	
	TeacherProject save(TeacherProject teacherProject,Teacher teacher);

	TeacherProject update(TeacherProject teacherProject);

	TeacherProject findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherProject> getAllProjectById(Long Id); 
	
	Page<TeacherProject> findAllProjectByTeacher(int pageNum, int pageSize, Teacher teacher);

}
