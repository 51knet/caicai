package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

@Transactional
public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Long>, JpaSpecificationExecutor<TeacherCourse>,TeacherCourseRepositoryCustom{
	Page<TeacherCourse> findTeacherCourseByTeacher(Teacher teacher,Pageable pageable);
	Page<TeacherCourse> findAll(Pageable pageable);
	Page<TeacherCourse> findTeacherCourseByTeacherAndPublishGreaterThan(Teacher teacher,Integer publish,Pageable pageable);
	//List<String> getCourseType();
	List<TeacherCourse> findTeacherCourseByStatusAndPublish(Integer status,Integer publish);
	List<TeacherCourse> findTeacherCourseByTeacherAndStatusAndPublish(Teacher teacher,Integer status,Integer publish);
	Page<TeacherCourse> findTeacherCourseByTeacherAndPublish(Teacher teacher,Integer publish,Pageable pageable);
	List<TeacherCourse> findTeacherCourseByTeacherAndPublish(Teacher teacher,Integer publish);
	@Query("select t from TeacherCourse t where t.courseName = :coursename")
	TeacherCourse getTeacherCourseByCourseName(@Param("coursename") String cousername);
}
