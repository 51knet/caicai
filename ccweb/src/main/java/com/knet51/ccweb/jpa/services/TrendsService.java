package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.timeline.Trends;

public interface TrendsService {
	Trends createTrends(Trends trends);
	void deleteTrendsById(Long trend_id);
	List<Trends> showTrendsByUser(User user);
	
	Page<Trends> showTrendsByUserId(int pageNum, int pageSize, Long u_id);
	
	Trends findOneById(Long trend_id);
	
	List<Trends> showAllTrendsByUserId(Long u_id);
	
	List<Trends> showAllTrendsByUserIdAndRole(Long u_id,String role);
	
	List<Trends> showAllTrendsByUserIdAndRoleAndVariety(Long u_id,String role,String variety);
	
	List<Trends> showAllTrendsByUserIdAndVariety(Long u_id,String variety);
	
	List<Trends> showAllTeacherTrendsByUserId(Long u_id);
}
