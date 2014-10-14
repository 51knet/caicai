package com.knet51.diplomat.jpa.services.achievement;

import java.util.List;
import org.springframework.data.domain.Page;
import com.knet51.ccweb.jpa.entities.Enterprise;
import com.knet51.ccweb.jpa.entities.enterprise.EnterpriseIntro;

public interface EnterpriseIntroService {
	
	EnterpriseIntro save(EnterpriseIntro enterpriseIntro,Enterprise enterprise);

	EnterpriseIntro update(EnterpriseIntro enterpriseIntro);

	EnterpriseIntro findOneById(Long id);

	void deleteById(Long id);
	
	List<EnterpriseIntro> getAllIntroById(Long Id); 
	
	Page<EnterpriseIntro> findAllIntroByEnterprise(int pageNum, int pageSize, Enterprise enterprise);

}
