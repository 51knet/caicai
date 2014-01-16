package com.knet51.ccweb.jpa.repository.course;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;

@Transactional
public interface CourseResourceRepository extends JpaRepository<CourseResource, Long>, JpaSpecificationExecutor<CourseResource>,CourseResourceRepositoryCustom{
	//Page<CourseResource> findTeacherCourseResourceByCourse(TeacherCourse course,Pageable pageable);
	Page<CourseResource> findResourceByUserAndStatusAndForbiddenIsNull(User user,Integer status, Pageable pageable);
	List<CourseResource> findResourceByUserAndStatusAndForbiddenIsNull(User user,Integer status);
	
	/* for super admin */
	Page<CourseResource> findResourceByUserAndStatus(User user,Integer status, Pageable pageable);
	List<CourseResource> findResourceByUserAndStatus(User user,Integer status);
	
	Page<CourseResource> findResourceByStatus(Integer status, Pageable pageable);
	List<CourseResource> findResourceByStatus(Integer status);
}
