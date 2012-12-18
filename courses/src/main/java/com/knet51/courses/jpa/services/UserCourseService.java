package com.knet51.courses.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.UserCourse;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

public interface UserCourseService {
	UserCourse create(UserCourse userCourse);
	
	List<TeacherCourse> getCourseByUserId(Long user_id);
}
