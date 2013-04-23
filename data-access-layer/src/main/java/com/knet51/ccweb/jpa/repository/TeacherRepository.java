package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.knet51.ccweb.jpa.entities.Teacher;

public interface TeacherRepository  extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher>,TeacherRepositoryCustom{
	Page<Teacher> findAll(Pageable pageable);
	@Query("select t from Teacher t where t.isEnterprise = :isEnterprise")
	List<Teacher> findEnterpriseByisEnterpriseAndForbiddenIsNull(@Param("isEnterprise") String isEnterprise);
	
	@Query("select t from Teacher t where t.isEnterprise = :isEnterprise")
	Page<Teacher> findEnterpriseByIsEnterpriseAndForbiddenIsNull(@Param("isEnterprise") String isEnterprise, Pageable pageable);
	
	Page<Teacher> findTeacherByIsEnterpriseIsNullAndForbiddenIsNull(Pageable pageable);
	
	@Query("select t from Teacher t where t.isEnterprise = :isEnterprise")
	List<Teacher> findEnterpriseByisEnterprise(@Param("isEnterprise") String isEnterprise);
}
