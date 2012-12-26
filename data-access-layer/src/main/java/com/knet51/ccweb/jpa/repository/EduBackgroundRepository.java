package com.knet51.ccweb.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.EduBackground;
import com.knet51.ccweb.jpa.entities.User;
@Transactional
public interface EduBackgroundRepository extends JpaRepository<EduBackground, Long>, JpaSpecificationExecutor<EduBackground>,EduBackgroundRepositoryCustom {
	Page<EduBackground> findEduBackgroundByTeacherid(Long teacher_id, Pageable pageable);
	List<EduBackground> findEduBackgroundByTeacherid(Long teacher_id);
}
