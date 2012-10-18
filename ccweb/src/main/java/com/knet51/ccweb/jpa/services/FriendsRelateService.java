package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.Friends_Related;

public interface FriendsRelateService {
	Friends_Related save(Friends_Related friendsRelated);

	Friends_Related update(Friends_Related friendsRelated);

	Friends_Related findOneById(Long id);

	void deleteById(Long id);
	
	List<Friends_Related> getAllFollow(Long id); 
}
