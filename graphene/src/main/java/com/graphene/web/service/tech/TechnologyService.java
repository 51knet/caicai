package com.graphene.web.service.tech;

import java.util.List;

import org.springframework.data.domain.Page;

import com.graphene.web.jpa.entity.tech.Technology;
import com.graphene.web.jpa.entity.user.User;


public interface TechnologyService {
	Technology create(Technology technology);
	void delete(Long id);
	Technology update(Technology technology);
	Technology findOne(Long id);
	
	Page<Technology> findAllByUser(User user, int pageNumber, int pageSize);
	List<Technology> findListByUser(User user);
	
	Page<Technology> findAll(int pageNumber, int pageSize);
	List<Technology> findAllList();
	
	Page<Technology> findAllByStatus(int pageNumber, int pageSize,Integer status);
	List<Technology> findAllListByStatus(Integer status);
	
	Page<Technology> findAllByFocus(int pageNumber, int pageSize,Integer focus);
	
	Page<Technology> findAllByUserAndStatus(User user, int status,int pageNumber, int pageSize);
}
