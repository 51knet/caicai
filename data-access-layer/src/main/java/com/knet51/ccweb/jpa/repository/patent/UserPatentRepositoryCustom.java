package com.knet51.ccweb.jpa.repository.patent;

import java.util.List;

import com.knet51.ccweb.jpa.entities.patent.Patent;

public interface UserPatentRepositoryCustom {
	List<Patent> findPatentPage(Long type_id,String search, String params,int pageNumber,int pageSize);
	List<Patent> findPatentList(Long type_id,String search, String params);// count the total size
	
	List<Patent> findPatentPageDetail();
}
