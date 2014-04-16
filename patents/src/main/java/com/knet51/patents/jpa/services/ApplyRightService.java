package com.knet51.patents.jpa.services;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.ApplyRight;
import com.knet51.ccweb.jpa.entities.User;

public interface ApplyRightService {
	ApplyRight create(ApplyRight applyRight);
	ApplyRight update(ApplyRight applyRight);
	void delete(Long id);
	ApplyRight find(Long id);
	
	Page<ApplyRight> findApplyRightPage(int pageNumber,int pageSize);
	
	Page<ApplyRight> findApplyRightByStatusAndApplypermit(Integer status,String applypermit, int pageNumber, int pageSize);
	
	Page<ApplyRight> findApplyRightByStatus(Integer status,int pageNumber, int pageSize);
	
	boolean empower4User(Long apply_id, User user);
}
