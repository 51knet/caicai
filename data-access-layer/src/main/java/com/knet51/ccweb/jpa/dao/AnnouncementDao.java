package com.knet51.ccweb.jpa.dao;

import java.util.List;
import java.util.Map;

import com.knet51.ccweb.jpa.entities.Announcement;

public interface AnnouncementDao {
	Announcement save(Announcement announcement);

	Announcement update(Announcement announcement);

	Announcement findOneById(Long id);

	boolean deleteAllByUid(Long uId);

	void deleteById(Long id);

	Announcement queryStringBySql(String col, String value);
	
	Announcement getSingleResultByQuery(String query);
	
	Announcement getSingleResultByParamsMap(Map<String,String> paramsMap);

	List<Announcement> listAllByUid(Long uId);
}
