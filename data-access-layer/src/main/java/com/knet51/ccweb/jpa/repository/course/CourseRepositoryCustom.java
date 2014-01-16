package com.knet51.ccweb.jpa.repository.course;

import java.util.List;


import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.courses.Course;

public interface CourseRepositoryCustom {
	
	Course updateTeacherCourseDetail(Course course);
	List<Course> getAllCourseById(Long teacher_id);
	
	//List<CourseBeans> listAllCourseBeans();
	List<String> getSchool();
	List<Teacher> showCourseTeacher(String schoolName);
	List<String> getCourseType();
	
	List<String> getCourseTypeByTeacherId(Long teacher_id);
}
