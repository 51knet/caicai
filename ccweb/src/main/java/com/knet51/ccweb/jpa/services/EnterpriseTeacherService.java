package com.knet51.ccweb.jpa.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.knet51.ccweb.jpa.entities.EnterpriseTeacher;
import com.knet51.ccweb.jpa.entities.User;

public interface EnterpriseTeacherService {
	List<EnterpriseTeacher> findTeacherByEnterprise(User enterprise);
	Page<EnterpriseTeacher> findTeacherByEnterprise(int pageNum, int pageSize, User enterprise);
	EnterpriseTeacher createTeacher(EnterpriseTeacher eTeacher);
	EnterpriseTeacher updateTeacher(EnterpriseTeacher eTeacher);
	void destoryTeacher(Long eTeacherid);
	EnterpriseTeacher findOneById(Long id);
}
