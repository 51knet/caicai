package com.knet51.courses.jpa.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.UserDao;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.repository.user.UserRepository;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDao userDao; 
	
	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User createUser(User usr) {
		return userRepository.save(usr);
	}

	@Override
	public User updateUser(User usr) {
		return userRepository.save(usr);
	}

	@Override
	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getValidUser(String email, String psw) {
		return userRepository.getValidUser(email, psw);
	}

	@Override
	public User getValidEmail(String email) {
		return userRepository.getValidEmail(email);
	}

	@Override
	public boolean login(String email, String password) {
		HashMap<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("email", email);
		paramsMap.put("password", password);

		User user = null;
		try {
			user = userDao.getSingleResultByParamsMap(paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (user != null);
	}
}
