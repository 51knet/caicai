package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.User;

public interface UserService {
	boolean login(String email, String password);
	boolean activate(String email);
	User findOne(Long id);
	User findByRandomUrl(String randomUrl);
	User findByEmailAddress(String emailAddress);
	User createUser(User usr);
	User updateUser(User usr);
	List<User> findAllUsers();
	boolean usableUrl(String url);
}
