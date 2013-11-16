package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.Patent;

public interface UserPatentRepository extends JpaRepository<Patent, String>,JpaSpecificationExecutor<Patent> {
	Page<Patent> findPatentByUser(User user, Pageable pageable);
	List<Patent> findPatentByUser(User user);
}
