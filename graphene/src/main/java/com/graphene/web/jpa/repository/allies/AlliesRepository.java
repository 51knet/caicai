package com.graphene.web.jpa.repository.allies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.graphene.web.jpa.entity.allies.Allies;

public interface AlliesRepository extends JpaRepository<Allies, Long>, JpaSpecificationExecutor<Allies> {

}
