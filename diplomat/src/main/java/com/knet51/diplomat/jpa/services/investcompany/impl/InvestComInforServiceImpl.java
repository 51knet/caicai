package com.knet51.diplomat.jpa.services.investcompany.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.investcompany.InvestComInfor;
import com.knet51.ccweb.jpa.repository.investcompany.InvestComInforRepository;
import com.knet51.diplomat.jpa.services.investcompany.InvestComInforService;
@Service("investComInforService")
public class InvestComInforServiceImpl implements InvestComInforService {
	@Autowired
	private InvestComInforRepository comInforRepository;
	@Override
	public InvestComInfor findByUser(User user) {
		
		return comInforRepository.findInforByUser(user);
	}

	@Override
	public InvestComInfor create(InvestComInfor investComInfor) {
		
		return comInforRepository.saveAndFlush(investComInfor);
	}

	@Override
	public InvestComInfor update(InvestComInfor investComInfor) {

		return comInforRepository.saveAndFlush(investComInfor);
	}

}
