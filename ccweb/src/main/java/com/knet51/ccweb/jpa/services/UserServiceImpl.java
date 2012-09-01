package com.knet51.ccweb.jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.UserDao;
import com.knet51.ccweb.jpa.entities.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public boolean login(String email, String password) {
		return false;
	}

	@Override
	public User findOne(Long id) {
		return userDao.findById(id);
	}

	@Override
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
	public User createUser(User usr) {
		return userDao.save(usr);
	}
}
