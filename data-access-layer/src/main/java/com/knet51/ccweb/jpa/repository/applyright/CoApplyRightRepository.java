package com.knet51.ccweb.jpa.repository.applyright;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.applyright.CoApplyRight;

public interface CoApplyRightRepository extends JpaRepository<CoApplyRight, Long>, JpaSpecificationExecutor<CoApplyRight> {
	Page<CoApplyRight> findCoApplyRightByStatusAndComApplypermit(Integer status,String applypermit, Pageable pageable);
	Page<CoApplyRight> findCoApplyRightByStatus(Integer status,Pageable pageable);
	CoApplyRight findCoApplyRightByUser(User user);
}
