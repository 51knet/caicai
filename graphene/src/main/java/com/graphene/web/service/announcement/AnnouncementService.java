package com.graphene.web.service.announcement;

import java.util.List;

import org.springframework.data.domain.Page;

import com.graphene.web.jpa.entity.announcement.Announcement;
import com.graphene.web.jpa.entity.user.User;

public interface AnnouncementService {
	
	Page<Announcement> findAllAnnoByUser(int pageNum, int pageSize, User user);
	
	Announcement createAnnouncement(Announcement ann);
	
	Announcement updateAnnouncement(Announcement ann);
	
	Announcement findOne(Long id);
	
	void deleAnnouncementById(Long id);
	Page<Announcement> findAllAnnoForSuperAdmin(int pageNum, int pageSize);
	List<Announcement> findAllForSuperAdmin();
	
	Page<Announcement> findAnnoByUserAndType(int pageNumber,int pageSize, User user, String type);
	Page<Announcement> findAllByType(int pageNumber,int pageSize,String type);
}
