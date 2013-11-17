package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.RequirType;
import com.knet51.ccweb.jpa.entities.Requirement;
import com.knet51.ccweb.jpa.entities.User;

public interface RequirementRepository extends JpaRepository<Requirement, Long>,JpaSpecificationExecutor<Requirement> {
	Page<Requirement> findRequireByUser(User user , Pageable pageable);
	Page<Requirement> findReqireByRequirType(RequirType type, Pageable pageable);
	List<Requirement> findRequirByUser(User user);
	List<Requirement> findRequirByRequirType(RequirType type);
}
