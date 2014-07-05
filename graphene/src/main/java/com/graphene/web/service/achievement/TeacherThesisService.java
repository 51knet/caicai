package com.graphene.web.service.achievement;

import java.util.List;

import org.springframework.data.domain.Page;

import com.graphene.web.jpa.entity.teacher.TeacherThesis;
import com.graphene.web.jpa.entity.user.User;


public interface TeacherThesisService {
	
	TeacherThesis save(TeacherThesis teacherThesis,User user);

	TeacherThesis update(TeacherThesis teacherThesis);

	TeacherThesis findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherThesis> getAllThesisById(Long Id); 
	
	Page<TeacherThesis> findAllThesisByUser(int pageNum, int pageSize,User user );

}
