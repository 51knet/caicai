package com.knet51.patents.jpa.services.investcompany;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.investcompany.InvestComInfor;

public interface InvestComInforService {
	InvestComInfor findByUser(User user);
	InvestComInfor create(InvestComInfor investComInfor);
	InvestComInfor update(InvestComInfor investComInfor);
}
