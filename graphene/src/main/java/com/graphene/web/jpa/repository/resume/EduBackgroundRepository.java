package com.graphene.web.jpa.repository.resume;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.graphene.web.jpa.entity.resume.EduBackground;

public interface EduBackgroundRepository extends JpaRepository<EduBackground, Long>, JpaSpecificationExecutor<EduBackground>{
	Page<EduBackground> findEduBackgroundByTeacheridAndForbiddenIsNull(Long teacher_id, Pageable pageable);
	List<EduBackground> findEduBackgroundByTeacherid(Long teacher_id ,Sort sort);
	
	Page<EduBackground> findAll(Pageable pageable);
}
