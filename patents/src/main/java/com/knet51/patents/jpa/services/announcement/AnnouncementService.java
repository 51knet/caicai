package com.knet51.patents.jpa.services.announcement;

import java.util.List;

import org.springframework.data.domain.Page;


import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;


public interface AnnouncementService {
	
	List<Announcement> findAllByUid(Long uId);
	
	Page<Announcement> findAllAnnoByUser(int pageNum, int pageSize, User user);
	
	Announcement findLatestByUid(Long uId);
	
	Announcement findOneById(Long id);
	
	Announcement createAnnouncement(Announcement ann);
	
	Announcement updateAnnouncement(Announcement ann);
	
	void deleAnnouncementById(Long id);
	
	List<Announcement> findAnnoByUserAndId(User user,Long id);
	
	Page<Announcement> findAllAnnoForSuperAdmin(int pageNum, int pageSize);
	List<Announcement> findAllForSuperAdmin();
	
	Page<Announcement> findAnnoByUserAndCode(int pageNumber,int pageSize, User user, Integer code);
}
