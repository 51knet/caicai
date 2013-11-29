package com.knet51.ccweb.jpa.repository.requirement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.RequirType;

public interface RequirTypeRepository extends JpaRepository<RequirType, Long>,JpaSpecificationExecutor<RequirType> {
	
}
