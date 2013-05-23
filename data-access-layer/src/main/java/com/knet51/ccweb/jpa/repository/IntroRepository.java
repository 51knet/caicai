package com.knet51.ccweb.jpa.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Enterprise;
import com.knet51.ccweb.jpa.entities.enterprise.EnterpriseIntro;

@Transactional
public interface IntroRepository extends JpaRepository<EnterpriseIntro, Long>, JpaSpecificationExecutor<EnterpriseIntro> {
	Page<EnterpriseIntro> findIntroByEnterprise(Enterprise enterprise, Pageable pageable);
	List<EnterpriseIntro> getAllIntroById(Long id);
}
