package com.knet51.patents.jpa.services.investcompany.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.investcompany.InvestSuccessCase;
import com.knet51.ccweb.jpa.repository.investcompany.InvestSuccessCaseRepository;
import com.knet51.patents.jpa.services.investcompany.InvestSuccessCaseService;
@Service("investSuccessCaseService")
public class InvestSuccessCaseServiceImpl implements InvestSuccessCaseService {
	@Autowired
	private InvestSuccessCaseRepository successCaseRepository;
	@Override
	public void delete(Long id) {
		successCaseRepository.delete(id);
	}

	@Override
	public Page<InvestSuccessCase> findSuccessCaseByUser(User user,
			int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<InvestSuccessCase> page = successCaseRepository.findSuccessCaseByUser(user, pageable);
		return page;
	}

	@Override
	public InvestSuccessCase create(InvestSuccessCase successCase) {
		
		return successCaseRepository.saveAndFlush(successCase);
	}

	@Override
	public InvestSuccessCase update(InvestSuccessCase successCase) {
		return successCaseRepository.saveAndFlush(successCase);
	}

	@Override
	public InvestSuccessCase findOne(Long id) {
		
		InvestSuccessCase successCase = successCaseRepository.findOne(id);
		successCase.getUser();
		return successCase;
	}

}
