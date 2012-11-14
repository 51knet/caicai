package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.FriendsRelatedDao;
import com.knet51.ccweb.jpa.entities.Friends_Related;

@Transactional
@Service("friendsRelateService")
public class FriendsRelateServiceImpl implements FriendsRelateService {
	@Autowired
	private FriendsRelatedDao friendsRelatedDao;

	@Override
	public Friends_Related save(Friends_Related friendsRelated) {
		return friendsRelatedDao.save(friendsRelated);
	}

	@Override
	public Friends_Related update(Friends_Related friendsRelated) {
		return friendsRelatedDao.update(friendsRelated);
	}

	@Override
	public Friends_Related findOneById(Long id) {
		return null;
	}

	@Override
	public void deleteById(Long id) {
		friendsRelatedDao.deleteById(id);
	}

	@Override
	public List<Friends_Related> getAllFollow(Long id) {
		return friendsRelatedDao.getAllFollow(id);
	}

	@Override
	public List<Friends_Related> getAllHost(Long followId) {
		return friendsRelatedDao.getAllHost(followId);
	}
	
	/**
	 * 判断followId是否存在以此来验证是否对其关注
	 */
	@Override
	public int getFollowById(Long followId,Long hostId) {
		int followValue=friendsRelatedDao.getFollowById(hostId,followId);
		return followValue;
	}

}
