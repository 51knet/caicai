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
	public Comment createComment(Comment comment) {
		return commitRepository.save(comment);
	}
	@Override
	public Page<Comment> findAllCommit(int pageNumber, int pageSize,
			Long teacherCourse_id) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		
		Page<Comment> onePage = commitRepository.findCommentByTeachercourseid(teacherCourse_id, dateDesc);
		/*List<Comment> list = onePage.getContent();
		for (int i = 0; i < list.size(); i++) {
			System.out.println("+++++++++++++++++"+list.get(i).getTeachercourseid());
		}*/
		return onePage;
	}
	@Override
	public Double getMark(Long teacherCourseId) {
		Double mark=commitRepository.getMark(teacherCourseId);
		return mark;
	}
	@Override
	public Long getPerson(Long teacherCourseId) {
		Long personNum=commitRepository.getPerson(teacherCourseId);
		return personNum;
	}
	@Override
	public Comment getComment(Long teacherCourseId, Long userId) {
		Comment comment=commitRepository.getComment(teacherCourseId, userId);
		return comment;
	}
	@Override
	public int getCommentByTeacherCourseIdAndUserId(Long teacherCourseId,
			Long userId) {
		int num=commitRepository.getCommentByTeacherCourseIdAndUserId(teacherCourseId, userId);
		return num;
	}
	@Override
	public List<Comment> getAllCourse(Long teacherCourseId) {
		List<Comment> list=new ArrayList<Comment>();
		list=commitRepository.getAllCourse(teacherCourseId);
		return list;
	}
	@Override
	public User getByUser(Long userId) {
		User user=userRepository.findOne(userId);
		return user;
	}
}
