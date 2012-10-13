package com.knet51.ccweb.jpa.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.knet51.ccweb.jpa.entities.Announcement;

@Repository("announcementDao")
public class AnnouncementDaoImpl implements AnnouncementDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Announcement save(Announcement announcement) {
		em.persist(announcement);
		return announcement;
	}

	@Override
	public Announcement update(Announcement announcement) {
		em.merge(announcement);
		return announcement;
	}

	@Override
	public Announcement findOneById(Long id) {
		Announcement announcement = em.find(Announcement.class, id);
		return announcement;
	}

	@Override
	public boolean deleteAllByUid(Long uId) {
		return false;
	}

	@Override
	public void deleteById(Long id) {
		Announcement ann = em.find(Announcement.class, id);
		em.remove(ann);
	}

	@Override
	public Announcement queryStringBySql(String col, String value) {
		return null;
	}

	@Override
	public Announcement getSingleResultByQuery(String query) {
		return null;
	}

	@Override
	public Announcement getSingleResultByParamsMap(Map<String, String> paramsMap) {
		return null;
	}

	@Override
	public List<Announcement>  listAllByUid(Long uId) {
		
		List<Announcement> list =  em.createQuery("from Announcement where user_id="+uId, Announcement.class).getResultList();
		return list;
	}





}
