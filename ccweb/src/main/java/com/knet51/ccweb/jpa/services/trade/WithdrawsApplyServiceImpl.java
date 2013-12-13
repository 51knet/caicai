package com.knet51.ccweb.jpa.services.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.WithdrawsApply;
import com.knet51.ccweb.jpa.repository.WithdrawsApplyRepository;
@Service("withdrawsApplyService")
public class WithdrawsApplyServiceImpl implements WithdrawsApplyService {
	@Autowired
	private WithdrawsApplyRepository applyRepository;
	
	@Override
	public Page<WithdrawsApply> findAllByUser(int pageNumber, int pageSize,
			User user) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return applyRepository.findAllByUser(user, pageable);
	}

	@Override
	public Page<WithdrawsApply> findAll(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return applyRepository.findAll(pageable);
	}

	@Override
	public WithdrawsApply createWithdrawsApply(WithdrawsApply withdrawsApply) {
		return applyRepository.save(withdrawsApply);
	}

	@Override
	public void deleteWithdrawsApply(Long id) {
		applyRepository.delete(id);
	}

	@Override
	public WithdrawsApply findOneById(Long id) {
		return applyRepository.findOne(id);
	}

}
