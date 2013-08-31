package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.FriendsRelated;
import com.knet51.ccweb.jpa.entities.User;

public interface FriendsRelateService {
	
	FriendsRelated save(FriendsRelated friendsRelated);

	FriendsRelated update(FriendsRelated friendsRelated);

	FriendsRelated findOneById(Long id);
	
	FriendsRelated findOneByHostIdAndFollowId(Long host_id,Long follow_id);

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
	
	List<User> getAllFansInfo(Long user_id);
	
	List<User> getAllFansInfo(Long user_id, String role);
	
	List<User> getAllHostInfo(Long user_id);
	
	List<User> getAllHostInfo(Long user_id, String role);
	
	List<User> getAllMatesInfo(Long user_id);
}
