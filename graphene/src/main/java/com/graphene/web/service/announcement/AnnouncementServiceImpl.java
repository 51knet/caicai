package com.graphene.web.service.announcement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graphene.web.jpa.entity.announcement.Announcement;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.jpa.repository.announcement.AnnouncementRepository;

@Transactional
@Service("announcementService")
public class AnnouncementServiceImpl implements AnnouncementService {
	
	@Autowired
	private AnnouncementRepository annoRepository;
	


	@Override
	public Announcement createAnnouncement(Announcement ann) {
		return annoRepository.save(ann);
	}

	@Override
	public Announcement updateAnnouncement(Announcement ann) {
		return annoRepository.save(ann);
	}

	@Override
	public void deleAnnouncementById(Long id) {
		annoRepository.delete(id);
	}



	@Override
	public Page<Announcement> findAllAnnoByUser(int pageNumber, int pageSize,
			User user) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<Announcement> onePage = annoRepository.findAnnoByUser(user, dateDesc);
		return onePage;
	}


	@Override
	public Page<Announcement> findAllAnnoForSuperAdmin(int pageNumber, int pageSize) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<Announcement> onePage = annoRepository.findAll(dateDesc);
		return onePage;
	}

	@Override
	public List<Announcement> findAllForSuperAdmin() {
		return annoRepository.findAll();
	}

	@Override
	public Page<Announcement> findAnnoByUserAndType(int pageNumber,
			int pageSize, User user, String type) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<Announcement> page = annoRepository.findAnnoByUserAndType(user, type, dateDesc);
		return page;
	}

	@Override
	public Announcement findOne(Long id) {
		
		return annoRepository.findOne(id);
	}

	@Override
	public Page<Announcement> findAllByType(int pageNumber, int pageSize,
			String type) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<Announcement> page = annoRepository.findAnnoByType(type, dateDesc);
		return page;
	}

}
