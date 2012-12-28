package com.knet51.courses.jpa.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.courses.beans.CourseBeans;
import com.knet51.courses.beans.TeacherCourseBeans;

public interface TeacherCourseService {
	TeacherCourse findOneById(Long id);
	TeacherCourse createTeacherCourse(TeacherCourse teacherCourse);
	TeacherCourse updateTeacherCourse(TeacherCourse teacherCourse);
	void deleTeacherCourse(Long teacher_id);
	Page<TeacherCourse> findAllCourseByTeacher(int pageNum, int pageSize, Teacher teacher);
	Page<TeacherCourse> findAllCourse(int pageNum, int pageSize);
	List<String> getAllSchool();
	List<Teacher> getAllCourseTeacher(String schoolName);
	Map<Teacher,List<TeacherCourse>> tcmap();
	List<TeacherCourseBeans> getAllTeacherCourseBeans();
	List<TeacherCourse> findAllCourses();
	List<TeacherCourse> getAllCourseById(Long teacher_id);
	List<String> courseTypeList();
	List<CourseBeans> getAllCourseBeans();
	CourseBeans getCourseBeansById(Long course_id);
	List<String> getCourseTypeByTeacherId(Long teacher_id);
}
