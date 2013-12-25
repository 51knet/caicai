package com.knet51.patents.jpa.services.requirement;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.RequirType;
import com.knet51.ccweb.jpa.entities.Requirement;
import com.knet51.ccweb.jpa.entities.User;

public interface RequirementService {
	Requirement create(Requirement requirement);
	Requirement findOne(Long require_id);
	void delete(Long id);
	Requirement update(Requirement requirement);
	Page<Requirement> findRequireByUser(int pageNum, int pageSize, User user );
	Page<Requirement> findRequireByRequireType(int pageNum, int pageSize, RequirType type);
	Page<Requirement> findRequireAll(int pageNum, int pageSize);
	List<Requirement> findRequireListByUser(User user);
	List<Requirement> findRequiteListByRequireType(RequirType type);
	
	Page<Requirement> findRequireByStatus(int pageNum, int pageSize, Integer status);
}
