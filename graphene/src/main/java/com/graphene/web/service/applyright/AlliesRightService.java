package com.graphene.web.service.applyright;

import org.springframework.data.domain.Page;

import com.graphene.web.jpa.entity.applyright.AlliesApplyRight;
import com.graphene.web.jpa.entity.user.User;


public interface AlliesRightService {
	AlliesApplyRight create(AlliesApplyRight allieApply);
	AlliesApplyRight update(AlliesApplyRight allieApply);
	void delete(Long id);
	AlliesApplyRight find(Long id);
	
	Page<AlliesApplyRight> findAllByStatus(int pageNumber, int pageSize, Integer status);
	Page<AlliesApplyRight> findAll(int pageNumber, int pageSize);
	
	boolean empower4User(Long apply_id, User user,String types);
}
