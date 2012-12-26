package com.knet51.courses.jpa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.UserCourse;
import com.knet51.ccweb.jpa.entities.teacher.Comment;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.repository.CommentRepository;
import com.knet51.ccweb.jpa.repository.TeacherCourseRepository;
import com.knet51.ccweb.jpa.repository.UserCourseRepository;
@Transactional
@Service("userCourseService")
public class UserCourseServiceImpl implements UserCourseService {
	
	@Autowired
	private UserCourseRepository userCourseRepository;
	
	@Autowired
	private TeacherCourseRepository courseRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public UserCourse create(UserCourse userCourse) {
		return userCourseRepository.save(userCourse);
	}

	@Override
	public List<TeacherCourse> getCourseByUserId(Long user_id) {
		//List<UserCourse> userCourseList = userCourseRepository.findCourseByUserId(user_id);
		List<Comment> comment = commentRepository.findCommentByUserid(user_id);
		List<TeacherCourse> courseList = new ArrayList<TeacherCourse>();
		for(int i=0;i<comment.size();i++){
			TeacherCourse course = courseRepository.findOne(comment.get(i).getTeachercourseid());
			courseList.add(course);
		}
		return courseList;
	}

}
