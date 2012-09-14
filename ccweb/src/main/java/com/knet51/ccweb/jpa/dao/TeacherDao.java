package com.knet51.ccweb.jpa.dao;

import java.util.Map;

import com.knet51.ccweb.jpa.entities.Teacher;

public interface TeacherDao {
	Teacher save(Teacher teacher);

	Teacher update(Teacher teacher);

	Teacher findById(Long id);

	boolean delete(Teacher teacher);

	boolean deleteById(Long id);

	Teacher queryStringBySql(String col, String value);
	
	Teacher getSingleResultByQuery(String query);
	
	Teacher getSingleResultByParamsMap(Map<String,String> paramsMap);
}
