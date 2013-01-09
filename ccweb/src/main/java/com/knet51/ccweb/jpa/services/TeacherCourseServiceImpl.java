package com.knet51.ccweb.jpa.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.beans.CourseBeans;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.repository.TeacherCourseRepository;
import com.knet51.ccweb.jpa.repository.TeacherRepository;
@Transactional
@Service("teacherCourseService")
public class TeacherCourseServiceImpl implements TeacherCourseService {
	@Autowired
	private TeacherCourseRepository courseRepository;
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public TeacherCourse findOneById(Long id) {	 
		return courseRepository.findOne(id);
	}

	@Override
	public TeacherCourse createTeacherCourse(TeacherCourse teacherCourse) {
		return courseRepository.save(teacherCourse);
	}

	@Override
	public TeacherCourse updateTeacherCourse(TeacherCourse teacherCourse) {
		return courseRepository.updateTeacherCourseDetail(teacherCourse);
	}

	@Override
	public List<TeacherCourse> getAllTeacherCourseByTeacheridAndPublish(Long teacher_id,Integer publish) {
		Teacher teacher = teacherRepository.findOne(teacher_id);
		return courseRepository.findTeacherCourseByTeacherAndPublish(teacher, publish);
	}

	@Override
	public void deleTeacherCourse(Long course_id) {
		courseRepository.delete(course_id);
	}

	@Override
	public Page<TeacherCourse> findAllCourseByTeacher(int pageNum, int pageSize,
			Teacher teacher) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id"); 
		Page<TeacherCourse> onePage = courseRepository.findTeacherCourseByTeacher(teacher, dateDesc);
		return onePage;
	}

//	@Override
//	public List<CourseBeans> getAllCourseBeans() {
//		// FIXME this is stupid to depend the CourseBeans on this layer  
//		//return teacherCourseRepository.listAllCourseBeans(teacher_id);
//		return Collections.emptyList();
//	}

	@Override
	public List<String> getAllSchool() {
		return courseRepository.getSchool();
	}

	@Override
	public List<Teacher> getAllCourseTeacher(String schoolName) {
		return courseRepository.showCourseTeacher(schoolName);
	}

	@Override
	public Page<TeacherCourse> findTeacherCourseByTeacherAndPublish(
			int pageNum, int pageSize, Teacher teacher, Integer publish) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id"); 
		Page<TeacherCourse> onePage = courseRepository.findTeacherCourseByTeacherAndPublish(teacher, publish, dateDesc);
		return onePage;
	}

	@Override
	public Page<TeacherCourse> findTeacherCourseByTeacherAndPublishGreaterThan(
			int pageNum, int pageSize, Teacher teacher, Integer publish) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id"); 
		Page<TeacherCourse> onePage = courseRepository.findTeacherCourseByTeacherAndPublishGreaterThan(teacher, publish, dateDesc);
		return onePage;
	}


	

}
