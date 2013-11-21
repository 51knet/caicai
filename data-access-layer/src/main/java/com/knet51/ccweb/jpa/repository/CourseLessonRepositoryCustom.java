package com.knet51.ccweb.jpa.repository;

import java.util.List;

import com.knet51.ccweb.jpa.entities.courses.CourseLesson;
import com.knet51.ccweb.jpa.entities.patent.Patent;

public interface CourseLessonRepositoryCustom {
	List<CourseLesson> getMaxLessonNumByCourseId(Long course_id);
}
