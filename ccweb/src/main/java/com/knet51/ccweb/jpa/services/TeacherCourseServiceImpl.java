package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseType;
import com.knet51.ccweb.jpa.entities.courses.TeacherCourse;
import com.knet51.ccweb.jpa.repository.TeacherCourseRepository;
import com.knet51.ccweb.jpa.repository.TeacherRepository;
import com.knet51.ccweb.jpa.repository.UserRepository;
@Transactional
@Service("teacherCourseService")
public class TeacherCourseServiceImpl implements TeacherCourseService {
	@Autowired
	private TeacherCourseRepository courseRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private UserRepository userRepository;

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
		User user = userRepository.findOne(teacher_id);
		return courseRepository.findTeacherCourseByUserAndPublish(user, publish);
	}

	@Override
	public void deleTeacherCourse(Long course_id) {
		courseRepository.delete(course_id);
	}

	@Override
	public Page<TeacherCourse> findAllCourseByUser(int pageNum, int pageSize,
			User user) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id"); 
		Page<TeacherCourse> onePage = courseRepository.findTeacherCourseByUser(user, dateDesc);
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
	public Page<TeacherCourse> findTeacherCourseByUserAndPublish(
			int pageNum, int pageSize, User user, Integer publish) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id"); 
		Page<TeacherCourse> onePage = courseRepository.findTeacherCourseByUserAndPublishAndForbiddenIsNull(user, publish, dateDesc);
		return onePage;
	}

	@Override
	public Page<TeacherCourse> findTeacherCourseByUserAndPublishGreaterThan(
			int pageNum, int pageSize,User user, Integer publish) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id"); 
		Page<TeacherCourse> onePage = courseRepository.findTeacherCourseByUserAndForbiddenIsNullAndPublishGreaterThan(user, publish, dateDesc);
		return onePage;
	}

	@Override
	public TeacherCourse getTeacherCourseByCourseName(String cousername,Long teacherId) {
		TeacherCourse teacherCourse=courseRepository.getTeacherCourseByCourseName(cousername,teacherId);
		return teacherCourse;
	}

	@Override
	public Page<TeacherCourse> findAllCourse(int pageNum, int pageSize) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id"); 
		Page<TeacherCourse> onePage = courseRepository.findAll(dateDesc);
		return onePage;
	}

	@Override
	public Page<TeacherCourse> findTeacherCourseByUserAndPublishAndCType(
			int pageNum, int pageSize, User user, Integer publish,
			CourseType cType) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<TeacherCourse> onePage = courseRepository.findTeacherCourseByUserAndPublishAndCType(user, publish, cType, dateDesc);
		return onePage;
	}

	@Override
	public List<TeacherCourse> findCourseByUserAndPublishGreaterThanForSuperAdmin(
			User user, Integer publish) {
		return courseRepository.findTeacherCourseByUserAndPublishGreaterThan(user, publish);
	}

	@Override
	public Page<TeacherCourse> findCourseByUserAndPublishGreaterThanForSuperAdmin(
			User user, Integer publish, int pageNum, int pageSize) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id"); 
		Page<TeacherCourse> onePage = courseRepository.findTeacherCourseByUserAndPublishGreaterThan(user, publish, dateDesc);
		return onePage;
	}

	@Override
	public Page<TeacherCourse> findCourseByPublishGreaterThanForSuperAdmin(
			Integer publish, int pageNum, int pageSize) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id"); 
		Page<TeacherCourse> onePage = courseRepository.findAllByPublishGreaterThan(publish, dateDesc);
		return onePage;
	}

	@Override
	public List<TeacherCourse> findAll() {
		return courseRepository.findAll();
	}

	


	

}
