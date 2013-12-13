package com.knet51.ccweb.jpa.services.enterprise;

import com.knet51.ccweb.jpa.entities.Enterprise;

public interface EnterpriseService {
	Enterprise findOne(Long id);
	Enterprise createEnterprise(Enterprise usr);
	Enterprise updateEnterprise(Enterprise usr);
}
