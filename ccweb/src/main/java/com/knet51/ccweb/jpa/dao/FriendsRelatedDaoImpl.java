package com.knet51.ccweb.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.knet51.ccweb.jpa.entities.Friends_Related;

@Repository("friendsRelateDao")
public class FriendsRelatedDaoImpl implements FriendsRelatedDao {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Friends_Related save(Friends_Related friendsRelated) {
		em.persist(friendsRelated);
		return friendsRelated;
	}

	@Override
	public Friends_Related update(Friends_Related friendsRelated) {
		em.merge(friendsRelated);
		return friendsRelated;
	}

	@Override
	public Friends_Related findOneById(Long id) {
		return null;
	}

	@Override
	public void deleteById(Long id) {
		Friends_Related friendsRelated = em.find(Friends_Related.class, id);
		em.remove(friendsRelated);
	}

	@Override
	public List<Friends_Related> getAllFollow(Long hostId) {
		@SuppressWarnings("unchecked")
		List<Friends_Related> follow = em.createQuery("from Friends_Related where type=1 and  host_id="+hostId).getResultList();
		return follow;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Friends_Related> getAllHost(Long followId) {
		List<Friends_Related> host = em.createQuery("from Friends_Related where type=1 and follow_id="+followId).getResultList();
		return host;
	}
	
	

}
