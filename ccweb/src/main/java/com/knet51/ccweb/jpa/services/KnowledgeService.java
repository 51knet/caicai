package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.Knowledge;

public interface KnowledgeService {
	
	Knowledge create (Knowledge knowledge);
	
	Knowledge update (Knowledge knowledge);
	
	void deleteById(Long k_id);
	
	Knowledge findOneById(Long k_id);
	
	Knowledge findOneByUseridAndCourseid(Long user_id, Long course_id);
	
	List<Knowledge> findAllByUserid(Long user_id);
	
	Page<Knowledge> findAllByUserid(int pageNumber, int pageSize, Long userid);
}
