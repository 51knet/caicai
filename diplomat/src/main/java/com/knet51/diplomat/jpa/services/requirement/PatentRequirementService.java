package com.knet51.diplomat.jpa.services.requirement;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.requirement.PatentRequirement;

public interface PatentRequirementService {
	
	PatentRequirement create(PatentRequirement patentRequirement);
	void delete(Long id);
	PatentRequirement update(PatentRequirement patentRequirement);
	PatentRequirement findOne(Long id);
	
	Page<PatentRequirement> findAllByUser(User user, int pageNumber, int pageSize);
	List<PatentRequirement> findListByUser(User user);
	
	Page<PatentRequirement> findAll(int pageNumber, int pageSize);
	List<PatentRequirement> findAllList();
	
	Page<PatentRequirement> findAllByStatus(int pageNumber, int pageSize,Integer status);
	List<PatentRequirement> findAllListByStatus(Integer status);
}
