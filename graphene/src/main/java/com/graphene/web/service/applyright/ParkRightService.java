package com.graphene.web.service.applyright;

import org.springframework.data.domain.Page;

import com.graphene.web.jpa.entity.applyright.ParkApplyRight;


public interface ParkRightService {
	ParkApplyRight create(ParkApplyRight parkApply);
	ParkApplyRight update(ParkApplyRight parkApply);
	void delete(Long id);
	ParkApplyRight find(Long id);
	
	Page<ParkApplyRight> findAllByStatus(Integer status, int pageNumber, int pageSize);
	Page<ParkApplyRight> findAll(int pageNumber, int pageSize);
}
