package com.knet51.ccweb.jpa.services.teacherAchievement;

import java.util.List;

import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;

public interface TeacherHonorService {
	
	TeacherHonor save(TeacherHonor teacherHonor);

	TeacherHonor update(TeacherHonor teacherHonor);

	TeacherHonor findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherHonor> getAllHonorById(Long Id); 

}
