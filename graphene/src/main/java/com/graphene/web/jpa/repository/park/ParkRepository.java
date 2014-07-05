package com.graphene.web.jpa.repository.park;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.graphene.web.jpa.entity.park.Park;

public interface ParkRepository extends JpaRepository<Park, Long>,JpaSpecificationExecutor<Park> {


}
