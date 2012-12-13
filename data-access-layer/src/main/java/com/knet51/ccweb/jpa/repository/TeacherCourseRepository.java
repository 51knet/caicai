package com.knet51.ccweb.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

@Transactional
public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Long>, JpaSpecificationExecutor<TeacherCourse>,TeacherCourseRepositoryCustom{
	Page<TeacherCourse> findTeacherCourseByTeacher(Teacher teacher,Pageable pageable);
	Page<TeacherCourse> findAll(Pageable pageable);
}
