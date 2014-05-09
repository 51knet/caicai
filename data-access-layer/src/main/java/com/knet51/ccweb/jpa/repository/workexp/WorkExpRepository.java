package com.knet51.ccweb.jpa.repository.workexp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.WorkExp;
@Transactional
public interface WorkExpRepository extends JpaRepository<WorkExp, Long>, JpaSpecificationExecutor<WorkExp>,WorkExpRepositoryCustom  {

}
