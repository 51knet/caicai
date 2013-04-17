package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.courses.CourseType;
import com.knet51.ccweb.jpa.repository.CourseTypeRepository;
@Transactional
@Service("couseTypeService")
public class CourseTypeServiceImpl implements CourseTypeService {
	@Autowired
	private CourseTypeRepository courseTypeRepository;

	@Override
	public List<CourseType> findAll() {
		return courseTypeRepository.findAll();
	}

	@Override
	public void destryCourseType(Long id) {
		courseTypeRepository.delete(id);
	}

	@Override
	public CourseType createCourseType(CourseType courseType) {
		return courseTypeRepository.save(courseType);
	}

	@Override
	public CourseType updateCourseType(CourseType courseType) {
		return courseTypeRepository.saveAndFlush(courseType);
	}

	@Override
	public CourseType findOneById(Long id) {
		// TODO Auto-generated method stub
		return courseTypeRepository.findOne(id);
	}

}
