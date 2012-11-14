package com.knet51.ccweb.jpa.dao;
import java.util.List;

import com.knet51.ccweb.jpa.entities.Friends_Related;

public interface FriendsRelatedDao {
	Friends_Related save(Friends_Related friendsRelated);

	Friends_Related update(Friends_Related friendsRelated);

	Friends_Related findOneById(Long id);

	void deleteById(Long id);
	
	List<Friends_Related> getAllFollow(Long hostId); 
	
	List<Friends_Related> getAllHost(Long followId);
	public int getFollowById(Long hostId,Long followId);
}
