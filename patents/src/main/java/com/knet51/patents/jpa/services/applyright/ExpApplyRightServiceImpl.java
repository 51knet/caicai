package com.knet51.patents.jpa.services.applyright;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.applyright.ExpApplyRight;
import com.knet51.ccweb.jpa.repository.applyright.ExpApplyRightRepository;
@Service("expApplyRightService")
public class ExpApplyRightServiceImpl implements ExpApplyRightService {
	@Autowired
	private ExpApplyRightRepository expRepository;
	@Override
	public ExpApplyRight findOne(Long id) {
		return expRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		expRepository.delete(id);
	}

	@Override
	public ExpApplyRight update(ExpApplyRight expRight) {
		return expRepository.saveAndFlush(expRight);
	}

	@Override
	public ExpApplyRight create(ExpApplyRight expRight) {
		return expRepository.saveAndFlush(expRight);
	}

}
