package com.knet51.ccweb.jpa.repository.applyright;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.applyright.ApplyRight;

public interface ApplyRightRepository extends JpaRepository<ApplyRight, Long>,JpaSpecificationExecutor<ApplyRight> {
	Page<ApplyRight> findApplyRightByStatusAndApplypermit(Integer status,String applyright, Pageable pageable);
	Page<ApplyRight> findApplyRightByStatus(Integer status, Pageable pageable);
}
