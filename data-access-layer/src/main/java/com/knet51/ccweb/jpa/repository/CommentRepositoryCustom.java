package com.knet51.ccweb.jpa.repository;

import java.util.List;

import com.knet51.ccweb.jpa.entities.teacher.Comment;


public interface CommentRepositoryCustom {
	Comment createComment(Comment comment);
	Long getPerson(Long  teacherCourseId);
	Long getMark(Long teacherCourseId);
	Comment getComment(Long teacherCourseId, Long userId);
	int  getCommentByTeacherCourseIdAndUserId(Long teacherCourseId, Long userId);
	List<Comment> getAllCourse(Long  teacherCourseId);
}
