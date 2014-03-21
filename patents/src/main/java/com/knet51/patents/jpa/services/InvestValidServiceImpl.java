package com.knet51.patents.jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.InvestValid;
import com.knet51.ccweb.jpa.repository.InvestValidRepository;
@Service("investValidService")
public class InvestValidServiceImpl implements InvestValidService {
	@Autowired
	private InvestValidRepository validRepository;
	@Override
	public InvestValid create(InvestValid investValid) {
		return validRepository.save(investValid);
	}

	@Override
	public InvestValid update(InvestValid investValid) {
		return validRepository.saveAndFlush(investValid);
	}

	@Override
	public void delete(Long id) {
		validRepository.delete(id);

	}

	@Override
	public InvestValid find(Long id) {
		return validRepository.findOne(id);
	}

	@Override
	public Page<InvestValid> findInvestValidPage(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return validRepository.findAll(pageable);
	}

}
