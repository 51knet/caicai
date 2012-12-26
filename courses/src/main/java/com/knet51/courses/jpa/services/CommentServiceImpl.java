package com.knet51.courses.jpa.services;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.Comment;
import com.knet51.ccweb.jpa.repository.CommentRepository;
import com.knet51.ccweb.jpa.repository.UserRepository;
@Transactional
@Service("commentService")
public class CommentServiceImpl implements CommentService {
	private static final Logger logger = LoggerFactory
			.getLogger(CommentServiceImpl.class);
	@Autowired
	private CommentRepository commitRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@SuppressWarnings("unchecked")
	public Page<Comment> findCommentByTeachercourseid(int pageNumber, int pageSize,
			Long teacherCourse_id) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<Comment> onePage = commitRepository.findCommentByTeachercourseid(teacherCourse_id, dateDesc);
		return onePage;
	}
	@Override
	public List<Comment> findByTeachercourseid(Long teacherCourseId) {
		List<Comment> listComment=commitRepository.findByTeachercourseid(teacherCourseId);
		return listComment;
	}
	@Override
	public Double getMark(Long teacherCourseId) {
		Double mark=commitRepository.getMark(teacherCourseId);
		return mark;
	}
	@Override
	public Comment save(Comment comment) {
		 comment=commitRepository.save(comment);
		return comment;
	}
	
	@Override
	public Comment findByTeachercourseidAndUserid(Long teacherCourseId, Long userId) {
		Comment comment=commitRepository.findByTeachercourseidAndUserid(teacherCourseId, userId);
		return comment;
	}
	@Override
	public User findByUserId(Long id) {
		User user=userRepository.findOne(id);
		return user;
	}
	@Override
	public List<Comment> findCommentByUserid(Long user_id) {
		List<Comment> list=commitRepository.findCommentByUserid(user_id);
		return list;
	}
	
}
