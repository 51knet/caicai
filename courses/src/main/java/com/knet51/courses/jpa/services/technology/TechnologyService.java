package com.knet51.courses.jpa.services.technology;

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
		
	Page<Technology> findAllByStatus(int pageNumber, int pageSize,Integer status);
	List<Technology> findAllListByStatus(Integer status);
	
	Page<Technology> findAllByFocusAndStatus(int pageNumber, int pageSize,Integer focus,Integer status);
	List<Technology> findListByFocusAndStatus(Integer focus,Integer status);
}
