package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.FriendsRelated;

public interface FriendsRelateService {
	
	FriendsRelated save(FriendsRelated friendsRelated);

	FriendsRelated update(FriendsRelated friendsRelated);

	FriendsRelated findOneById(Long id);

	void deleteById(Long id);
	
	List<FriendsRelated> getAllFans(Long hostId); 
	
	List<FriendsRelated> getAllHost(Long followId);
	
	/**
	 * check if the followId(normally the user in session) follows the hostId(teacher)
	 * @param followId
	 * @param hostId
	 * @return
	 */
	boolean isTheFollower(Long followId,Long hostId);
	
	List<UserInfo> getAllFansInfo(Long user_id);
	
	List<UserInfo> getAllHostInfo(Long user_id);
	
}
