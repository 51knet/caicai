package com.knet51.ccweb.jpa.dao;
import java.util.List;

import com.knet51.ccweb.jpa.entities.FriendsRelated;

public interface FriendsRelatedDao {
	FriendsRelated save(FriendsRelated friendsRelated);

	FriendsRelated update(FriendsRelated friendsRelated);

	FriendsRelated findOneById(Long id);

	void deleteById(Long id);
	
	List<FriendsRelated> getAllFollow(Long hostId); 
	
	List<FriendsRelated> getAllHost(Long followId);
	int getFollowById(Long hostId,Long followId);
	
	FriendsRelated findOneByFollowIdAndHostId(Long hostId,Long followId);
}
