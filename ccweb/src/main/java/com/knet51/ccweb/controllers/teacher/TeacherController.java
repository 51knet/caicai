package com.knet51.ccweb.controllers.teacher;

import java.io.PrintWriter;

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
	public String changeEduInfo(@RequestParam("eduId")Long edu_id,@Valid TeacherEduInfoForm eduInfoForm,
			BindingResult validResult, HttpSession session) {
		logger.info("#### eduInfo InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("eduInfo Validation Failed " + validResult);
			
		} else {
			EduBackground edu;
			if(edu_id!=null){
				logger.info("### eduInfo Validation passed. ###");
				edu = eduBackgroundService.findOneById(Long.valueOf(edu_id));
		
			}else{
				logger.info("### eduInfo Validation passed. ###");
				UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
				//EduBackground eduInfo = eduBackgroundService.findEduInfoByteacherId(userInfo.getId());
				edu = new EduBackground();
				edu.setTeacherid(userInfo.getId());
			}
			edu.setCollege(eduInfoForm.getCollegeName());
			edu.setSchool(eduInfoForm.getSchoolName());
			edu.setDegree(eduInfoForm.getDegree());
			edu.setStartTime(eduInfoForm.getStartTime());
			edu.setEndTime(eduInfoForm.getEndTime());
			edu.setEducationDesc(eduInfoForm.getEducationDesc());
			eduBackgroundService.createEduBackground(edu);
		}
		return "redirect:/admin/teacher/resume?active=edu";
	}
	
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/eduInfo/destory",method=RequestMethod.POST)
	public String destoryEduInfo(@RequestParam("eduId") Long edu_id, HttpSession session) {
		logger.info("#### eduInfo InfoController ####");
		eduBackgroundService.destory(Long.valueOf(edu_id));
		return "redirect:/admin/teacher/resume?active=edu";
		
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/workInfo",method = RequestMethod.POST)
	public String changeWorkInfo(@RequestParam("workId")Long work_Id,@Valid TeacherWorkExpInfoForm workInfoForm,
			BindingResult validResult, HttpSession session) {
		logger.info("#### workInfo Controller ####");
		if (validResult.hasErrors()) {
			logger.info("eduInfo Validation Failed " + validResult);
			
		} else {
			WorkExp work = null;
			if(work_Id!=null){
				logger.info("### workInfo Validation passed. ###");
				work = workExpService.findOneById(Long.valueOf(work_Id));
			}else{
				logger.info("### workInfo Validation passed. ###");
				UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
				work = new WorkExp();
				work.setTeacherid(userInfo.getId());
			}
			work.setCompany(workInfoForm.getCompany());
			work.setDepartment(workInfoForm.getDepartment());
			work.setPosition(workInfoForm.getPosition());
			work.setStartTime(workInfoForm.getStartTimeName());
			work.setEndTime(workInfoForm.getEndTimeName());
			work.setWorkDesc(workInfoForm.getWorkDesc());
			workExpService.createWorkExp(work);
		}
		return "redirect:/admin/teacher/resume?active=work";
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/workInfo/destory",method = RequestMethod.POST)
	public String destoryWorkInfo(@RequestParam("workId") Long work_id, HttpSession session) {
		logger.info("#### eduInfo InfoController ####");
		workExpService.destory(Long.valueOf(work_id));
		return "redirect:/admin/teacher/resume?active=work";
	}
	
	@Transactional
	@RequestMapping(value = "/admin/teacher/thesisInfo",method = RequestMethod.POST)
	public String changeThesisInfo(@RequestParam("thesisd")Long thesis_Id,@Valid TeacherThesisDetailInfoForm thesisDetailInfoForm,
			BindingResult validResult, HttpSession session) {
		logger.info("#### workInfo Controller ####");
		if (validResult.hasErrors()) {
			logger.info("eduInfo Validation Failed " + validResult);
			
		} else {
			TeacherThesis thesis = null;
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			if(thesis_Id!=null){
				logger.info("### thesisnfo Validation passed. ###");
				thesis = thesisService.findOneById(thesis_Id);
				thesis.setContent(thesisDetailInfoForm.getContent());
				thesisService.update(thesis);
			}else{
				logger.info("### thesisInfo Validation passed. ###");
				thesis = new TeacherThesis();
				thesis.setContent(thesisDetailInfoForm.getContent());
				thesisService.save(thesis, userInfo.getTeacher());
			}
			
		}
		return "redirect:/admin/teacher/resume?active=thesis";
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
	public String addThesis(@RequestParam("thesisId") Long thesis_id,@Valid TeacherThesisDetailInfoForm thesisDetailInfoForm, HttpSession session,
			Model model, BindingResult validResult){
		logger.info("#### workInfo Controller ####");
		if (validResult.hasErrors()) {
			logger.info("eduInfo Validation Failed " + validResult);
			return "redirect:/admin/teacher/resume?active=thesis";
		} else {
			TeacherThesis thesis = null;
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			if(thesis_id!=null){
				logger.info("### thesisnfo Validation passed. ###");
				thesis = thesisService.findOneById(thesis_id);
				thesis.setContent(thesisDetailInfoForm.getContent());
				thesisService.update(thesis);
			}else{
				logger.info("### thesisInfo Validation passed. ###");
				thesis = new TeacherThesis();
				thesis.setContent(thesisDetailInfoForm.getContent());
				thesisService.save(thesis, userInfo.getTeacher());
			}
			
		}
		return "redirect:/admin/teacher/resume?active=thesis";
	}	
	
	@RequestMapping(value="/admin/teacher/thesis/destory",method=RequestMethod.POST)
	public String deleThesis(@RequestParam("thesisId") Long thesis_id){
		thesisService.deleteById(Long.valueOf(thesis_id));
		return "redirect:/admin/teacher/resume?active=thesis";
	}
	
	@RequestMapping(value="/admin/teacher/project/new")
	public String addProject(@RequestParam("projectId") Long projectId, @Valid TeacherProjectDetailInfoForm projectDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherProjectAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/teacher/resume?active=project";
		}else{
			TeacherProject project=null;
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			if(projectId!=null){
				project=projectService.findOneById(projectId);
				project.setTitle(projectDetailForm.getProjectTitle());
				project.setSource(projectDetailForm.getProjectSource());
				project.setStartTime(projectDetailForm.getProjectStartTime());
				project.setEndTime(projectDetailForm.getProjectEndTime());
				project.setDesc(projectDetailForm.getProjectDesc());
				projectService.update(project);
			}else{
				project = new TeacherProject();
				project.setTitle(projectDetailForm.getProjectTitle());
				project.setSource(projectDetailForm.getProjectSource());
				project.setStartTime(projectDetailForm.getProjectStartTime());
				project.setEndTime(projectDetailForm.getProjectEndTime());
				project.setDesc(projectDetailForm.getProjectDesc());
				projectService.save(project, userInfo.getTeacher());
			}
			return "redirect:/admin/teacher/resume?active=project";
		}
	}
	
	@RequestMapping(value="/admin/teacher/project/destory",method=RequestMethod.POST)
	public String deleProject(@RequestParam("projectId") Long project_id){
		projectService.deleteById(Long.valueOf(project_id));
		return "redirect:/admin/teacher/resume?active=project";
	}
	
	@RequestMapping(value="/admin/teacher/patent/new")
	public String addPatent(@RequestParam("patentId")Long patentId,@Valid TeacherPatentDetailInfoForm patentDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherPatentAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/teacher/resume?active=patent";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			TeacherPatent patent = null;
			if(patentId!=null){
				patent=patentService.findOneById(patentId);
				patent.setInventer(patentDetailForm.getInventer());
				patent.setName(patentDetailForm.getPatentName());
				patent.setNumber(patentDetailForm.getNumber());
				patent.setType(patentDetailForm.getPatentType());
				patent.setDesc(patentDetailForm.getPatentDesc());
				patentService.update(patent);
			}else{
				patent=new TeacherPatent();
				patent.setInventer(patentDetailForm.getInventer());
				patent.setName(patentDetailForm.getPatentName());
				patent.setNumber(patentDetailForm.getNumber());
				patent.setType(patentDetailForm.getPatentType());
				patent.setDesc(patentDetailForm.getPatentDesc());
				patentService.save(patent, userInfo.getTeacher());
			}		
					
			return "redirect:/admin/teacher/resume?active=patent";
		}
	}
	
	@RequestMapping(value="/admin/teacher/patent/destory",method=RequestMethod.POST)
	public String delePatent(@RequestParam("patentId")Long patent_id){
		patentService.deleteById(Long.valueOf(patent_id));
		return "redirect:/admin/teacher/resume?active=patent";
	}
	
	@RequestMapping(value="/admin/teacher/honor/new")
	public String addHonor(@RequestParam("honorId") Long honorId, @Valid TeacherHonorDetailInfoForm honorDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherProjectAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/teacher/resume?active=honor";
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
			return "redirect:/admin/teacher/resume?active=honor";
		}
	}
	
	@RequestMapping(value="/admin/teacher/honor/destory",method=RequestMethod.POST)
	public String deleHonor(@RequestParam("honorId")Long honor_id){
		honorService.deleteById(Long.valueOf(honor_id));
		return "redirect:/admin/teacher/resume?active=honor";
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
}
