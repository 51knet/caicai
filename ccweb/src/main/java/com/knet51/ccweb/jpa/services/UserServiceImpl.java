package com.knet51.ccweb.jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.UserDao;
import com.knet51.ccweb.jpa.entities.User;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
	// @Autowired
	// private UserRepository repo;
	//
	// @Override
	// public boolean login(String email, String password) {
	// // TODO Auto-generated method stub
	// return false;
	// }
	//
	// @Override
	// public User findOne(Long id) {
	// return repo.findOne(id);
	// }

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
	public User createUser(User user) {
		return userDao.save(user);
	}
	}
