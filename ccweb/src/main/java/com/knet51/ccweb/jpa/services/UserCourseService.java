package com.knet51.ccweb.jpa.services;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.UserCourse;

public interface UserCourseService {
	Page<UserCourse> findUserCourseByTeachercourseid(int pageNumber, int pageSize,Long teacherCourse_id);
	List<UserCourse> findByTeachercourseid(Long teacherCourseId);
	UserCourse save(UserCourse userCourse);
	List<UserCourse> findUserCourseByUserid(Long user_id);
	UserCourse findByTeachercourseidAndUserid( Long teachercourseid,Long userid);
	Double getMark(Long teacherCourseId);
	public User findByUserId(Long id);
	UserCourse update(UserCourse userCourse);
	
	Page<UserCourse> findByUserid(int pageNumber, int pageSize,Long user_id);
}
