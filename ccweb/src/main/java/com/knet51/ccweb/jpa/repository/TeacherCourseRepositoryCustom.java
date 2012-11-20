package com.knet51.ccweb.jpa.repository;

import java.util.List;

import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

public interface TeacherCourseRepositoryCustom {
	
	TeacherCourse updateTeacherCourseDetail(TeacherCourse teacherCourse);
	List<TeacherCourse> getAllCourseById(Long id);
}
