package com.knet51.courses.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.UserRight;
import com.knet51.ccweb.jpa.repository.UserRightRepository;

@Service("userRightService")
public class UserRightServiceImpl implements UserRightService {
	@Autowired
	private UserRightRepository rightRepository; 

	@Override
	public List<UserRight> findUserRightListByUser(User user) {
		
		return rightRepository.findUserRightListByUser(user);
	}



}
