package com.knet51.ccweb.controllers.admin.enterprise;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.admin.enterprise.EnterprisePersonalInfoForm;
import com.knet51.ccweb.controllers.admin.teacher.TeacherSelfUrlForm;
import com.knet51.ccweb.controllers.admin.teacher.achievement.TeacherHonorDetailInfoForm;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;


import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.services.EduBackgroundService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.WorkExpService;
import com.knet51.ccweb.jpa.services.achievement.TeacherHonorService;
import com.knet51.ccweb.jpa.services.achievement.TeacherPatentService;
import com.knet51.ccweb.jpa.services.achievement.TeacherProjectService;
import com.knet51.ccweb.jpa.services.achievement.TeacherThesisService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;

/**
 * Handles requests for the application home page.
 */
@Controller
public class EnterpriseController {

	private static final Logger logger = LoggerFactory
			.getLogger(EnterpriseController.class);

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;
	@Autowired
	private EduBackgroundService eduBackgroundService;
	@Autowired
	private WorkExpService workExpService;
	@Autowired
	private TeacherThesisService thesisService;
	@Autowired
	private TeacherProjectService projectService;
	@Autowired
	private TeacherPatentService patentService;
	@Autowired
	private TeacherHonorService honorService;
	
	@Transactional
	@RequestMapping(value = "/admin/enterprisepersonalInfo", method = RequestMethod.POST)
	public String enterprisepersonalInfo(@Valid EnterprisePersonalInfoForm personalInfoForm,
			BindingResult validResult, HttpSession session,RedirectAttributes redirectAttr, HttpServletRequest request, HttpServletResponse response) {
		logger.info("#### Personal InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("detailInfoForm Validation Failed " + validResult);
			
		} else {
			logger.info("### detailInfoForm Validation passed. ###");
			
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			User user = userService.findOne(userInfo.getId());
			user.setName(personalInfoForm.getName());
			user = userService.updateUser(user);
			Teacher teacher = new Teacher(user);
			teacher.setIsEnterprise("1");
			teacher = teacherService.updateTeacher(teacher);
			userInfo.setUser(user);
			userInfo.setTeacher(teacher);
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			
			String message = "个人信息保存成功";
			redirectAttr.addFlashAttribute("message", message);
		}
		return "redirect:/admin/resume?active=personal";
	}
	
//	@Transactional
//	@RequestMapping(value = "/admin/enterprsie/selfurl" , method = RequestMethod.POST)
//	public String selfUrl(@Valid TeacherSelfUrlForm selfUrlForm,
//			BindingResult validResult, HttpSession session) {
//
//		logger.info("### in self url controller ###");
//
//		if (validResult.hasErrors()) {
//			logger.info("selfUrlForm Validation Failed " + validResult);
//			return "redirect:/admin/enterprise/details?active=url";
//		} else {
//			logger.info("### detailInfoForm Validation passed. ###");
//			String url = selfUrlForm.getUrl();
//			boolean usableUrl = false;
//			try {
//				usableUrl = userService.usableUrl(url);
//			} catch (Exception e) {
//				usableUrl = false;
//			}
//			if (usableUrl) {
//				UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
//				User user = userService.findOne(userInfo.getId());
//				user.setSelf_url(url);
//				user = userService.updateUser(user);
//				userInfo.setUser(user);
//				session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
//			}
//			return "redirect:/admin/enterprise/details?active=url";
//		}
//	}
//	
//	
//	@RequestMapping(value="/admin/enterprise/honor/new" , method = RequestMethod.POST)
//	public String addHonor(@RequestParam("honorId") Long honorId, @Valid TeacherHonorDetailInfoForm honorDetailForm, HttpSession session,
//			Model model,BindingResult validResult){
//		logger.info("#### Into enterprsieHonnerAddController ####");
//		if(validResult.hasErrors()){
//			return "redirect:/admin/enterprise/resume?active=honor";
//		}else{
//			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
//			
//			TeacherHonor honor = null;
//			if(honorId!=null){
//				honor=honorService.findOneById(honorId);
//				honor.setName(honorDetailForm.getHonorName());
//				honor.setReason(honorDetailForm.getReason());
//				honor.setDesc(honorDetailForm.getHonorDesc());
//				honorService.update(honor);
//			}else{
//				honor=new TeacherHonor();
//				honor.setName(honorDetailForm.getHonorName());
//				honor.setReason(honorDetailForm.getReason());
//				honor.setDesc(honorDetailForm.getHonorDesc());
//				honorService.save(honor, userInfo.getTeacher());
//			}
//			return "redirect:/admin/enterprise/resume?active=honor";
//		}
//	}
//	
//	@RequestMapping(value="/admin/enterprise/honor/destory",method=RequestMethod.POST)
//	public String deleHonor(@RequestParam("honorId")Long honor_id){
//		honorService.deleteById(Long.valueOf(honor_id));
//		return "redirect:/admin/enterprise/resume?active=honor";
//	}
//	
//	
//
//	@Transactional
//	@RequestMapping(value = "/admin/enterprise/resume")
//	public String resumePage(@RequestParam("active") String active,
//			Model model, HttpSession session) {
//	
//		if (active == null || active.equals("")) {
//			active = "personal";
//		}
//		UserInfo userInfo = (UserInfo) session
//				.getAttribute(GlobalDefs.SESSION_USER_INFO);
//		List<TeacherHonor> honorList = honorService.getAllHonorById(userInfo.getId());
//		model.addAttribute("honorList", honorList);
//		model.addAttribute("honorCount", honorList.size());
//		model.addAttribute("active", active);
//		return "admin.enterprise.resume";
//	}
	
	
	@RequestMapping(value = "/admin/enterpriseInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse enterpriseFormAjaxJson(@Valid EnterprisePersonalInfoForm personalInfoForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
}
