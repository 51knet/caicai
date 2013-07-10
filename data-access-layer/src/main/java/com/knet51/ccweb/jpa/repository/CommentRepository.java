package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> ,JpaSpecificationExecutor<Comment> {
	Page<Comment> findAllByTrendId(Long trend_id , Pageable pageable);
	List<Comment> findAllByTrendId(Long trend_id);
}
