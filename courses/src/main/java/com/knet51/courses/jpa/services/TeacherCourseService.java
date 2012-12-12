package com.knet51.courses.jpa.services;

import java.util.List;
import java.util.Map;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.courses.beans.TeacherCourseBeans;

public interface TeacherCourseService {
	
	List<String> getAllSchool();
	
	List<Teacher> getAllCourseTeacher(String schoolName);
	
	Map<Teacher,List<TeacherCourse>> tcmap();
	
	List<TeacherCourseBeans> getAllTeacherCourseBeans();
	
	List<TeacherCourse> findAllCourses();
	
	List<String> courseTypeList();
}
