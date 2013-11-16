package com.knet51.ccweb.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.patent.PatentType;

public interface PatentTypeRepository extends JpaRepository<PatentType, Long>, JpaSpecificationExecutor<PatentType>{
	
}
