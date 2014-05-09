package com.knet51.ccweb.jpa.repository.investcompany;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.investcompany.InvestSuccessCase;

public interface InvestSuccessCaseRepository extends JpaRepository<InvestSuccessCase, Long> {
	Page<InvestSuccessCase> findSuccessCaseByUser(User user, Pageable pageable);
}
