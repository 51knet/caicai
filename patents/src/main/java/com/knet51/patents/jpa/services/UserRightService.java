package com.knet51.patents.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.UserRight;

public interface UserRightService {
	UserRight findOne(Long id);
	UserRight create(UserRight userRight);
	UserRight update(UserRight userRight);
	void delete(Long id);
	List<UserRight> findUserRightListByUser(User user);
}
