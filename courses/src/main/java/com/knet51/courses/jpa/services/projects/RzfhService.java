package com.knet51.courses.jpa.services.projects;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.projects.Rzfh;

public interface RzfhService {

	Page<Rzfh> findRzfhByStatusAndTypes(int pageNumber, int pageSize,Integer status,String types);
	List<Rzfh> findRzfhListByStatusAndTypes(Integer status ,String types);
	Page<Rzfh> findAll(int pageNumber, int pageSize);
}
