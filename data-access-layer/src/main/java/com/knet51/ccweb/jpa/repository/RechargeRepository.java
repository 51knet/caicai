package com.knet51.ccweb.jpa.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Recharge;

@Transactional
public interface RechargeRepository  extends JpaRepository<Recharge, Long>, JpaSpecificationExecutor<Recharge>{
	Page<Recharge> findAll(Pageable pageable);
	Recharge findRechargeByCardid(String cardid);
}
