package com.knet51.ccweb.jpa.services;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.UserDao;
import com.knet51.ccweb.jpa.entities.User;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public boolean login(String email, String password) {
		HashMap<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("email", email);
		paramsMap.put("password", password);

		User user = null;
		try {
			user = userDao.getSingleResultByParamsMap(paramsMap);
		} catch (Exception e) {
			logger.warn("login failed", e);
		}
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

	@Override
	public List<User> findAllUsers() {
		List<User> userList = userDao.list();
		return userList;
	}

	@Override
	public boolean usableUrl(String url) {
		User usr = findBySelfUrl(url);
		return usr == null ? true : false;
	}

	@Override
	public User findBySelfUrl(String url) {
		User usr = userDao.queryStringBySql("self_url", url);
		return usr;
	}

	@Override
	public int getCountByEmail(String email) {
		int count =userDao.getcountByEmail(email);
		return count;
	}
}
