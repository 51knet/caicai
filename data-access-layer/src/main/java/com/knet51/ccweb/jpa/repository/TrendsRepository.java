package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.timeline.Trends;

public interface TrendsRepository extends JpaRepository<Trends, Long>, JpaSpecificationExecutor<Trends> {
	Page<Trends> findAllByUserId(Long user_id, Pageable pageable);
	
	List<Trends> findAllByUserId(Long user_id);
}
