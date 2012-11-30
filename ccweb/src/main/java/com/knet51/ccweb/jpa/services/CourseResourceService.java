package com.knet51.ccweb.jpa.services;
import java.util.List;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;

public interface CourseResourceService {
	
	CourseResource findOneById(Long resource_id);
	CourseResource createCourseResource(CourseResource CourseResource);
	CourseResource updateCourseResource(CourseResource CourseResource);
	List<CourseResource> getAllCourseResourceById(Long course_id);
	void deleCourseResource(Long resource_id);
	//Page<CourseResource> findAllCourseResourceByCourse(int pageNum, int pageSize, TeacherCourse course);
	
}
