package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.knet51.ccweb.beans.CourseBeans;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

public interface TeacherCourseService {
	
	TeacherCourse findOneById(Long id);
	TeacherCourse createTeacherCourse(TeacherCourse teacherCourse);
	TeacherCourse updateTeacherCourse(TeacherCourse teacherCourse);
	List<TeacherCourse> getAllTeacherCourseByTeacheridAndPublish(Long teacher_id,Integer Publish);
	void deleTeacherCourse(Long course_id);
	Page<TeacherCourse> findAllCourseByTeacher(int pageNum, int pageSize, Teacher teacher);
	//List<CourseBeans> getAllCourseBeans();
	List<String> getAllSchool();
	List<Teacher> getAllCourseTeacher(String schoolName);
	Page<TeacherCourse> findTeacherCourseByTeacherAndPublish(int pageNum, int pageSize, Teacher teacher,Integer publish);
	
	Page<TeacherCourse> findTeacherCourseByTeacherAndPublishGreaterThan(int pageNum, int pageSize,Teacher teacher,Integer publish);
	TeacherCourse getTeacherCourseByCourseName(String cousername);
}
