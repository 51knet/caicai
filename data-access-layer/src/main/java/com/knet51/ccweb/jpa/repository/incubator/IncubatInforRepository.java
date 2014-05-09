package com.knet51.ccweb.jpa.repository.incubator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.incubator.IncubatInfor;

public interface IncubatInforRepository extends JpaRepository<IncubatInfor, Long>, JpaSpecificationExecutor<IncubatInfor> {
	IncubatInfor findIncubatInforByUser(User user);
}
