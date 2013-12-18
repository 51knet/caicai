package com.knet51.patents.jpa.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.User;

public interface UserService {
	boolean login(String email, String password);
	boolean activate(String email);
	User findOne(Long id);
	User findByRandomUrl(String randomUrl);
	User findByEmailAddress(String emailAddress);
	User createUser(User usr);
	User updateUser(User usr);
	/**
	 * 
	 * @param id
	 * @param avatarRelativePath - /resources/attached/${id}/avatar_large.jpg
	 * @return
	 */
	User updateUserAvatar(Long id, String avatarRelativePath);
	List<User> findAllUsers();
	boolean usableUrl(String url);
	User findBySelfUrl(String url);
	int getCountByEmail(String email);
	
	Page<User> findUserByRole(String role, int pageNumber, int pageSize);
	List<User> findUserByRole(String role);
	User findUserBy3pp(String vendor, String name);
	
	List<User> findUserByNameLike(String name);
	
}
