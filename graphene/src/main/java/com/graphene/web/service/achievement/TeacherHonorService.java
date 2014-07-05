package com.graphene.web.service.achievement;

import java.util.List;
import org.springframework.data.domain.Page;

import com.graphene.web.jpa.entity.teacher.TeacherHonor;
import com.graphene.web.jpa.entity.user.User;

public interface TeacherHonorService {
	
	TeacherHonor save(TeacherHonor teacherHonor,User user);

	TeacherHonor update(TeacherHonor teacherHonor);

	TeacherHonor findOneById(Long id);

	void deleteById(Long id);
	
	List<TeacherHonor> getAllHonorById(Long Id); 
	
	Page<TeacherHonor> findAllHonorByUser(int pageNum, int pageSize, User user);

}
