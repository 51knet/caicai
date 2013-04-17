package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.blog.BlogComment;
import com.knet51.ccweb.jpa.entities.courses.TeacherCourse;

@Transactional
public interface BlogCommentRepository extends JpaRepository<BlogComment, Long>, JpaSpecificationExecutor<BlogComment> {
}
