package com.knet51.ccweb.controllers.enterprise;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.EnterpriseTeacher;
import com.knet51.ccweb.jpa.services.EnterpriseTeacherService;

@Controller
public class EnterpriseTeacherInfoPageController {
	private static Logger logger = 
			LoggerFactory.getLogger(EnterpriseTeacherInfoPageController.class);
	
	@Autowired
	private EnterpriseTeacherService enterpriseTeacherService;
	
	@RequestMapping(value="/admin/enterprise/teacher/list")
	public String showAllTeachersInEnterprise(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		logger.info("======= into enterprise teacher controller");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Page<EnterpriseTeacher> page = enterpriseTeacherService.findTeacherByEnterprise(pageNumber, pageSize, userInfo.getUser());
		model.addAttribute("page", page);
		return "admin.enterprise.teacher.list";
	}
}
