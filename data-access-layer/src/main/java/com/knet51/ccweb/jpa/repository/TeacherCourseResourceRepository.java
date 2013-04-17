package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;

@Transactional
public interface TeacherCourseResourceRepository extends JpaRepository<CourseResource, Long>, JpaSpecificationExecutor<CourseResource>,TeacherCourseResourceRepositoryCustom{
	//Page<CourseResource> findTeacherCourseResourceByCourse(TeacherCourse course,Pageable pageable);
	Page<CourseResource> findResourceByUserAndStatus(User user,Integer status, Pageable pageable);
	List<CourseResource> findResourceByUserAndStatus(User user,Integer status);
}
