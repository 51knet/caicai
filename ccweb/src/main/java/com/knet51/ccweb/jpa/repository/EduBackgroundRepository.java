package com.knet51.ccweb.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.EduBackground;
@Transactional
public interface EduBackgroundRepository extends JpaRepository<EduBackground, Long>, JpaSpecificationExecutor<EduBackground>,EduBackgroundRepositoryCustom {

}
