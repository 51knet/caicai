package com.knet51.ccweb.jpa.repository.course;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseType;
import com.knet51.ccweb.jpa.entities.courses.Course;

@Transactional
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course>,CourseRepositoryCustom{
	Page<Course> findTeacherCourseByUser(User user,Pageable pageable);
	Page<Course> findAll(Pageable pageable);
	Page<Course> findTeacherCourseByUserAndForbiddenIsNullAndPublishGreaterThan(User user,Integer publish,Pageable pageable);
	//List<String> getCourseType();
	List<Course> findTeacherCourseByStatusAndPublishAndForbiddenIsNull(Integer status,Integer publish,Sort sort);
	List<Course> findTeacherCourseByUserAndStatusAndForbiddenIsNullAndPublish(User user,Integer status,Integer publish);
	Page<Course> findTeacherCourseByUserAndPublishAndForbiddenIsNull(User user,Integer publish,Pageable pageable);
	
	List<Course> findTeacherCourseByUserAndPublish(User user,Integer publish);
	@Query("select t from Course t where t.courseName = :coursename and t.user.id= :userid and t.publish !='0'")
	Course getTeacherCourseByCourseName(@Param("coursename") String cousername,@Param("userid") Long userid);
	
	Page<Course> findTeacherCourseByUserAndPublishAndCType(User user,Integer publish,CourseType cType,Pageable pageable);
	
	/* use in super admin */
	Page<Course> findTeacherCourseByUserAndPublishGreaterThan(User user,Integer publish,Pageable pageable);
	List<Course> findTeacherCourseByUserAndPublishGreaterThan(User user,Integer publish);
	Page<Course> findAllByPublishGreaterThan(Integer publish,Pageable pageable);
	List<Course> findAllByPublishGreaterThan(Integer publish);
}
