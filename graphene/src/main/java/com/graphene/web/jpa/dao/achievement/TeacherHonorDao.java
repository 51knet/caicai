package com.graphene.web.jpa.dao.achievement;

import java.util.List;

import com.graphene.web.jpa.entity.teacher.TeacherHonor;


public interface TeacherHonorDao {
	
	TeacherHonor save(TeacherHonor teacherHonor);

	TeacherHonor update(TeacherHonor teacherHonor);

	TeacherHonor findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherHonor> getAllHonorById(Long Id); 
}
