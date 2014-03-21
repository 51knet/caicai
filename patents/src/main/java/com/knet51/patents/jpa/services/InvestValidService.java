package com.knet51.patents.jpa.services;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.InvestValid;

public interface InvestValidService {
	InvestValid create(InvestValid investValid);
	InvestValid update(InvestValid investValid);
	void delete(Long id);
	InvestValid find(Long id);
	
	Page<InvestValid> findInvestValidPage(int pageNumber,int pageSize);
}
