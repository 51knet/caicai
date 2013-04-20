package com.knet51.ccweb.controllers.admin.upgrade;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.services.TeacherService;

@Controller
public class UpgradeRoleController {

	@Autowired
	private TeacherService teacherService;
	
	@Transactional
	@RequestMapping(value="/admin/teacher/upgradeRole")
	public String upgradeRole(HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Teacher teacher = teacherService.findOne(userInfo.getId());
		teacher.setIsEnterprise("1");
		teacher = teacherService.updateTeacher(teacher);
		userInfo.setTeacher(teacher);
		session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
		return "redirect:/admin/teacher/resume?active=personal";
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/downgradeRole")
	public String downgradeRole(HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Teacher teacher = teacherService.findOne(userInfo.getId());
		teacher.setIsEnterprise(null);
		teacher = teacherService.updateTeacher(teacher);
		userInfo.setTeacher(teacher);
		session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
		return "redirect:/admin/teacher/resume?active=personal";
	}

}
