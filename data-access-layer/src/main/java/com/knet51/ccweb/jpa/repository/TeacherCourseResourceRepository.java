package com.knet51.ccweb.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;

@Transactional
public interface TeacherCourseResourceRepository extends JpaRepository<CourseResource, Long>, JpaSpecificationExecutor<CourseResource>,TeacherCourseResourceRepositoryCustom{
	//Page<CourseResource> findTeacherCourseResourceByCourse(TeacherCourse course,Pageable pageable);
}
