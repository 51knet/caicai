package com.knet51.courses.jpa.services;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.Comment;

public interface CommentService {
	List<Comment> findByTeachercourseid(Long teacherCourseId);
	Comment save(Comment comment);
	Comment findByTeachercourseidAndUserid(Long teacherCourseId, Long userId);
	Page<Comment> findCommentByTeachercourseid(int pageNumber, int pageSize,
			Long teacherCourse_id);
	User findByUserId(Long userid);
}
