package com.knet51.ccweb.jpa.services.requirement;

import java.util.List;

import com.knet51.ccweb.jpa.entities.RequirType;

public interface RequirTypeService {
	List<RequirType> findTypeList();
	RequirType findOne(Long type_id);
}
