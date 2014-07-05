package com.graphene.web.service.requirement;

import java.util.List;

import org.springframework.data.domain.Page;

import com.graphene.web.jpa.entity.require.TechRequirement;
import com.graphene.web.jpa.entity.user.User;



public interface TechRequireService {
	TechRequirement create(TechRequirement requirement);
	TechRequirement findOne(Long require_id);
	void delete(Long id);
	TechRequirement update(TechRequirement requirement);
	Page<TechRequirement> findRequireByUser(int pageNum, int pageSize, User user );
	Page<TechRequirement> findRequireAll(int pageNum, int pageSize);
	List<TechRequirement> findRequireListByUser(User user);
	
	Page<TechRequirement> findRequireByStatus(int pageNum, int pageSize, Integer status);
	
	Page<TechRequirement> findRequireByTitleLike(int pageNum, int pageSize, String title);
}
