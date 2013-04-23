package com.knet51.ccweb.jpa.services.achievement;

import java.util.List;
import org.springframework.data.domain.Page;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;

public interface TeacherHonorService {
	
	TeacherHonor save(TeacherHonor teacherHonor,Teacher teacher);

	TeacherHonor update(TeacherHonor teacherHonor);

	TeacherHonor findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherHonor> getAllHonorById(Long Id); 
	
	Page<TeacherHonor> findAllHonorByTeacher(int pageNum, int pageSize, Teacher teacher);

}
