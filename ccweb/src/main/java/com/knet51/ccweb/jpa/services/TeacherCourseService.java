package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.beans.CourseBeans;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

public interface TeacherCourseService {
	
	TeacherCourse findOneById(Long id);
	TeacherCourse createTeacherCourse(TeacherCourse teacherCourse);
	TeacherCourse updateTeacherCourse(TeacherCourse teacherCourse);
	List<TeacherCourse> getAllTeacherCourseById(Long teacher_id);
	void deleTeacherCourse(Long teacher_id);
	Page<TeacherCourse> findAllCourseByTeacher(int pageNum, int pageSize, Teacher teacher);
	
	List<CourseBeans> getAllCourseBeans(Long teacher_id);
	List<String> getAllSchool();
	
}
