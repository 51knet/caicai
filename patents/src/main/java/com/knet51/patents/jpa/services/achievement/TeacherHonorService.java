package com.knet51.patents.jpa.services.achievement;

import java.util.List;
import org.springframework.data.domain.Page;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;

public interface TeacherHonorService {
	
	TeacherHonor save(TeacherHonor teacherHonor,User user);

	TeacherHonor update(TeacherHonor teacherHonor);

	TeacherHonor findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherHonor> getAllHonorById(Long Id); 
	
	Page<TeacherHonor> findAllHonorByUser(int pageNum, int pageSize, User user);

}
