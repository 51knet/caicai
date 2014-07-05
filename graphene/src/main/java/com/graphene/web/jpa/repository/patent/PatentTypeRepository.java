package com.graphene.web.jpa.repository.patent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.graphene.web.jpa.entity.patent.PatentType;


public interface PatentTypeRepository extends JpaRepository<PatentType, Long>, JpaSpecificationExecutor<PatentType>{
	
}
