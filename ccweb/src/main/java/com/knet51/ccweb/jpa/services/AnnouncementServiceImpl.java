package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.knet51.ccweb.jpa.dao.AnnouncementDao;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.repository.AnnouncementRepository;



@Transactional
@Service("announcementService")
public class AnnouncementServiceImpl implements AnnouncementService {
	@Autowired
	private AnnouncementDao annDao;
	
	@Autowired
	private AnnouncementRepository repository;

	@Override
	public List<Announcement> findAllByUid(Long uId) {
		return annDao.listAllByUid(uId);
	}

	@Override
	public Announcement findOneById(Long id) {
		return annDao.findOneById(id);
	}

	@Override
	public Announcement createAnnouncement(Announcement ann, User user) {
		
		ann.setUser(user);
		return annDao.save(ann);
	}

	@Override
	public Announcement updateAnnouncement(Announcement ann) {
		return annDao.update(ann);
	}

	@Override
	public void deleAnnouncementById(Long id) {
		annDao.deleteById(id);
	}

	@Override
	public List<Announcement> findAllByUid() {
		// TODO Auto-generated method stub
		return null;
	}

}
