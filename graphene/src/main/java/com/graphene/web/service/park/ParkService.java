package com.graphene.web.service.park;

import org.springframework.data.domain.Page;

import com.graphene.web.jpa.entity.park.Park;


public interface ParkService {
	Park create(Park park);
	Park update(Park park);
	void delete(Long id);
	Park find(Long id);
	Page<Park> findAll(int pageNumber,int pageSize);
}
