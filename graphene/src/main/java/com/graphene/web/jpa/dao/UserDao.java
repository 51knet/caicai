package com.graphene.web.jpa.dao;

import java.util.List;
import java.util.Map;

import com.graphene.web.jpa.entity.user.User;


public interface UserDao {
	User save(User user);

	User update(User user);

	User findById(Long id);

	boolean delete(User user);

	boolean deleteById(Long id);

	User queryStringBySql(String col, String value);
	
	User getSingleResultByQuery(String query);
	
	User getSingleResultByParamsMap(Map<String,String> paramsMap);

	List<User> list();
	
	//UserBeans getUserName(Long id);
	int getcountByEmail(String email);
	
}
