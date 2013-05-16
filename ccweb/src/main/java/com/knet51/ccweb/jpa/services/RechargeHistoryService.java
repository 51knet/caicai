package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.RechargeHistory;
import com.knet51.ccweb.jpa.entities.User;

public interface RechargeHistoryService {
	List<RechargeHistory>findAll();
	RechargeHistory createRechargeHistory(RechargeHistory history);
	Page<RechargeHistory> findAll(int pageNumber,int pageSize);
	Page<RechargeHistory>findAllByUser(int pageNumber,int pageSize,User user);
}
