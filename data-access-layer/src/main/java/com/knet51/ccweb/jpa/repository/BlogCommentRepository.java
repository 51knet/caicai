package com.knet51.ccweb.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.blog.BlogComment;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

@Transactional
public interface BlogCommentRepository extends JpaRepository<BlogComment, Long>, JpaSpecificationExecutor<BlogComment> {
	@Query("select t from BlogComment b where b.blogpost_id = :blogpost_id)
	List<BlogComment> getBlogCommentByblogpost_id(@Param("blogpost_id") Long blogpost_id);
}
