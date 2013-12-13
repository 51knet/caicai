package com.knet51.ccweb.jpa.services.course;
import java.util.List;

import com.knet51.ccweb.jpa.entities.courses.CourseResource;

public interface CourseResourceService {
	
	CourseResource findOneById(Long resource_id);
	CourseResource createCourseResource(CourseResource CourseResource);
	CourseResource updateCourseResource(CourseResource CourseResource);
	List<CourseResource> getAllCourseResourceByCourseIdAndStatus(Long course_id,Integer status);
	List<CourseResource> getResourceByLessonNumAndCourseId(int lessonNum,Long course_id);
	void deleCourseResource(Long resource_id);
	int getMaxLessonNumByCourseId(Long course_id);
	
	//Page<CourseResource> findAllCourseResourceByCourse(int pageNum, int pageSize, TeacherCourse course);
	
}
