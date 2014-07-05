package com.graphene.web.jpa.repository.announcement;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.graphene.web.jpa.entity.announcement.Announcement;
import com.graphene.web.jpa.entity.user.User;


public interface AnnouncementRepository  extends JpaRepository<Announcement, Long>, JpaSpecificationExecutor<Announcement>{
	
	Page<Announcement> findAnnoByUser(User user, Pageable pageable);
	List<Announcement> findAnnoByUser(User user);
	
	Page<Announcement> findAll(Pageable pageable);
	
	Page<Announcement> findAnnoByUserAndType(User user, String type, Pageable pageable);
	Page<Announcement> findAnnoByType(String type, Pageable pageable);
}
