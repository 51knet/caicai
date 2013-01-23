package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.teacher.CourseLesson;
import com.knet51.ccweb.jpa.repository.CourseLessonRepository;
@Service
@Transactional
public class CourseLessonServiceImpl implements CourseLessonService {
	
	@Autowired
	private CourseLessonRepository lessonRepository;

	@Override
	public CourseLesson getMaxLessonNumByCourseId(Long course_id) {
		
		return null;
	}

	@Override
	public CourseLesson findOne(Long id) {
		
		return lessonRepository.findOne(id);
	}

	@Override
	public CourseLesson createCourseLesson(CourseLesson courseLesson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseLesson destory(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseLesson> findCourseLessonByCourseId(Long course_id) {
		Sort sort = new Sort("lessonNum");
		List<CourseLesson> lessonList = lessonRepository.findCourseLessonByCourseId(course_id, sort);
		return lessonList;
	}

}
