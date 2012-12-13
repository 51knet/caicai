package com.knet51.ccweb.jpa.repository;

import com.knet51.ccweb.jpa.entities.teacher.Comment;


public interface CommentRepositoryCustom {
	Comment createComment(Comment comment);
	Long getPerson(Long  teacherCourseId);
	Long getMark(Long teacherCourseId, Long userId);
	Long getMark(Long teacherCourseId);
}
