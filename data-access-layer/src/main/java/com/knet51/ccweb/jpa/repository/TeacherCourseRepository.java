package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseType;
import com.knet51.ccweb.jpa.entities.courses.TeacherCourse;

@Transactional
public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Long>, JpaSpecificationExecutor<TeacherCourse>,TeacherCourseRepositoryCustom{
	Page<TeacherCourse> findTeacherCourseByUser(User user,Pageable pageable);
	Page<TeacherCourse> findAll(Pageable pageable);
	Page<TeacherCourse> findTeacherCourseByUserAndForbiddenIsNullAndPublishGreaterThan(User user,Integer publish,Pageable pageable);
	//List<String> getCourseType();
	List<TeacherCourse> findTeacherCourseByStatusAndPublishAndForbiddenIsNull(Integer status,Integer publish,Sort sort);
	List<TeacherCourse> findTeacherCourseByUserAndStatusAndForbiddenIsNullAndPublish(User user,Integer status,Integer publish);
	Page<TeacherCourse> findTeacherCourseByUserAndPublishAndForbiddenIsNull(User user,Integer publish,Pageable pageable);
	
	List<TeacherCourse> findTeacherCourseByUserAndPublish(User user,Integer publish);
	@Query("select t from TeacherCourse t where t.courseName = :coursename and t.user.id= :userid and t.publish !='0'")
	TeacherCourse getTeacherCourseByCourseName(@Param("coursename") String cousername,@Param("userid") Long userid);
	
	Page<TeacherCourse> findTeacherCourseByUserAndPublishAndCType(User user,Integer publish,CourseType cType,Pageable pageable);
	
	/* use in super admin */
	Page<TeacherCourse> findTeacherCourseByUserAndPublishGreaterThan(User user,Integer publish,Pageable pageable);
	List<TeacherCourse> findTeacherCourseByUserAndPublishGreaterThan(User user,Integer publish);
	Page<TeacherCourse> findAllByPublishGreaterThan(Integer publish,Pageable pageable);
	List<TeacherCourse> findAllByPublishGreaterThan(Integer publish);
}
