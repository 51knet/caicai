package com.knet51.ccweb.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.RechargeHistory;
import com.knet51.ccweb.jpa.entities.User;

@Transactional
public interface RechargeHistoryRepository  extends JpaRepository<RechargeHistory, Long>, JpaSpecificationExecutor<RechargeHistory>{
	Page<RechargeHistory> findAll(Pageable pageable);
	Page<RechargeHistory> findAllByUser(User user,Pageable pageable);
}
