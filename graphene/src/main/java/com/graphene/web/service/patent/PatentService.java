package com.graphene.web.service.patent;

import java.util.List;

import org.springframework.data.domain.Page;

import com.graphene.web.jpa.entity.patent.Patent;
import com.graphene.web.jpa.entity.user.User;


public interface PatentService {
	Page<Patent> findPatentByUser(int pageNum , int pageSize, User user);
	Page<Patent> findPatent(int pageNum, int pageSize);
	List<Patent> findPatentListByUser(User user);
 	void create(Patent patent);
 	Patent update(Patent patent);
 	void delete(Long id);
 	Patent findOne(Long id);
 	Page<Patent> findPatentByUserAndStatus(int pageNum , int pageSize, User user, Integer status);
 	Page<Patent> findPatentByStatus(int pageNum , int pageSize,Integer status);
 	Page<Patent> findPatentByCountryAndStatus(int pageNum , int pageSize,Integer country,Integer status);
	Page<Patent> findPatentByFocus(int pageNum , int pageSize,Integer focus);
	Page<Patent> findPatentByPatentNumLike(int pageNum , int pageSize,String patentNum);
	Page<Patent> findPatentByPatentNameLike(int pageNum , int pageSize,String patentName);
 	
}
