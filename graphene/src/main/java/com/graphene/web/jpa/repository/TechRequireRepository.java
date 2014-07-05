package com.graphene.web.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.graphene.web.jpa.entity.require.TechRequirement;
import com.graphene.web.jpa.entity.user.User;



public interface TechRequireRepository extends JpaRepository<TechRequirement, Long>,JpaSpecificationExecutor<TechRequirement> {
	Page<TechRequirement> findRequireByUser(User user , Pageable pageable);
	List<TechRequirement> findRequirByUser(User user,Sort sort);
	
	Page<TechRequirement> findRequireByStatus(Integer status, Pageable pageable);
	List<TechRequirement> findRequireListByStatus(Integer status);
	
	Page<TechRequirement> findReqireByTitleLike(String title,Pageable pageable);
	
}
