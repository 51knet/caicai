package com.knet51.ccweb.jpa.services.promotion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.FriendsRelateService;
import com.knet51.ccweb.jpa.services.UserService;
@Transactional
@Service("UserRecommendService")
public class UserRecommendServiceImpl implements UserRecommendService{

	@Autowired
	private UserService userService;
	private FriendsRelateService friendsService;

	@Override
	public List<User> getRandomUsers(String role) {
		List<User> userList = userService.findUserByRole(role);
		Collections.shuffle(userList);
		return userList;
	}

	@Override
	public List<User> getRandomUsers(String role, int count) {
		if (count > 0) {
			List<User> userList = getRandomUsers(role);
			return userList.subList(0, count);
		} else {
			return null;
		}
	}

	@Override
	public List<User> getRecommendTeachersFromMyTeacher(Long id) {
		List<User> userList = getRandomUsersFriends("teacher", "teacher", id);
		return userList;
	}

	@Override
	public List<User> getRecommendTeachersFromMyTeacher(Long id, int count) {
		List<User> userList = getRandomUsersFriends("teacher", "teacher", id,
				count);
		return userList;
	}

	@Override
	public List<User> getRecommendTeachersFromFriendsTeacher(Long id) {
		List<User> userList = getRandomUsersFriends("user", "teacher", id);
		return userList;
	}

	@Override
	public List<User> getRecommendTeachersFromFriendsTeacher(Long id, int count) {
		List<User> userList = getRandomUsersFriends("user", "teacher", id,
				count);
		return userList;
	}

	@Override
	public List<User> getRandomUsersFriends(String friendRole,
			String targetRole, Long id) {
		List<User> userList = new ArrayList<User>();
		List<User> hostList = friendsService.getAllHostInfo(id, friendRole);
		Set<User> userSet = new HashSet<User>();
		for (User user : hostList) {
			userSet.addAll(friendsService.getAllHostInfo(user.getId(),
					targetRole));
			userSet.addAll(friendsService.getAllFansInfo(user.getId(),
					targetRole));
		}
		userList.addAll(userSet);
		Collections.shuffle(userList);
		return userList;
	}

	@Override
	public List<User> getRandomUsersFriends(String friendRole,
			String targetRole, Long id, int count) {
		List<User> userList = new ArrayList<User>();
		if (count > 0) {
			userList = getRandomUsersFriends(friendRole, targetRole, id);
			return userList.subList(0, count);
		} else {
			return null;
		}
	}

}
