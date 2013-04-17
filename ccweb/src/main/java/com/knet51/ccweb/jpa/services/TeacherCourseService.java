package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.knet51.ccweb.beans.CourseBeans;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseType;
import com.knet51.ccweb.jpa.entities.courses.TeacherCourse;

public interface TeacherCourseService {
	Page<TeacherCourse> findAllCourse(int pageNum, int pageSize);
	TeacherCourse findOneById(Long id);
	TeacherCourse createTeacherCourse(TeacherCourse teacherCourse);
	TeacherCourse updateTeacherCourse(TeacherCourse teacherCourse);
	List<TeacherCourse> getAllTeacherCourseByTeacheridAndPublish(Long teacher_id,Integer Publish);
	void deleTeacherCourse(Long course_id);
	Page<TeacherCourse> findAllCourseByUser(int pageNum, int pageSize, User user);
	//List<CourseBeans> getAllCourseBeans();
	List<String> getAllSchool();
	List<Teacher> getAllCourseTeacher(String schoolName);
	Page<TeacherCourse> findTeacherCourseByUserAndPublish(int pageNum, int pageSize, User user,Integer publish);
	Page<TeacherCourse> findTeacherCourseByUserAndPublishGreaterThan(int pageNum, int pageSize,User user,Integer publish);
	TeacherCourse getTeacherCourseByCourseName(String cousername,Long teacherId);
	
	Page<TeacherCourse> findTeacherCourseByUserAndPublishAndCType(int pageNum, int pageSize, User user,Integer publish,CourseType cType);
}
