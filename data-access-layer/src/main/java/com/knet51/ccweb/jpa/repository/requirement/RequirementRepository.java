package com.knet51.ccweb.jpa.repository.requirement;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.RequirType;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.requirement.Requirement;

public interface RequirementRepository extends JpaRepository<Requirement, Long>,JpaSpecificationExecutor<Requirement> {
	Page<Requirement> findRequireByUser(User user , Pageable pageable);
	Page<Requirement> findReqireByRequirType(RequirType type, Pageable pageable);
	List<Requirement> findRequirByUser(User user,Sort sort);
	List<Requirement> findRequirByRequirType(RequirType type, Sort sort);
	
	Page<Requirement> findRequireByStatus(Integer status, Pageable pageable);
	List<Requirement> findRequireListByStatus(Integer status);
	
	Page<Requirement> findReqireByTitleLike(String title,Pageable pageable);
	
}
