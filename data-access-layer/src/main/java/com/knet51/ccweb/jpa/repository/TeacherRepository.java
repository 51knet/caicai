package com.knet51.ccweb.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;

public interface TeacherRepository  extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher>{
	Page<Teacher> findAll(Pageable pageable);
}
