package com.knet51.ccweb.jpa.services.trade;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.WithdrawsApply;

public interface WithdrawsApplyService {
	Page<WithdrawsApply> findAllByUser(int pageNumber,int pageSize,User user);
	Page<WithdrawsApply> findAll(int pageNumber,int pageSize);
	WithdrawsApply createWithdrawsApply(WithdrawsApply withdrawsApply);
	void deleteWithdrawsApply(Long id);
	WithdrawsApply findOneById(Long id);
}
