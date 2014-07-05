package com.graphene.web.jpa.repository.patent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.graphene.web.jpa.entity.patent.PatentField;


public interface PatentFieldRepository extends JpaRepository<PatentField, Long>,JpaSpecificationExecutor<PatentField> {

}
