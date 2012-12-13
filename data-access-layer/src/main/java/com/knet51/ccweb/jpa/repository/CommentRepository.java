package com.knet51.ccweb.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.teacher.Comment;

@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment>,CommentRepositoryCustom {
	Page<Comment> findCommentByCourseid(Long teachercourseid ,Pageable pageable);
}
