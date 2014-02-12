package com.knet51.ccweb.jpa.repository.requirement;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.requirement.PatentRequirement;

public interface PatentRequirementRepository extends JpaRepository<PatentRequirement, Long>,JpaSpecificationExecutor<Long> {
	Page<PatentRequirement> findAll(Pageable pageable);
	
	Page<PatentRequirement> findAllByUser(User user, Pageable pageable);
	List<PatentRequirement> findAllListByUser(User user, Sort sort);
	
	Page<PatentRequirement> findAllByStatus(Integer status, Pageable pageable );
	List<PatentRequirement> findAllListByStatus(Integer status ,Sort sort);
}
