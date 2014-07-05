package com.graphene.web.service.allies;

import org.springframework.data.domain.Page;

import com.graphene.web.jpa.entity.allies.Allies;



public interface AlliesService {
	Allies create(Allies allie);
	Allies update(Allies allie);
	void delete(Long id);
	Allies find(Long id);
	Page<Allies> findAll(int pageNumber,int pageSize);
	
	
}
