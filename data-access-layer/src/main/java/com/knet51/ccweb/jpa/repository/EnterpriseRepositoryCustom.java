package com.knet51.ccweb.jpa.repository;

import java.util.List;

import com.knet51.ccweb.jpa.entities.Enterprise;

public interface EnterpriseRepositoryCustom {
	List<Enterprise> findByIsEnterprise();
}
