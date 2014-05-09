package com.knet51.patents.jpa.services.investcompany;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.investcompany.InvestSuccessCase;

public interface InvestSuccessCaseService {
	void delete(Long id);
	Page<InvestSuccessCase> findSuccessCaseByUser(User user, int pageNumber,int pageSize);
	InvestSuccessCase create(InvestSuccessCase successCase);
	InvestSuccessCase update(InvestSuccessCase successCase);
	InvestSuccessCase findOne(Long id);
}
