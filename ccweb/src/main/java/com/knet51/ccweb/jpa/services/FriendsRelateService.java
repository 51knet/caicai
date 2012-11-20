package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.FriendsRelated;
import com.knet51.ccweb.jpa.entities.User;

public interface FriendsRelateService {
	
	FriendsRelated save(FriendsRelated friendsRelated);

	FriendsRelated update(FriendsRelated friendsRelated);

	FriendsRelated findOneById(Long id);

	void deleteById(Long id);
	
	List<FriendsRelated> getAllFans(Long hostId); 
	
	List<FriendsRelated> getAllHost(Long followId);
	
	int getFollowById(Long followId,Long hostId);
	
	List<UserInfo> getAllFansInfo(Long user_id);
	
	List<UserInfo> getAllHostInfo(Long user_id);
	
}
