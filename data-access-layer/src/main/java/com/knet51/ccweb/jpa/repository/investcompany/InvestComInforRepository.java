package com.knet51.ccweb.jpa.repository.investcompany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.investcompany.InvestComInfor;

public interface InvestComInforRepository extends JpaRepository<InvestComInfor, Long>, JpaSpecificationExecutor<InvestComInfor> {
	InvestComInfor findInforByUser(User user);
}
