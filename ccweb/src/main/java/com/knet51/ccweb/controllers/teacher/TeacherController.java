package com.knet51.ccweb.controllers.teacher;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.EduBackground;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.WorkExp;
import com.knet51.ccweb.jpa.services.EduBackgroundService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.WorkExpService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TeacherController {

	private static final Logger logger = LoggerFactory
			.getLogger(TeacherController.class);

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;
	@Autowired
	private EduBackgroundService eduBackgroundService;
	@Autowired
	private WorkExpService workExpService;
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/details")
	public String detailInfoPage(@RequestParam("active") String active,Model model,HttpSession session) {
		String universityFilePath = "";
		List<String> universityList = new ArrayList<String>();
		BufferedReader br;
		universityFilePath = session.getServletContext().getRealPath("/");
		universityFilePath += "resources\\university\\universities.property";
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(universityFilePath),"utf-8"));
			String data = "";
			while ((data = br.readLine()) != null) {
				universityList.add(data);
			}
			br.close();
		} catch (FileNotFoundException e) {
			universityList.add(" ");
		} catch (IOException e) {
			universityList.add(" ");
		}
		if(active == null || active.equals("")){
			active = "personal";
		}
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
//		EduBackground eduInfo = eduBackgroundService.findEduInfoByteacherId(userInfo.getId());
		List<EduBackground> eduInfo = eduBackgroundService.findEduInfoList(userInfo.getId());
		if(eduInfo !=null){
			model.addAttribute("eduInfo", eduInfo);
			model.addAttribute("eduCount", eduInfo.size());
		}
		List<WorkExp> workInfo = workExpService.findWorkList(userInfo.getId());
		if(workInfo !=null){
			model.addAttribute("workInfo", workInfo);
			model.addAttribute("workCount", workInfo.size());
		}
		model.addAttribute("active", active);
		model.addAttribute("universityList", universityList);
		return "admin.teacher.details";
	}
	
	@RequestMapping(value = "/admin/teacher/personalInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse processFormAjaxJson(@Valid TeacherPersonalInfoForm personalInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/teacher/contactInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse contactInfoFormAjaxJson(@Valid TeacherContactInfoForm TeacherContactInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/personalInfo")
	public String personalInfo(@Valid TeacherPersonalInfoForm personalInfoForm,
			BindingResult validResult, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		logger.info("#### Personal InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "redirect:/admin/teacher/details?active=personal";
		} else {
			logger.info("### detailInfoForm Validation passed. ###");
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			User user = userService.findOne(userInfo.getId());
			user.setName(personalInfoForm.getName());
			user.setGender(personalInfoForm.getGender());
			user = userService.updateUser(user);
			Teacher teacher = new Teacher(user);
			teacher.setCollege(personalInfoForm.getCollege());
			teacher.setSchool(personalInfoForm.getSchool());
			teacher.setTitle(personalInfoForm.getTitle());
			teacher.setMajor(personalInfoForm.getMajor());
			teacher.setRole(personalInfoForm.getRole());
			teacher = teacherService.updateTeacher(teacher);
			userInfo.setUser(user);
			userInfo.setTeacher(teacher);
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);

			return "redirect:/admin/teacher/details?active=personal";
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/contactInfo")
	public String contactInfo(@Valid TeacherContactInfoForm contactInfoForm,
			BindingResult validResult, HttpSession session) {
		logger.info("#### contactInfo InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("contactInfo Validation Failed " + validResult);
			return "redirect:/admin/teacher/details?active=contact";
		} else {
			logger.info("### contactInfo Validation passed. ###");

			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			
			User user = userService.findOne(userInfo.getId());
			user.setAddress(contactInfoForm.getAddress());
			user.setCell_phone(contactInfoForm.getCellphone());
			user.setFix_phone(contactInfoForm.getPhone());
			user.setFax(contactInfoForm.getFax());
			user.setQq(contactInfoForm.getQq());
			user.setMsn(contactInfoForm.getMsn());
			user = userService.updateUser(user);
			
			Teacher teacher = teacherService.findOne(userInfo.getId());
			userInfo.setUser(user);
			userInfo.setTeacher(teacher);
			
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			
			return "redirect:/admin/teacher/details?active=contact";
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/eduInfo")
	public String eduInfo(@Valid TeacherEduInfoForm eduInfoForm,
			BindingResult validResult, HttpSession session) {
		logger.info("#### eduInfo InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("eduInfo Validation Failed " + validResult);
			return "redirect:/admin/teacher/details?active=edu";
		} else {
			logger.info("### eduInfo Validation passed. ###");
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			//EduBackground eduInfo = eduBackgroundService.findEduInfoByteacherId(userInfo.getId());
			EduBackground edu = new EduBackground();
			edu.setCollege(eduInfoForm.getCollege());
			edu.setSchool(eduInfoForm.getSchool());
			edu.setDegree(eduInfoForm.getDegree());
			edu.setStartTime(eduInfoForm.getStartTime());
			edu.setEndTime(eduInfoForm.getEndTime());
			edu.setTeacherid(userInfo.getId());
			eduBackgroundService.createEduBackground(edu);
			return "redirect:/admin/teacher/details?active=edu";
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/eduInfo/destory/{edu_id}")
	public String destoryEduInfo(@PathVariable Long edu_id, HttpSession session) {
		logger.info("#### eduInfo InfoController ####");
		eduBackgroundService.destory(edu_id);
		return "redirect:/admin/teacher/details?active=edu";
		
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/workInfo")
	public String workInfo(@Valid TeacherWorkExpInfoForm workInfoForm,
			BindingResult validResult, HttpSession session) {
		logger.info("#### workInfo Controller ####");
		if (validResult.hasErrors()) {
			logger.info("eduInfo Validation Failed " + validResult);
			return "redirect:/admin/teacher/details?active=work";
		} else {
			logger.info("### workInfo Validation passed. ###");
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			WorkExp work = new WorkExp();
			work.setCompany(workInfoForm.getCompany());
			work.setDepartment(workInfoForm.getDepartment());
			work.setPosition(workInfoForm.getPosition());
			work.setStartTime(workInfoForm.getStartTime());
			work.setEndTime(workInfoForm.getEndTime());
			work.setTeacherid(userInfo.getId());
			workExpService.createWorkExp(work);
			return "redirect:/admin/teacher/details?active=work";
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/workInfo/destory/{work_id}")
	public String destoryWorkInfo(@PathVariable Long work_id, HttpSession session) {
		logger.info("#### eduInfo InfoController ####");
		workExpService.destory(work_id);
		return "redirect:/admin/teacher/details?active=work";
		
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/changePsw")
	public String changePsw(@Valid TeacherPswForm pswForm,
			BindingResult validResult, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		logger.info("#### changePsw InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("changePsw Validation Failed " + validResult);
			return "redirect:/admin/teacher/details?active=psw";
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
			}else{
				logger.info("original password is not correct. Nothing update.");
			}
			return "redirect:/admin/teacher/details?active=psw";
		}
	}

	@Transactional
	@RequestMapping(value = "/admin/teacher/selfurl")
	public String selfUrl(@Valid TeacherSelfUrlForm selfUrlForm,
			BindingResult validResult, HttpSession session) {

		logger.info("### in self url controller ###");

		if (validResult.hasErrors()) {
			logger.info("selfUrlForm Validation Failed " + validResult);
			return "redirect:/admin/teacher/details?active=url";
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
			return "redirect:/admin/teacher/details?active=url";
		}
	}
}
