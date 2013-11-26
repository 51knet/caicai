package com.knet51.courses.jpa.services.patent;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.entities.patent.PatentType;

public interface PatentService {
	Page<Patent> findPatentByUser(int pageNum , int pageSize, User user);
	List<Patent> findPatentListByUser(User user);
	
	Page<Patent> findPatentByPatentField(int pageNum , int pageSize,String patentField);
	
	List<Patent> findPatentList();
 	Patent findOne(String patentNum);
 	Page<Patent> findAll(int pageNum , int pageSize);
 	
 	Page<Patent> searchPatent(int pageNum , int pageSize,PatentType patentType, String search,String params);
 	List<Patent> searchPatentList(PatentType patentType,String search, String params);
 	
 	
 	
}
