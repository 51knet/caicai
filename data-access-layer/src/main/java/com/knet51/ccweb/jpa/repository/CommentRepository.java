package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.teacher.Comment;

@Transactional
@SuppressWarnings("unchecked")
public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment>,CommentRepositoryCustom{
	Page<Comment> findCommentByTeachercourseid(Long teachercourseid ,Pageable pageable);
	List<Comment> findByTeachercourseid(Long teacherCourseId);
	Comment save(Comment comment);
	List<Comment> findCommentByUserid(Long user_id);
	@Query("select c from Comment c where c.teachercourseid = :teachercourseid and c.userid = :userid")
	Comment findByTeachercourseidAndUserid(@Param("teachercourseid") Long teachercourseid,
	                                 @Param("userid") Long userid);
}
