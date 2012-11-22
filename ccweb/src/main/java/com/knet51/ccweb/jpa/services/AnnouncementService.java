package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.data.domain.Page;


import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;


public interface AnnouncementService {
	
	List<Announcement> findAllByUid(Long uId);
	
	Page<Announcement> findAllAnnoById(int pageNum, int pageSize, User user);
	
	Announcement findLatestByUid(Long uId);
	
	Announcement findOneById(Long id);
	
	Announcement createAnnouncement(Announcement ann);
	
	Announcement updateAnnouncement(Announcement ann);
	
	void deleAnnouncementById(Long id);
}
