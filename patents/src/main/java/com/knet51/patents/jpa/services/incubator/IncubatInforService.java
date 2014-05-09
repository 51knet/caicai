package com.knet51.patents.jpa.services.incubator;

import com.knet51.ccweb.jpa.entities.incubator.IncubatInfor;

public interface IncubatInforService {
	IncubatInfor findOne(Long id);
	IncubatInfor create(IncubatInfor incubatInfor);
	IncubatInfor update(IncubatInfor incubatInfor);
	void delete(Long id);
}
