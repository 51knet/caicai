package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.FriendsRelated;

public interface FriendsRelateService {
	FriendsRelated save(FriendsRelated friendsRelated);

	FriendsRelated update(FriendsRelated friendsRelated);

	FriendsRelated findOneById(Long id);

	void deleteById(Long id);
	
	List<FriendsRelated> getAllFollow(Long hostId); 
	
	List<FriendsRelated> getAllHost(Long followId);
	public int getFollowById(Long followId,Long hostId);
	
}
