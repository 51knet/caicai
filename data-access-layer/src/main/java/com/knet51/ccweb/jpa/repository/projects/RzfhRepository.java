package com.knet51.ccweb.jpa.repository.projects;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.projects.Rzfh;

public interface RzfhRepository extends JpaRepository<Rzfh, Long>, JpaSpecificationExecutor<Long> {
	Page<Rzfh> findRzfhByStatusAndTypes(Integer status, String types,Pageable pageable);
	List<Rzfh> findRzfhListByStatusAndTypes(Integer status,String types);
	Page<Rzfh> findRzfhByNameLike(String name, Pageable pageable);
	
	Page<Rzfh> findRzfhByUserAndTypes(User user, String types, Pageable pageable);
	List<Rzfh> findRzfhListByUserAndTypes(User user,String types);
}
