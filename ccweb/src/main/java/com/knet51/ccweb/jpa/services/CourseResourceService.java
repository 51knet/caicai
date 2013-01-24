package com.knet51.ccweb.jpa.services;
import java.util.List;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;

public interface CourseResourceService {
	
	CourseResource findOneById(Long resource_id);
	CourseResource createCourseResource(CourseResource CourseResource);
	CourseResource updateCourseResource(CourseResource CourseResource);
	List<CourseResource> getAllCourseResourceById(Long course_id);
	List<CourseResource> getResourceByCourseId(Long course_id);
	List<CourseResource> getResourceByLessonNumAndCourseId(String lessonNum,Long course_id);
	void deleCourseResource(Long resource_id);
	String getMaxLessonNumByCourseId(Long course_id);
	List<CourseResource> findNullResourceByCourseIdAndLessonNum(Long course_id,String lessonNum);
	//Page<CourseResource> findAllCourseResourceByCourse(int pageNum, int pageSize, TeacherCourse course);
	
}
