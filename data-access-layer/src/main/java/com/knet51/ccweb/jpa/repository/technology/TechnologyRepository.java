package com.knet51.ccweb.jpa.repository.technology;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.technology.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, Long>, JpaSpecificationExecutor<Technology>{
	Page<Technology> findAll(Pageable pageable);
	
	Page<Technology> findAllByUser(User user, Pageable pageable);
	List<Technology> findAllListByUser(User user);
	
	Page<Technology> findAllByStatus(Integer status, Pageable pageable );
	List<Technology> findAllListByStatus(Integer status, Pageable pageable);
}
