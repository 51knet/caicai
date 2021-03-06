package com.knet51.ccweb.jpa.repository.course;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.courses.CourseLesson;
@Transactional
public interface CourseLessonRepository extends JpaRepository<CourseLesson, Long>,JpaSpecificationExecutor<CourseLesson>,CourseLessonRepositoryCustom {
//	@Query("select c from CourseLesson c where c.courseId =: cId and c.lessonNum = (select max(cl.lessonNum) from CourseLesson cl where cl.courseId =: cId)")
//	CourseLesson getMaxLessonNum(@Param("cId") Long courseId);
	List<CourseLesson> findCourseLessonByCourseId(Long course_id, Sort sort);
	List<CourseLesson> findCourseLessonByCourseIdAndLessonNum(Long course_id,int lessonNum);
}
