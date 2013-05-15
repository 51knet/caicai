package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.Recharge;
import com.knet51.ccweb.jpa.repository.RechargeRepository;
@Service("rechargeService")
public class RechargeServiceImpl implements RechargeService {
	@Autowired
	private RechargeRepository rechargeRepository;
	@Override
	public List<Recharge> findAll() {
		Sort sort = new Sort(Direction.DESC, "id");
		return rechargeRepository.findAll(sort);
	}

	@Override
	public Recharge createRecharge(Recharge recharge) {
		return rechargeRepository.save(recharge);
	}

	@Override
	public Recharge updateRecharge(Recharge recharge) {
		return rechargeRepository.saveAndFlush(recharge);
	}

	@Override
	public void deleteRechargeById(Long id) {
		rechargeRepository.delete(id);
	}

	@Override
	public Page<Recharge> findAll(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<Recharge> page = rechargeRepository.findAll(pageable);
		return page;
	}

}
