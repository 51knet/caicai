package com.knet51.ccweb.jpa.repository.requirement;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.PatentRequirement;
import com.knet51.ccweb.jpa.entities.User;

public interface PatentRequirementRepository extends JpaRepository<PatentRequirement, Long>,JpaSpecificationExecutor<Long> {
	Page<PatentRequirement> findAll(Pageable pageable);
	
	Page<PatentRequirement> findAllByUser(User user, Pageable pageable);
	List<PatentRequirement> findAllListByUser(User user, Pageable pageable);
	
	Page<PatentRequirement> findAllByStatus(Integer status, Pageable pageable );
	List<PatentRequirement> findAllListByStatus(Integer status, Pageable pageable);
}
