package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.RechargeHistory;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.repository.RechargeHistoryRepository;

@Service("recharHistoryService")
public class RechargeHistoryServiceImpl implements RechargeHistoryService {
	@Autowired
	private RechargeHistoryRepository historyRepository;
	@Override
	public List<RechargeHistory> findAll() {
		return historyRepository.findAll();
	}

	@Override
	public RechargeHistory createRechargeHistory(RechargeHistory rechargeHistory) {
		return historyRepository.saveAndFlush(rechargeHistory);
	}

	@Override
	public Page<RechargeHistory> findAll(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return historyRepository.findAll(pageable);
	}

	@Override
	public Page<RechargeHistory> findAllByUser(int pageNumber,int pageSize,User user) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return historyRepository.findAllByUser(user,pageable);
	}
	

}
