package com.knet51.ccweb.jpa.repository;

import java.util.List;


import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.courses.TeacherCourse;

public interface TeacherCourseRepositoryCustom {
	
	TeacherCourse updateTeacherCourseDetail(TeacherCourse teacherCourse);
	List<TeacherCourse> getAllCourseById(Long teacher_id);
	
	//List<CourseBeans> listAllCourseBeans();
	List<String> getSchool();
	List<Teacher> showCourseTeacher(String schoolName);
	List<String> getCourseType();
	
	List<String> getCourseTypeByTeacherId(Long teacher_id);
}
