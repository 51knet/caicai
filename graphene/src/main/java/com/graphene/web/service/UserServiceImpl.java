package com.graphene.web.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graphene.web.jpa.dao.UserDao;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.jpa.repository.UserRepository;


@Service("userService")
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRepository userRepository;

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
		return userRepository.findOne(id);
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User findByRandomUrl(String randomUrl) {
		User usr = userDao.queryStringBySql("randomUrl", randomUrl);
		return usr;
	}

	public User findByForgotPsw(String forgotPsw) {
		User usr = userDao.queryStringBySql("forgotPsw", forgotPsw);
		return usr;
	}

	@Override
	public User findByEmailAddress(String emailAddress) {
		User usr = userDao.queryStringBySql("email", emailAddress);
		return usr;
	}

	@Override
	public User updateUser(User usr) {
		return userRepository.save(usr);
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
		int count = userDao.getcountByEmail(email);
		return count;
	}

	@Override
	public User updateUserAvatar(Long id, String avatarRelativePath) {
		User user = userDao.findById(id);
		user.setPhoto_url(avatarRelativePath);
		user = userDao.update(user);
		return user;
	}

	@Override
	public Page<User> findUserByRole(String role, int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize,
				Direction.DESC, "id");
		Page<User> onePage = userRepository.findUserByRole(role, pageable);
		return onePage;
	}

	@Override
	public User findUserBy3pp(String vendor, String name) {
		String queryString = "select c from User c where c.thirdParty='"
				+ vendor;
		queryString += "' and ";
		queryString += "c.thirdPartyName='" + name;
		queryString += "'";
		User usr = null;
		try {
			usr = userDao.getSingleResultByQuery(queryString);
		} catch (Exception e) {
			usr = null;
		}
		return usr;
	}

	@Override
	public List<User> findUserByRole(String role) {
		List<User> userList = userRepository.findUserByRole(role);
		return userList;
	}

	@Override
	public List<User> findUserByNameLike(String name) {
		return userRepository.findUserByNameLike(name);
	}

	@Override
	public Page<User> findUserByRightAndRole(String right, String role, int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<User> page = userRepository.findUserByMyrightAndRole(right,role, pageable);
		return page;
	}


}
