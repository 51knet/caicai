package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.resource.ResourceType;

@Transactional
public interface ResourceTypeRepository  extends JpaRepository<ResourceType, Long>, JpaSpecificationExecutor<ResourceType>{
	
	List<ResourceType> findResourceTypeByTypeStatus(String typeStatus);
}
