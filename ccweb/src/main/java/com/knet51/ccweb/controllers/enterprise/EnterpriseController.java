package com.knet51.ccweb.controllers.enterprise;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.commons.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.controllers.enterprise.EnterprisePersonalInfoForm;
import com.knet51.ccweb.controllers.login.LoginForm;
import com.knet51.ccweb.controllers.enterprise.TeacherPersonalInfoForm;
import com.knet51.ccweb.controllers.teacher.TeacherPswForm;
import com.knet51.ccweb.controllers.teacher.TeacherSelfUrlForm;
import com.knet51.ccweb.controllers.teacher.achievement.TeacherHonorDetailInfoForm;


import com.knet51.ccweb.jpa.entities.EduBackground;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.WorkExp;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;
import com.knet51.ccweb.jpa.services.EduBackgroundService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.WorkExpService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherHonorService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherPatentService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherProjectService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherThesisService;
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
	@RequestMapping(value = "/admin/enterprise/details")
	public String detailInfoPage(@RequestParam("active") String active,Model model,HttpSession session) {
		if(active == null || active.equals("")){
			active = "avatar";
		}
		model.addAttribute("active", active);
		return "admin.enterprise.details";
	}
	@RequestMapping(value="/admin/enterprise/pswInfoCheck", method = RequestMethod.POST)
	public void checkEmailAndPsw(HttpServletResponse response,HttpSession session,TeacherPswForm teacherPswForm) throws Exception{
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		PrintWriter out=response.getWriter();
		String email=userInfo.getEmail();
		User user=userService.findByEmailAddress(email);
		String password=user.getPassword();
		String oriPsw=teacherPswForm.getOri_psw();
		Integer num=1;
		if(!password.equals(oriPsw)){
			num=0;
		}
		String number=num.toString();
		out.write(number);
		out.flush();
		out.close();
	}


	
	@Transactional
	@RequestMapping(value = "/admin/enterprise/enterprisepersonalInfo", method = RequestMethod.POST)
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
		return "redirect:/admin/teacher/resume?active=personal";
	}
	
	/**
	 * update the teacher's pwd
	 * @param pswForm
	 * @param validResult
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/admin/enterprise/changePsw" ,method = RequestMethod.POST)
	public String changePsw(@Valid TeacherPswForm pswForm,
			BindingResult validResult, HttpSession session, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttr) {
		logger.info("#### changePsw InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("changePsw Validation Failed " + validResult);
			return "redirect:/admin/enterprise/details?active=psw";
		} else {
			logger.info("### changePsw Validation passed. ###");

			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			
			User user = userService.findOne(userInfo.getId());
			String password = user.getPassword();
			if(password.equals(pswForm.getOri_psw())){
				user.setPassword(pswForm.getNew_psw());
				user = userService.updateUser(user);
				Teacher teacher = teacherService.findOne(userInfo.getId());
				userInfo.setUser(user);
				userInfo.setTeacher(teacher);
				session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
				String message = "密码修改成功";
				redirectAttr.addFlashAttribute("message", message);
			}else{
				logger.info("original password is not correct. Nothing update.");
			}
			return "redirect:/admin/enterprise/details?active=psw";
		}
	}

	@Transactional
	@RequestMapping(value = "/admin/enterprsie/selfurl" , method = RequestMethod.POST)
	public String selfUrl(@Valid TeacherSelfUrlForm selfUrlForm,
			BindingResult validResult, HttpSession session) {

		logger.info("### in self url controller ###");

		if (validResult.hasErrors()) {
			logger.info("selfUrlForm Validation Failed " + validResult);
			return "redirect:/admin/enterprise/details?active=url";
		} else {
			logger.info("### detailInfoForm Validation passed. ###");
			String url = selfUrlForm.getUrl();
			boolean usableUrl = false;
			try {
				usableUrl = userService.usableUrl(url);
			} catch (Exception e) {
				usableUrl = false;
			}
			if (usableUrl) {
				UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
				User user = userService.findOne(userInfo.getId());
				user.setSelf_url(url);
				user = userService.updateUser(user);
				userInfo.setUser(user);
				session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			}
			return "redirect:/admin/enterprise/details?active=url";
		}
	}
	
	
	@RequestMapping(value="/admin/enterprise/honor/new" , method = RequestMethod.POST)
	public String addHonor(@RequestParam("honorId") Long honorId, @Valid TeacherHonorDetailInfoForm honorDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into enterprsieHonnerAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/enterprise/resume?active=honor";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			
			TeacherHonor honor = null;
			if(honorId!=null){
				honor=honorService.findOneById(honorId);
				honor.setName(honorDetailForm.getHonorName());
				honor.setReason(honorDetailForm.getReason());
				honor.setDesc(honorDetailForm.getHonorDesc());
				honorService.update(honor);
			}else{
				honor=new TeacherHonor();
				honor.setName(honorDetailForm.getHonorName());
				honor.setReason(honorDetailForm.getReason());
				honor.setDesc(honorDetailForm.getHonorDesc());
				honorService.save(honor, userInfo.getTeacher());
			}
			return "redirect:/admin/enterprise/resume?active=honor";
		}
	}
	
	@RequestMapping(value="/admin/enterprise/honor/destory",method=RequestMethod.POST)
	public String deleHonor(@RequestParam("honorId")Long honor_id){
		honorService.deleteById(Long.valueOf(honor_id));
		return "redirect:/admin/enterprise/resume?active=honor";
	}
	
	

	@Transactional
	@RequestMapping(value = "/admin/enterprise/resume")
	public String resumePage(@RequestParam("active") String active,
			Model model, HttpSession session) {
	
		if (active == null || active.equals("")) {
			active = "personal";
		}
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<TeacherHonor> honorList = honorService.getAllHonorById(userInfo.getId());
		model.addAttribute("honorList", honorList);
		model.addAttribute("honorCount", honorList.size());
		model.addAttribute("active", active);
		return "admin.enterprise.resume";
	}
	
	
	@RequestMapping(value = "/admin/enterprise/enterpriseInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse enterpriseFormAjaxJson(@Valid EnterprisePersonalInfoForm personalInfoForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/admin/enterprise/honorInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse honorInfoFormAjaxJson(@Valid TeacherHonorDetailInfoForm teacherHonorDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
}
