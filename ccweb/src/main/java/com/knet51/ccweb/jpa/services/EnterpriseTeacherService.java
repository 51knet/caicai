package com.knet51.ccweb.jpa.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.knet51.ccweb.jpa.entities.EnterpriseTeacher;
import com.knet51.ccweb.jpa.entities.User;

public interface EnterpriseTeacherService {
	List<EnterpriseTeacher> findTeacherByEnterprise(User user);
	Page<EnterpriseTeacher> findTeacherByEnterprise(int pageNum, int pageSize, User user);
	
}
