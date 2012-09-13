package com.knet51.ccweb.jpa.dao;

import java.util.Map;

import com.knet51.ccweb.jpa.entities.Student;

public interface StudentDao {
	Student save(Student student);

	Student update(Student student);

	Student findById(Long id);

	boolean delete(Student student);

	boolean deleteById(Long id);

	Student queryStringBySql(String col, String value);
	
	Student getSingleResultByQuery(String query);
	
	Student getSingleResultByParamsMap(Map<String,String> paramsMap);
}
