package com.knet51.courses.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.User;

public interface UserService {
	User findOne(Long id);
	User createUser(User usr);
	User updateUser(User usr);
	User getValidUser(String email, String psw);
	User getValidEmail(String email);
	List<User> findAllUser();
	boolean login(String email, String password);
}
