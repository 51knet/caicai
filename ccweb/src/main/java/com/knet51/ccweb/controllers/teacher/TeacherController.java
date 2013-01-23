package com.knet51.ccweb.controllers.teacher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.commons.Method;
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

import com.google.gson.Gson;
import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.controllers.teacher.achievement.TeacherHonorDetailInfoForm;
import com.knet51.ccweb.controllers.teacher.achievement.TeacherPatentDetailInfoForm;
import com.knet51.ccweb.controllers.teacher.achievement.TeacherProjectDetailInfoForm;
import com.knet51.ccweb.controllers.teacher.achievement.TeacherThesisDetailInfoForm;
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
	@Autowired
	private TeacherThesisService thesisService;
	@Autowired
	private TeacherProjectService projectService;
	@Autowired
	private TeacherPatentService patentService;
	@Autowired
	private TeacherHonorService honorService;
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/details")
	public String detailInfoPage(@RequestParam("active") String active,Model model,HttpSession session) {
		if(active == null || active.equals("")){
			active = "avatar";
		}
		model.addAttribute("active", active);
		return "admin.teacher.details";
	}
	
	@RequestMapping(value = "/admin/teacher/personalInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse processFormAjaxJson(@Valid TeacherPersonalInfoForm personalInfoForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/teacher/eduInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse eduInfoFormAjaxJson(@Valid TeacherEduInfoForm teacherEduInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/admin/teacher/workExpInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse workExpInfoFormAjaxJson(@Valid TeacherWorkExpInfoForm workInfoForm, BindingResult result) {
		//logger.info("------into workExp ajax");
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/teacher/selfurlInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse selfurInfoFormAjaxJson(@Valid TeacherSelfUrlForm teacherSelfUrlForm, BindingResult result) {
		//logger.info("------into selfur ajax");
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/teacher/pswInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse pswfurInfoFormAjaxJson(@Valid TeacherPswForm teacherPswForm, BindingResult result) {
		//logger.info("------into psw ajax");
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/admin/teacher/thesisInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse thesisInfoFormAjaxJson(@Valid TeacherThesisDetailInfoForm teacherThesisDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/teacher/projectInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse projectInfoFormAjaxJson(@Valid TeacherProjectDetailInfoForm teacherProjectDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/teacher/patentInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse patentInfoFormAjaxJson(@Valid TeacherPatentDetailInfoForm teacherPatentDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/teacher/honorInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse honorInfoFormAjaxJson(@Valid TeacherHonorDetailInfoForm teacherHonorDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/personalInfo")
	public String personalInfo(@Valid TeacherPersonalInfoForm personalInfoForm,
			BindingResult validResult, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		logger.info("#### Personal InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("detailInfoForm Validation Failed " + validResult);
			
		} else {
			logger.info("### detailInfoForm Validation passed. ###");
			logger.info("### "+ personalInfoForm.getGender() +" ###");
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
		}
		return "redirect:/admin/teacher/resume?active=personal";
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/contactInfo")
	public String contactInfo(@Valid TeacherContactInfoForm contactInfoForm,
			BindingResult validResult, HttpSession session) {
		logger.info("#### contactInfo InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("contactInfo Validation Failed " + validResult);
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
		}
		return "redirect:/admin/teacher/resume?active=contact";
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/eduInfo" ,method = RequestMethod.POST)
	public String eduInfo(@RequestParam("eduId")String eduId,@Valid TeacherEduInfoForm eduInfoForm,
			BindingResult validResult, HttpSession session) {
		logger.info("#### eduInfo InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("eduInfo Validation Failed " + validResult);
			
		} else {
			if(eduId!=null){
				logger.info("### eduInfo Validation passed. ###");
				EduBackground edu = eduBackgroundService.findOneById(Long.parseLong(eduId));
				edu.setCollege(eduInfoForm.getCollegeName());
				edu.setSchool(eduInfoForm.getSchoolName());
				edu.setDegree(eduInfoForm.getDegree());
				edu.setStartTime(eduInfoForm.getStartTime());
				edu.setEndTime(eduInfoForm.getEndTime());
				eduBackgroundService.createEduBackground(edu);
			}else{
				logger.info("### eduInfo Validation passed. ###");
				UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
				//EduBackground eduInfo = eduBackgroundService.findEduInfoByteacherId(userInfo.getId());
				EduBackground edu = new EduBackground();
				edu.setCollege(eduInfoForm.getCollegeName());
				edu.setSchool(eduInfoForm.getSchoolName());
				edu.setDegree(eduInfoForm.getDegree());
				edu.setStartTime(eduInfoForm.getStartTime());
				edu.setEndTime(eduInfoForm.getEndTime());
				edu.setTeacherid(userInfo.getId());
				eduBackgroundService.createEduBackground(edu);
			}
			
		}
		return "redirect:/admin/teacher/resume?active=edu";
	}
	
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/eduInfo/destory/{edu_id}")
	public String destoryEduInfo(@PathVariable Long edu_id, HttpSession session) {
		logger.info("#### eduInfo InfoController ####");
		eduBackgroundService.destory(edu_id);
		return "redirect:/admin/teacher/resume?active=edu";
		
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/workInfo",method = RequestMethod.POST)
	public String workInfo(@RequestParam("workId")String workId,@Valid TeacherWorkExpInfoForm workInfoForm,
			BindingResult validResult, HttpSession session) {
		logger.info("#### workInfo Controller ####");
		if (validResult.hasErrors()) {
			logger.info("eduInfo Validation Failed " + validResult);
			
		} else {
			if(workId!=null){
				logger.info("### workInfo Validation passed. ###");
				WorkExp work = workExpService.findOneById(Long.parseLong(workId));
				work.setCompany(workInfoForm.getCompany());
				work.setDepartment(workInfoForm.getDepartment());
				work.setPosition(workInfoForm.getPosition());
				work.setStartTime(workInfoForm.getStartTimeName());
				work.setEndTime(workInfoForm.getEndTimeName());
				workExpService.createWorkExp(work);
			}else{
				logger.info("### workInfo Validation passed. ###");
				UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
				WorkExp work = new WorkExp();
				work.setCompany(workInfoForm.getCompany());
				work.setDepartment(workInfoForm.getDepartment());
				work.setPosition(workInfoForm.getPosition());
				work.setStartTime(workInfoForm.getStartTimeName());
				work.setEndTime(workInfoForm.getEndTimeName());
				work.setTeacherid(userInfo.getId());
				workExpService.createWorkExp(work);
			}
		}
		return "redirect:/admin/teacher/resume?active=work";
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/workInfo/destory",method = RequestMethod.POST)
	public String destoryWorkInfo(@RequestParam("workId") Long work_id, HttpSession session) {
		logger.info("#### eduInfo InfoController ####");
		workExpService.destory(work_id);
		return "redirect:/admin/teacher/resume?active=work";
		
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
	
	@RequestMapping(value="/admin/teacher/thesis/new")
	public String addThesis(@Valid TeacherThesisDetailInfoForm thesisDetailInfoForm, HttpSession session,
			Model model, BindingResult validResult){
		String content = thesisDetailInfoForm.getContent();
		logger.info("#### Into teacherThesisAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/teacher/resume?active=thesis";
		}
		else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long id = userInfo.getUser().getId();
			Teacher teacher= teacherService.findOne(id);
			TeacherThesis thesis = new TeacherThesis();
			thesis.setContent(content);
			thesisService.save(thesis, teacher);
			return "redirect:/admin/teacher/resume?active=thesis";
		}
	}
	
	@RequestMapping(value="/admin/teacher/thesis/destory/{thesis_id}")
	public String deleThesis(@PathVariable Long thesis_id){
		thesisService.deleteById(thesis_id);
		return "redirect:/admin/teacher/resume?active=thesis";
	}
	
	@RequestMapping(value="/admin/teacher/project/new")
	public String addProject(@Valid TeacherProjectDetailInfoForm projectDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherProjectAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/teacher/resume?active=project";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long id = userInfo.getUser().getId();
			Teacher teacher= teacherService.findOne(id);
			TeacherProject project = new TeacherProject();
			project.setTitle(projectDetailForm.getProjectTitle());
			project.setSource(projectDetailForm.getProjectSource());
			project.setStartTime(projectDetailForm.getProjectStartTime());
			project.setEndTime(projectDetailForm.getProjectEndTime());
			projectService.save(project, teacher);
			return "redirect:/admin/teacher/resume?active=project";
		}
	}
	
	@RequestMapping(value="/admin/teacher/project/destory/{project_id}")
	public String deleProject(@PathVariable Long project_id){
		projectService.deleteById(project_id);
		return "redirect:/admin/teacher/resume?active=project";
	}
	
	@RequestMapping(value="/admin/teacher/patent/new")
	public String addPatent(@Valid TeacherPatentDetailInfoForm patentDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherPatentAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/teacher/resume?active=patent";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long id = userInfo.getUser().getId();
			Teacher teacher= teacherService.findOne(id);
			TeacherPatent patent = new TeacherPatent();
			patent.setInventer(patentDetailForm.getInventer());
			patent.setName(patentDetailForm.getPatentName());
			patent.setNumber(patentDetailForm.getNumber());
			patent.setType(patentDetailForm.getPatentType());
			patentService.save(patent, teacher);
			return "redirect:/admin/teacher/resume?active=patent";
		}
	}
	
	@RequestMapping(value="/admin/teacher/patent/destory/{patent_id}")
	public String delePatent(@PathVariable Long patent_id){
		patentService.deleteById(patent_id);
		return "redirect:/admin/teacher/resume?active=patent";
	}
	
	@RequestMapping(value="/admin/teacher/honor/new")
	public String addHonor(@Valid TeacherHonorDetailInfoForm honorDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherProjectAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/teacher/resume?active=honor";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long id = userInfo.getUser().getId();
			Teacher teacher= teacherService.findOne(id);
			TeacherHonor honor = new TeacherHonor();
			honor.setName(honorDetailForm.getHonorName());
			honor.setReason(honorDetailForm.getReason());
			honorService.save(honor, teacher);
			return "redirect:/admin/teacher/resume?active=honor";
		}
	}
	
	@RequestMapping(value="/admin/teacher/honor/destory/{honor_id}")
	public String deleHonor(@PathVariable Long honor_id){
		honorService.deleteById(honor_id);
		return "redirect:/admin/teacher/resume?active=honor";
	}
	
	
	@RequestMapping(value="/admin/teacher/pswInfoCheck", method = RequestMethod.POST)
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
	
	@RequestMapping(value="/admin/teacher/eduInfo/edit/ajax",method = RequestMethod.POST)
	public void getEduJson(@RequestParam ("eduId") String eduId,HttpServletResponse response,HttpSession session) throws Exception{
		//logger.info(eduId);
		Long id = Long.parseLong(eduId);
		EduBackground eduInfo = eduBackgroundService.findOneById(id);
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		out.write(g.toJson(eduInfo));
		out.flush();
		out.close();
		
	}
	
	@RequestMapping(value="/admin/teacher/workInfo/edit/ajax",method = RequestMethod.POST)
	public void getWorkJson(@RequestParam ("workId") String workId,HttpServletResponse response,HttpSession session) throws Exception{
		//logger.info(eduId);
		Long id = Long.parseLong(workId);
		WorkExp workInfo = workExpService.findOneById(id);
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		out.write(g.toJson(workInfo));
		out.flush();
		out.close();
		
	}
	
}
