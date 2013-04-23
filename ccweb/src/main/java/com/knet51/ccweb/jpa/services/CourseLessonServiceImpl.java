package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.courses.CourseLesson;
import com.knet51.ccweb.jpa.repository.CourseLessonRepository;
@Service("courseLessonService")
@Transactional
public class CourseLessonServiceImpl implements CourseLessonService {
	
	@Autowired
	private CourseLessonRepository lessonRepository;

	@Override
	public List<CourseLesson> getMaxLessonNumByCourseId(Long course_id) {
		
		return lessonRepository.getMaxLessonNumByCourseId(course_id);
	}

	@Override
	public CourseLesson findOne(Long id) {
		
		return lessonRepository.findOne(id);
	}

	@Override
	public CourseLesson createCourseLesson(CourseLesson courseLesson) {
		// TODO Auto-generated method stub
		return lessonRepository.save(courseLesson);
	}



	@Override
	public List<CourseLesson> findCourseLessonByCourseId(Long course_id) {
		Sort sort = new Sort("lessonNum");
		List<CourseLesson> lessonList = lessonRepository.findCourseLessonByCourseId(course_id, sort);
		return lessonList;
	}

	@Override
	public void destory(Long id) {
		lessonRepository.delete(id);
		
	}

	@Override
	public List<CourseLesson> findCourseLessonByCourseIdAndLessonNum(
			Long course_id, String lessonNum) {
		List<CourseLesson> lessonList = lessonRepository.findCourseLessonByCourseIdAndLessonNum(course_id, lessonNum);
		return lessonList;
	}

}
