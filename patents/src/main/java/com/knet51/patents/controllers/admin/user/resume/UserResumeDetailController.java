package com.knet51.patents.controllers.admin.user.resume;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.resume.EduBackgroundService;
import com.knet51.patents.jpa.services.resume.WorkExpService;
import com.knet51.patents.util.ajax.AjaxValidationEngine;
import com.knet51.patents.util.ajax.ValidationResponse;
import com.knet51.ccweb.jpa.entities.EduBackground;
import com.knet51.ccweb.jpa.entities.Student;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.WorkExp;


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
			
			User newUser = userService.updateUser(user);
			userInfo.setUser(newUser);
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			
			String message = "个人信息保存成功";
			redirectAttr.addFlashAttribute("message", message);
		}
		return "redirect:/admin/resume?active=personal";
	}
	
	/**
	 * update or create the user's workExpInfo
	 * @param work_Id
	 * @param workInfoForm
	 * @param validResult
	 * @param session
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/admin/user/workInfo",method = RequestMethod.POST)
	public String changeUserWorkInfo(@Valid UserWorkExpInfoForm workInfoForm,
			BindingResult validResult, HttpSession session,@RequestParam("workId")Long work_Id) {
		logger.info("#### workInfo Controller ####");
		if (validResult.hasErrors()) {
			logger.info("eduInfo Validation Failed " + validResult);
			
		} else {
			logger.info("### workInfo Validation passed. ###");
			WorkExp work = null;
			if(work_Id!=null){
				work = workExpService.findOneById(Long.valueOf(work_Id));
			}else{
				UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
				work = new WorkExp();
				work.setTeacherid(userInfo.getId());
			}
			work.setCompany(workInfoForm.getCompany());
			work.setPosition(workInfoForm.getPosition());
			work.setStartTime(workInfoForm.getStartTime());
			work.setEndTime(workInfoForm.getEndTime());
			workExpService.createWorkExp(work);
		}
		return "redirect:/admin/resume?active=work";
	}
	
	/**
	 * delete the teacher's workExpInfo
	 * @param work_id
	 * @param session
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/admin/user/workInfo/destory",method = RequestMethod.POST)
	public String destoryUserWorkInfo(@RequestParam("userWorkId") Long work_id, HttpSession session) {
		logger.info("#### delete user workExp InfoController ####"+work_id);
		workExpService.destory(Long.valueOf(work_id));
		return "redirect:/admin/resume?active=work";
	}
	
	@RequestMapping(value = "/admin/user/lowEduInfo" ,method = RequestMethod.POST)
	public String changeUserLowEduInfo(@RequestParam("loweduId")Long edu_id,@RequestParam("level")String level,@Valid UserLowEduInfoForm eduInfoForm,
			BindingResult validResult, HttpSession session) {
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		logger.info("#### userLowEduInfo Controller ####");
		
		if (validResult.hasErrors()) {
			logger.info("eduInfo Validation Failed " + validResult);
			
		} else {
			EduBackground edu;
			logger.info("### eduInfo Validation passed. ###");
			try {
				if(edu_id!=null){
					edu = eduBackgroundService.findOneById(Long.valueOf(edu_id));
				}else{
					//EduBackground eduInfo = eduBackgroundService.findEduInfoByteacherId(userInfo.getId());
					edu = new EduBackground();
					edu.setTeacherid(userInfo.getId());
				}
				edu.setClassNum(eduInfoForm.getLowClassNum());
				edu.setSchool(eduInfoForm.getLowSchoolName());
				edu.setTeacherNam(eduInfoForm.getLowTeacherName());
				edu.setStartTime(eduInfoForm.getLowStartTime());
				edu.setLevel(level);
				eduBackgroundService.createEduBackground(edu);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/admin/resume?active=edu";
	}
	
	@RequestMapping(value = "/admin/user/highEduInfo" ,method = RequestMethod.POST)
	public String changeUserHighEduInfo(@RequestParam("higheduId")Long edu_id,@RequestParam("level")String level,@Valid UserHighEduInfoForm eduInfoForm,
			BindingResult validResult, HttpSession session) {
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		logger.info("#### userLowEduInfo Controller ####");
		
		if (validResult.hasErrors()) {
			logger.info("eduInfo Validation Failed " + validResult);
			
		} else {
			EduBackground edu;
			logger.info("### eduInfo Validation passed. ###");
			try {
				if(edu_id!=null){
					edu = eduBackgroundService.findOneById(Long.valueOf(edu_id));
				}else{
					edu = new EduBackground();
					edu.setTeacherid(userInfo.getId());
				}
				edu.setClassNum(eduInfoForm.getHighClassNum());
				edu.setSchool(eduInfoForm.getHighSchoolName());
				edu.setTeacherNam(eduInfoForm.getHighTeacherName());
				edu.setStartTime(eduInfoForm.getHighStartTime());
				edu.setCollege(eduInfoForm.getHighCollegeName());
				edu.setMajor(eduInfoForm.getHighMajor());
				edu.setLevel(level);
				eduBackgroundService.createEduBackground(edu);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/admin/resume?active=edu";
	}
	
	
	@RequestMapping(value = "/admin/user/personalInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse UserPersonalFormAjaxJson(@Valid UserPersonalInfoForm personalInfoForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/admin/user/highEduInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse userHigheduInfoFormAjaxJson(@Valid UserHighEduInfoForm highEduInfoForm, BindingResult result) {
		logger.info("--- into highEduAjax ----");
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/admin/user/lowEduInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse userLoweduInfoFormAjaxJson(@Valid UserLowEduInfoForm lowEduInfoForm, BindingResult result) {
		logger.info("====="+lowEduInfoForm.getLowTeacherName()+lowEduInfoForm.getLowClassNum());
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/admin/user/workExpInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse workExpInfoFormAjaxJson(@Valid UserWorkExpInfoForm workInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
}
