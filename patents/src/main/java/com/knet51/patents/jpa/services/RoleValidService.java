package com.knet51.patents.jpa.services;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.RoleValid;

public interface RoleValidService {
	RoleValid create(RoleValid investValid);
	RoleValid update(RoleValid investValid);
	void delete(Long id);
	RoleValid find(Long id);
	
	Page<RoleValid> findInvestValidPage(int pageNumber,int pageSize);
}
