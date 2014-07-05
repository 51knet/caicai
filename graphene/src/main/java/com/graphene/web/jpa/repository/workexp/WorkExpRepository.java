package com.graphene.web.jpa.repository.workexp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.graphene.web.jpa.entity.resume.WorkExp;

public interface WorkExpRepository extends JpaRepository<WorkExp, Long>, JpaSpecificationExecutor<WorkExp>,WorkExpRepositoryCustom  {
	List<WorkExp> findWorkExpByTeacherid(Long user_id);
}
