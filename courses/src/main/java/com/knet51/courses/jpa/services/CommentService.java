package com.knet51.courses.jpa.services;
import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.Comment;

public interface CommentService {
	Page<Comment> findCommentByTeachercourseid(int pageNumber, int pageSize,Long teacherCourse_id);
	List<Comment> findByTeachercourseid(Long teacherCourseId);
	Comment save(Comment comment);
	List<Comment> findCommentByUserid(Long user_id);
	Comment findByTeachercourseidAndUserid( Long teachercourseid,Long userid);
	Double getMark(Long teacherCourseId);
	public User findByUserId(Long id);
}
