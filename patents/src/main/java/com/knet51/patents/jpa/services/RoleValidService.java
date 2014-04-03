package com.knet51.patents.jpa.services;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.RoleValid;

public interface RoleValidService {
	RoleValid create(RoleValid investValid);
	RoleValid update(RoleValid investValid);
	void delete(Long id);
	RoleValid find(Long id);
	
	Page<RoleValid> findRoleValidPage(int pageNumber,int pageSize);
	
	Page<RoleValid> findRoleValidByStatusAndApplypermit(Integer status,String applypermit, int pageNumber, int pageSize);
}
