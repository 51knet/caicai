package com.knet51.ccweb.jpa.services;

import java.util.List;


import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;


public interface AnnouncementService {
	
	List<Announcement> findAllByUid(Long uId);
	
	List<Announcement> findAllByUid();
	
	Announcement findOneById(Long id);
	
	Announcement createAnnouncement(Announcement ann,User user);
	
	Announcement updateAnnouncement(Announcement ann);
	
	void deleAnnouncementById(Long id);
}
