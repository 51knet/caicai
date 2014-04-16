package com.knet51.courses.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.UserRight;

public interface UserRightService {
	List<UserRight> findUserRightListByUser(User user);
}
