package com.knet51.patents.jpa.services.patent;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.Patent;

public interface PatentService {
	Page<Patent> findPatentByUser(int pageNum , int pageSize, User user);
	Page<Patent> findPatent(int pageNum, int pageSize);
	List<Patent> findPatentListByUser(User user);
 	void create(Patent patent);
 	Patent update(Patent patent);
 	void delete(String patentNum);
 	Patent findOne(String patentNum);
 	
 	Page<Patent> findPatentByStatus(int pageNum , int pageSize,Integer status);
 	Page<Patent> findPatentByCountry(int pageNum , int pageSize,Integer country);
	Page<Patent> findPatentByFocus(int pageNum , int pageSize,Integer focus);
	Page<Patent> findPatentByPatentNumLike(int pageNum , int pageSize,String patentNum);
	Page<Patent> findPatgentByPatentNameLike(int pageNum , int pageSize,String patentName);
 	
}
