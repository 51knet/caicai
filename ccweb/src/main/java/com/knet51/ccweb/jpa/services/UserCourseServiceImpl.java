package com.knet51.ccweb.jpa.services;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.Course;
import com.knet51.ccweb.jpa.entities.courses.UserCourse;
import com.knet51.ccweb.jpa.repository.user.UserCourseRepository;
import com.knet51.ccweb.jpa.repository.user.UserRepository;
import com.knet51.ccweb.jpa.services.course.CourseService;
@Transactional
@Service("userCourseService")
public class UserCourseServiceImpl implements UserCourseService {
	@Autowired
	private UserCourseRepository userCourseRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseService courseService;
	
	@Override
	public Page<UserCourse> findUserCourseByTeachercourseid(int pageNumber, int pageSize,
			Long teacherCourse_id) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<UserCourse> onePage = userCourseRepository.findUserCourseByTeachercourseid(teacherCourse_id, dateDesc);
		return onePage;
	}
	@Override
	public List<UserCourse> findByTeachercourseid(Long teacherCourseId) {
		List<UserCourse> listUserCourse=userCourseRepository.findByTeachercourseid(teacherCourseId);
		return listUserCourse;
	}
	@Override
	public Double getMark(Long teacherCourseId) {
		Double mark=0.0;
		try {
			mark=userCourseRepository.getMark(teacherCourseId);
		} catch (NoResultException e) {
			e.printStackTrace();
		}
			 
		return mark;
	}
	@Override
	public UserCourse save(UserCourse userCourse) {
		userCourse=userCourseRepository.save(userCourse);
		return userCourse;
	}
	
	@Override
	public UserCourse findByTeachercourseidAndUserid(Long teacherCourseId, Long userId) {
		UserCourse UserCourse=userCourseRepository.findByTeachercourseidAndUserid(teacherCourseId, userId);
		return UserCourse;
	}
	@Override
	public User findByUserId(Long id) {
		User user=userRepository.findOne(id);
		return user;
	}
	@Override
	public List<UserCourse> findUserCourseByUserid(Long user_id) {
		List<UserCourse> list=userCourseRepository.findUserCourseByUserid(user_id);
		return list;
	}
	@Override
	public UserCourse update(UserCourse userCourse) {
		userCourse=userCourseRepository.saveAndFlush(userCourse);
		return userCourse;
	}
	@Override
	public Page<UserCourse> findByUserid(int pageNumber, int pageSize,
			Long user_id) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<UserCourse> onePage = userCourseRepository.findByUserid(user_id, dateDesc);
		return onePage;
		
	}
	@Override
	public List<Course> findAllCourseByUserId(Long id) {
		Course course;
		List<Course> courseList = new ArrayList<Course>();
		List<UserCourse> userCourseList = findUserCourseByUserid(id);
		for(UserCourse userCourse : userCourseList){
			course = courseService.findOneById(userCourse.getTeachercourseid());
			courseList.add(course);
		}
		return courseList;
	}
	
}
