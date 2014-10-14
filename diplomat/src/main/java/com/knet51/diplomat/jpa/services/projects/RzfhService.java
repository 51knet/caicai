package com.knet51.diplomat.jpa.services.projects;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.projects.Rzfh;

public interface RzfhService {
	Rzfh findOne(Long id);
	Rzfh create(Rzfh rzfh);
	Rzfh update(Rzfh rzfh);
	void dele(Long id);
	
	Page<Rzfh> findRzfhByStatusAndTypes(int pageNumber, int pageSize,Integer status,String types);
	List<Rzfh> findRzfhListByStatusAndTypes(Integer status ,String types);
	
	Page<Rzfh> findRzfhByUserAndTypes(User user,String types, int pageNumber, int pageSize);
	Page<Rzfh> findAll(int pageNumber, int pageSize);
	List<Rzfh> findRzfhListByUserAndTypes(User user,String types);
}
