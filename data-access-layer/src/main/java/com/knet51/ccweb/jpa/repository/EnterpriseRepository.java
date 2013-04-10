package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.knet51.ccweb.jpa.entities.Enterprise;

public interface EnterpriseRepository  extends JpaRepository<Enterprise, Long>, JpaSpecificationExecutor<Enterprise>,EnterpriseRepositoryCustom{
	Page<Enterprise> findAll(Pageable pageable);
	@Query("select t from Enterprise t where t.isEnterprise = :isEnterprise")
	List<Enterprise> findEnterpriseByisEnterprise(@Param("isEnterprise") String isEnterprise);
	
	@Query("select t from Enterprise t where t.isEnterprise = :isEnterprise")
	Page<Enterprise> findEnterpriseByIsEnterprise(@Param("isEnterprise") String isEnterprise, Pageable pageable);
}
