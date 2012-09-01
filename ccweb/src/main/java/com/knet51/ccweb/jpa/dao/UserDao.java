package com.knet51.ccweb.jpa.dao;

import com.knet51.ccweb.jpa.entities.User;

public interface UserDao {
	User save(User user);

	User update(User user);

	User findById(Long id);

	boolean delete(User user);

	boolean deleteById(Long id);

	User queryStringBySql(String col, String value);
}
