package com.knet51.ccweb.jpa.dao;

public interface BaseDao<T> {
	 T findOne (Class<T> clazz,Long id);
	 void delete(Class<T> clazz,Long id);
	 T update(T entity);
	 T create(T entity);
}
