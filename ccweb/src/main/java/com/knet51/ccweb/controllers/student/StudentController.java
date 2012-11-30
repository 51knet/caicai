package com.knet51.ccweb.controllers.student;

import java.io.PrintWriter;

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

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Student;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.StudentService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;
@Controller
public class StudentController {
	private static final Logger logger = LoggerFactory
			.getLogger(StudentController.class);
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/admin/student/personalInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse processFormAjaxJson(@Valid StudentPersonalInfoForm studentPersonalInfoForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/student/pswInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse pswfurInfoFormAjaxJson(@Valid StudentPswForm studentPswForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@Transactional
	@RequestMapping(value = "/admin/student/details")
	public String detailInfoPage(@RequestParam("active") String active,Model model,HttpSession session) {
		if(active == null || active.equals("")){
			active = "psw";
		}
		model.addAttribute("active", active);
		return "admin.student.details";
	}
	@Transactional
	@RequestMapping(value = "/admin/student/personalInfo")
	public String personalInfo(@Valid StudentPersonalInfoForm studentPersonalInfoForm,
			BindingResult validResult, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		logger.info("#### Personal InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "redirect:/admin/student/resume?active=personal";
		} else {
			logger.info("### detailInfoForm Validation passed. ###");
			logger.info("### "+ studentPersonalInfoForm.getGender() +" ###");
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			User user = userService.findOne(userInfo.getId());
			user.setName(studentPersonalInfoForm.getName());
			user.setGender(studentPersonalInfoForm.getGender());
			user = userService.updateUser(user);
			Student student = new Student(user);
			student.setCollege(studentPersonalInfoForm.getCollege());
			student.setSenior_high_school(studentPersonalInfoForm.getSenior_high_school());
			student.setJunior_high_school(studentPersonalInfoForm.getJunior_high_school());
			student.setPrimary_school(studentPersonalInfoForm.getPrimary_school());
			student.setRole(studentPersonalInfoForm.getRole());
			student = studentService.updateStudent(student);
			userInfo.setUser(user);
			userInfo.setStudent(student);
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);

			return "redirect:/admin/student/resume?active=personal";
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/student/contactInfo")
	public String contactInfo(@Valid StudentContactInfoForm studentContactInfoForm,
			BindingResult validResult, HttpSession session) {
		logger.info("#### contactInfo InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("contactInfo Validation Failed " + validResult);
			return "redirect:/admin/student/resume?active=contact";
		} else {
			logger.info("### contactInfo Validation passed. ###");

			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			
			User user = userService.findOne(userInfo.getId());
			user.setAddress(studentContactInfoForm.getAddress());
			user.setCell_phone(studentContactInfoForm.getCellphone());
			user.setQq(studentContactInfoForm.getQq());
			user.setMsn(studentContactInfoForm.getMsn());
			user = userService.updateUser(user);
			
			Student student = studentService.findOne(userInfo.getId());
			userInfo.setUser(user);
			userInfo.setStudent(student);
			
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			
			return "redirect:/admin/student/resume?active=contact";
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/student/changePsw")
	public String changePsw(@Valid StudentPswForm studentPswForm,
			BindingResult validResult, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		logger.info("#### changePsw InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("changePsw Validation Failed " + validResult);
			return "redirect:/admin/student/details?active=psw";
		} else {
			logger.info("### changePsw Validation passed. ###");

			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			
			User user = userService.findOne(userInfo.getId());
			String password = user.getPassword();
			if(password.equals(studentPswForm.getOri_psw())){
				user.setPassword(studentPswForm.getNew_psw());
				user = userService.updateUser(user);
				Student student = studentService.findOne(userInfo.getId());
				userInfo.setUser(user);
				userInfo.setStudent(student);
				session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			}else{
				logger.info("original password is not correct. Nothing update.");
			}
			return "redirect:/admin/student/details?active=psw";
		}
	}
	
	@RequestMapping(value="/admin/student/pswInfoCheck", method = RequestMethod.POST)
	public void checkEmail(@RequestParam("oriPsw") String oriPsw,HttpServletResponse response,HttpSession session) throws Exception{
		PrintWriter out=response.getWriter();
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		String password=userInfo.getUser().getPassword();
		Integer count=0;
		if(password.equals(oriPsw)){
			count=1;
		}else{
			count=0;
		}
		String countString  = count.toString();
		out.write(countString);
		out.flush();
		out.close();
	}
}
