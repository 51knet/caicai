package com.knet51.patents.jpa.services.applyright;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.applyright.CoApplyRight;

public interface CoApplyRightService {
	CoApplyRight create(CoApplyRight coApplyRight);
	CoApplyRight update(CoApplyRight coApplyRight);
	void delete(Long id);
	CoApplyRight find(Long id);
	Page<CoApplyRight> findCoApplyRightPage(int pageNumber,int pageSize);
	Page<CoApplyRight> findCoApplyRightByStatusAndComApplypermit(Integer status,String applypermit, int pageNumber, int pageSize);
	Page<CoApplyRight> findCoApplyRightByStatus(Integer status,int pageNumber, int pageSize);
}
