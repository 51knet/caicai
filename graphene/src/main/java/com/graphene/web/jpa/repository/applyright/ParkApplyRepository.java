package com.graphene.web.jpa.repository.applyright;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.graphene.web.jpa.entity.applyright.ParkApplyRight;
import com.graphene.web.jpa.entity.user.User;

public interface ParkApplyRepository extends JpaRepository<ParkApplyRight, Long>,JpaSpecificationExecutor<ParkApplyRight> {
	Page<ParkApplyRight> findParkApplyByStatus(Integer status,Pageable pageable);
	ParkApplyRight findParkApplyRightByUser(User user);

}
