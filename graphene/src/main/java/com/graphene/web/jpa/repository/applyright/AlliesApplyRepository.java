package com.graphene.web.jpa.repository.applyright;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.graphene.web.jpa.entity.applyright.AlliesApplyRight;
import com.graphene.web.jpa.entity.user.User;

public interface AlliesApplyRepository extends JpaRepository<AlliesApplyRight, Long>, JpaSpecificationExecutor<AlliesApplyRight> {
	Page<AlliesApplyRight> findAllieApplyByStatus(Integer status,Pageable pageable);
	AlliesApplyRight findAllieApplyRightByUser(User user);
}
