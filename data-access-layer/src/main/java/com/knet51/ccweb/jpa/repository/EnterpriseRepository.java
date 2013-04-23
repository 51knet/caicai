package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.Enterprise;

public interface EnterpriseRepository  extends JpaRepository<Enterprise, Long>, JpaSpecificationExecutor<Enterprise>,EnterpriseRepositoryCustom{
	Page<Enterprise> findAll(Pageable pageable);
	List<Enterprise> findEnterpriseByisEnterpriseAndForbiddenIsNull();
	
	Page<Enterprise> findEnterpriseByIsEnterpriseAndForbiddenIsNull(Pageable pageable);
}
