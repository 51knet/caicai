package com.knet51.courses.jpa.services;
import java.util.List;

import org.springframework.data.domain.Page;
import com.knet51.ccweb.jpa.entities.teacher.Comment;

public interface CommentService {
	Comment createComment(Comment comment);
	Page<Comment> findAllCommit(int pageNumber, int pageSize,Long teacherCourse_id);
	Long getMark(Long teacherCourseId);
	Long getPerson(Long teacherCourseId);
	Comment getComment(Long teacherCourseId, Long userId);
	int  getCommentByTeacherCourseIdAndUserId(Long teacherCourseId, Long userId);
	List<Comment> getAllCourse(Long  teacherCourseId);
}
