package com.knet51.ccweb.controllers.admin.user.resume;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Student;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.EduBackgroundService;
import com.knet51.ccweb.jpa.services.StudentService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.WorkExpService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;
import com.knet51.ccweb.controllers.admin.user.resume.UserPersonalInfoForm;

@Controller
public class UserResumeDetailController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserResumeDetailController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private EduBackgroundService eduBackgroundService;
	@Autowired
	private WorkExpService workExpService;
	@Autowired
	private StudentService studentService;
	
	/**
	 * update the teacher's personalInfo
	 * @param personalInfoForm
	 * @param validResult
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/admin/user/personalInfo", method = RequestMethod.POST)
	public String userPersonalInfo(@Valid UserPersonalInfoForm personalInfoForm,
			BindingResult validResult, HttpSession session,RedirectAttributes redirectAttr, HttpServletRequest request, HttpServletResponse response) {
		logger.info("#### Personal InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("detailInfoForm Validation Failed " + validResult);
			
		} else {
			logger.info("### detailInfoForm Validation passed. ###");
			logger.info("### "+ personalInfoForm.getGender() +" ###");
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			String photo_url=userInfo.getPhotoUrl();
			String photoUrl=photo_url.substring(0,photo_url.lastIndexOf("/")+1);
			String imgName= photo_url.substring(photo_url.lastIndexOf("/")+1, photo_url.length());
			User user = userService.findOne(userInfo.getId());
			user.setName(personalInfoForm.getName().trim());
			user.setGender(personalInfoForm.getGender());
			
			if(imgName.equals("avatar91.png")||imgName.equals("avatar90.png")){
				if(personalInfoForm.getGender().equals("女")){
					String photoName="avatar91.png";
					user.setPhoto_url(photoUrl+photoName);
				}else{
					String photoName="avatar90.png";
					user.setPhoto_url(photoUrl+photoName);
				}
			}
			
			Student student = new Student(user);
			student.setCollege(personalInfoForm.getCollege());
			student.setClassNum(personalInfoForm.getClassNum());
			student.setGraduateTime(personalInfoForm.getGraduateTime());
			student.setMajor(personalInfoForm.getMajor());
			student.setTeacher(personalInfoForm.getTeacher());
			student = studentService.createStudent(student);
			User newUser = userService.updateUser(user);
			userInfo.setUser(newUser);
			userInfo.setStudent(student);
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			System.out.println("---------------"+userInfo.getStudent().getMajor());
			
			String message = "个人信息保存成功";
			redirectAttr.addFlashAttribute("message", message);
		}
		return "redirect:/admin/resume?active=personal";
	}
	
	@RequestMapping(value = "/admin/user/personalInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse UserPersonalFormAjaxJson(@Valid UserPersonalInfoForm personalInfoForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
}
