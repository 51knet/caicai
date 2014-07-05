package com.graphene.web.service.achievement;

import java.util.List;

import org.springframework.data.domain.Page;

import com.graphene.web.jpa.entity.teacher.Teacher;
import com.graphene.web.jpa.entity.teacher.TeacherProject;
import com.graphene.web.jpa.entity.user.User;

public interface TeacherProjectService {
	
	TeacherProject save(TeacherProject teacherProject,User user);

	TeacherProject update(TeacherProject teacherProject);

	TeacherProject findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherProject> getAllProjectById(Long Id); 
	
	Page<TeacherProject> findAllProjectByUser(int pageNum, int pageSize, User user);

}
