package com.knet51.diplomat.jpa.services.announcement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.knet51.ccweb.jpa.dao.AnnouncementDao;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.repository.announcement.AnnouncementRepository;

@Transactional
@Service("announcementService")
public class AnnouncementServiceImpl implements AnnouncementService {
	@Autowired
	private AnnouncementDao annDao;

	@Autowired
	private AnnouncementRepository annoRepository;
	
	@Override
	public List<Announcement> findAllByUid(Long uId) {
		return annDao.listAllByUid(uId);
	}

	@Override
	public Announcement findOneById(Long id) {
		return annDao.findOneById(id);
	}

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
	public Announcement findLatestByUid(Long uId) {
		List<Announcement> annList = findAllByUid(uId);
		int size = annList.size();
		return size == 0 ? null : annDao.listAllByUid(uId).get(0);
	}

	@Override
	public Page<Announcement> findAllAnnoByUser(int pageNumber, int pageSize,
			User user) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<Announcement> onePage = annoRepository.findAnnoByUserAndForbiddenIsNull(user, dateDesc);
		return onePage;
	}

	@Override
	public List<Announcement> findAnnoByUserAndId(User user, Long id) {
		List<Announcement> annoList = annoRepository.findAnnoByUserAndIdAndForbiddenIsNull(user, id);
		return annoList;
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
	public Page<Announcement> findAnnoByUserAndCode(int pageNumber,
			int pageSize, User user, Integer code) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<Announcement> page = annoRepository.findAnnoByUserAndCode(user, code, dateDesc);
		return page;
	}

}
