package com.knet51.patents.jpa.services.course;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseType;
import com.knet51.ccweb.jpa.entities.courses.Course;

public interface CourseService {
	Page<Course> findAllCourse(int pageNum, int pageSize);
	Course findOneById(Long id);
	Course createTeacherCourse(Course course);
	Course updateTeacherCourse(Course course);
	List<Course> getAllTeacherCourseByUseridAndPublish(Long teacher_id,Integer Publish);
	void deleTeacherCourse(Long course_id);
	Page<Course> findAllCourseByUser(int pageNum, int pageSize, User user);
	//List<CourseBeans> getAllCourseBeans();
	List<String> getAllSchool();
	List<Teacher> getAllCourseTeacher(String schoolName);
	Page<Course> findTeacherCourseByUserAndPublish(int pageNum, int pageSize, User user,Integer publish);
	Page<Course> findTeacherCourseByUserAndPublishGreaterThan(int pageNum, int pageSize,User user,Integer publish);
	Course getTeacherCourseByCourseName(String cousername,Long teacherId);
	
	Page<Course> findTeacherCourseByUserAndPublishAndCType(int pageNum, int pageSize, User user,Integer publish,CourseType cType);
	
	List<Course> findAllPublish();
	
	/*  for super Admin */
	List<Course> findCourseByUserAndPublishGreaterThanForSuperAdmin(User user,Integer publish);
	Page<Course> findCourseByUserAndPublishGreaterThanForSuperAdmin(User user,Integer publish,int pageNum, int pageSize);
	Page<Course> findCourseByPublishGreaterThanForSuperAdmin(Integer publish,int pageNum, int pageSize);
	List<Course> findAllForAdmin();
}
