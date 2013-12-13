package com.knet51.ccweb.jpa.services.trade;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.Recharge;

public interface RechargeService {
	List<Recharge> findAll();
	Recharge createRecharge(Recharge recharge);
	Recharge updateRecharge(Recharge recharge);
	void deleteRechargeById(Long id);
	Page<Recharge> findAll(int pageNumber,int pageSize);
	Recharge findOneByCardid(String cardid);
}
