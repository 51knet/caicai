package com.knet51.courses.jpa.services;
import org.springframework.data.domain.Page;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.Comment;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

public interface CommentService {
	Comment createComment(Comment comment);
	Page<Comment> findAllCommit(int pageNumber, int pageSize,Long teacherCourse_id);
	Long getMark(Long teacherCourseId);
	Long getPerson(Long teacherCourseId);
	Long getMark(Long teacherCourseId, Long userId);
}
