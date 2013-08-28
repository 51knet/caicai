package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.Knowledge;

public interface KnowledgeRepository extends JpaRepository<Knowledge ,Long>, JpaSpecificationExecutor<Knowledge> {
	List<Knowledge> findKnowledgeByUserid(Long userid);
	
	Page<Knowledge> findByUserid(Long userid, Pageable pageable);
	
	Knowledge findByUseridAndCourseid(Long userid, Long courseid);
}
