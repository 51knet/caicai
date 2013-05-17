package com.knet51.ccweb.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.WithdrawsApply;

public interface WithdrawsApplyRepository extends JpaRepository<WithdrawsApply, Long>,JpaSpecificationExecutor<WithdrawsApply> {
	
	Page<WithdrawsApply> findAllByUser(User user, Pageable pageable);
	Page<WithdrawsApply> findAll(Pageable pageable);
}
