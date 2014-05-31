package com.knet51.ccweb.jpa.repository.applyright;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.applyright.ExpApplyRight;

public interface ExpApplyRightRepository extends JpaRepository<ExpApplyRight, Long>, JpaSpecificationExecutor<ExpApplyRight> {
	ExpApplyRight findExpApplyByUser(User user);
}
