package com.knet51.patents.jpa.services.technology;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.requirement.PatentRequirement;
import com.knet51.ccweb.jpa.entities.technology.Technology;

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
}
