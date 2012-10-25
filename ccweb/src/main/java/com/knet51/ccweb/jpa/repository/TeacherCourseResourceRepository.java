package com.knet51.ccweb.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

@Transactional
public interface TeacherCourseResourceRepository extends JpaRepository<CourseResource, Long>, JpaSpecificationExecutor<CourseResource>,TeacherCourseResourceRepositoryCustom{
	//Page<CourseResource> findTeacherCourseResourceByCourse(TeacherCourse course,Pageable pageable);
}
