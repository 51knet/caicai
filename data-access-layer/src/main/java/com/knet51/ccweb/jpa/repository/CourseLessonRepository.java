package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.teacher.CourseLesson;
@Transactional
public interface CourseLessonRepository extends JpaRepository<CourseLesson, Long>,JpaSpecificationExecutor<CourseLesson> {
//	@Query("select c from CourseLesson c where c.courseId =: cId and c.lessonNum = (select max(cl.lessonNum) from CourseLesson cl where cl.courseId =: cId)")
//	CourseLesson getMaxLessonNum(@Param("cId") Long courseId);
	List<CourseLesson> findCourseLessonByCourseId(Long course_id, Sort sort);
}
