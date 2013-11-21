package com.knet51.ccweb.jpa.services.patent;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.Patent;

public interface PatentService {
	Page<Patent> findPatentByUser(int pageNum , int pageSize, User user);
	List<Patent> findPatentListByUser(User user);
 	void create(Patent patent);
 	void update(Patent patent);
 	void delete(String patentNum);
 	Patent findOne(String patentNum);
 	
 	
}
