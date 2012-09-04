package com.knet51.ccweb.jpa.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.UserDao;
import com.knet51.ccweb.jpa.entities.User;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public boolean login(String email, String password) {
		HashMap<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("email", email);
		paramsMap.put("password", password);

		User user = userDao.getSingleResultByParamsMap(paramsMap);

		return (user != null);
	}

	@Override
	public boolean activate(String email) {
		User user = findByEmailAddress(email);
		boolean isActivate;
		if (user != null) {
			isActivate = "pass".equals(user.getRandomUrl());
		} else {
			isActivate = false;
		}
		return isActivate;
	}

	@Override
	public User findOne(Long id) {
		return userDao.findById(id);
	}

	@Override
	public User createUser(User user) {
		return userDao.save(user);
	}

	public User findByRandomUrl(String randomUrl) {
		User usr = userDao.queryStringBySql("randomUrl", randomUrl);
		return usr;
	}

	@Override
	public User findByEmailAddress(String emailAddress) {
		User usr = userDao.queryStringBySql("email", emailAddress);
		return usr;
	}

	@Override
	public User updateUser(User usr) {
		return userDao.update(usr);
	}
}
