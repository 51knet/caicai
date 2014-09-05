package com.knet51.patents.controllers.admin.teacher.resume;

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

import com.knet51.patents.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.EduBackground;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.WorkExp;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;
import com.knet51.patents.controllers.admin.teacher.achievement.TeacherHonorDetailInfoForm;
import com.knet51.patents.controllers.admin.teacher.achievement.TeacherPatentDetailInfoForm;
import com.knet51.patents.controllers.admin.teacher.achievement.TeacherProjectDetailInfoForm;
import com.knet51.patents.controllers.admin.teacher.achievement.TeacherThesisDetailInfoForm;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.TeacherService;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.achievement.TeacherHonorService;
import com.knet51.patents.jpa.services.achievement.TeacherPatentService;
import com.knet51.patents.jpa.services.achievement.TeacherProjectService;
import com.knet51.patents.jpa.services.achievement.TeacherThesisService;
import com.knet51.patents.jpa.services.resume.EduBackgroundService;
import com.knet51.patents.jpa.services.resume.WorkExpService;
import com.knet51.patents.util.ajax.AjaxValidationEngine;
import com.knet51.patents.util.ajax.ValidationResponse;

@Controller
public class ResumeDetailController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ResumeDetailController.class);
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherThesisService thesisService;
	@Autowired
	private TeacherProjectService projectService;
	@Autowired
	private TeacherPatentService patentService;
	@Autowired
	private TeacherHonorService honorService;
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
	@RequestMapping(value = "/admin/personalInfo", method = RequestMethod.POST)
	public String personalInfo(@Valid TeacherPersonalInfoForm personalInfoForm,
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
			Teacher teacher = new Teacher(user);
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
			teacher.setCollege(personalInfoForm.getCollege().trim());
			teacher.setSchool(personalInfoForm.getSchool().trim());
			teacher.setTitle(personalInfoForm.getTitle().trim());
			teacher.setMajor(personalInfoForm.getMajor().trim());
			teacher.setRole(personalInfoForm.getRole().trim());
			teacher.setInfor(personalInfoForm.getInfor().trim());
			teacher = teacherService.updateTeacher(teacher);
			userInfo.setUser(newUser);
			userInfo.setTeacher(teacher);
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			
			String message = "个人信息保存成功";
			redirectAttr.addFlashAttribute("message", message);
		}
		return "redirect:/admin/resume?active=personal";
	}
	
	@Transactional
	@RequestMapping(value = "/admin/contactInfo" , method = RequestMethod.POST)
	public String contactInfo(@Valid ContactInfoForm contactInfoForm,RedirectAttributes redirectAttr,
			BindingResult validResult, HttpSession session) {
		logger.info("#### contactInfo InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("contactInfo Validation Failed " + validResult);
		} else {
			logger.info("### contactInfo Validation passed. ###");

			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			
			User user = userService.findOne(userInfo.getId());
			user.setAddress(contactInfoForm.getAddress().trim());
			user.setCell_phone(contactInfoForm.getCellphone().trim());
			user.setFix_phone(contactInfoForm.getPhone().trim());
			user.setFax(contactInfoForm.getFax().trim());
			user.setQq(contactInfoForm.getQq().trim());
			user.setMsn(contactInfoForm.getMsn().trim());
			user = userService.updateUser(user);
			Teacher teacher = teacherService.findOne(userInfo.getId());
			userInfo.setUser(user);
			userInfo.setTeacher(teacher);
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			String message = "联系信息保存成功";
			redirectAttr.addFlashAttribute("message", message);
		}
		return "redirect:/admin/resume?active=contact";
	}
	/**
	 * update or create the teacher's educationInfo
	 * @param edu_id
	 * @param teacherEduInfoForm
	 * @param validResult
	 * @param session
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/admin/eduInfo" ,method = RequestMethod.POST)
	public String changeEduInfo(@RequestParam("eduId")Long edu_id,@Valid TeacherEduInfoForm teacherEduInfoForm,
			BindingResult validResult, HttpSession session) {
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		logger.info("#### eduInfo InfoController ####");
		
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
				edu.setCollege(teacherEduInfoForm.getCollegeName());
				edu.setSchool(teacherEduInfoForm.getSchoolName());
				edu.setDegree(teacherEduInfoForm.getDegree());
				edu.setStartTime(teacherEduInfoForm.getStartTime());
				edu.setEndTime(teacherEduInfoForm.getEndTime());
				edu.setEducationDesc(teacherEduInfoForm.getEducationDesc());
				eduBackgroundService.createEduBackground(edu);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/admin/resume?active=edu";
	}
	
	/**
	 * delete the teacher's eduInfo
	 * @param edu_id
	 * @param session
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/admin/eduInfo/destory",method=RequestMethod.POST)
	public String destoryEduInfo(@RequestParam("eduId") Long edu_id, HttpSession session) {
		logger.info("#### eduInfo InfoController ####");
		eduBackgroundService.destory(Long.valueOf(edu_id));
		return "redirect:/admin/resume?active=edu";
		
	}
	
	/**
	 * update or create the teacher's workExpInfo
	 * @param work_Id
	 * @param workInfoForm
	 * @param validResult
	 * @param session
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/admin/workInfo",method = RequestMethod.POST)
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
		return "redirect:/admin/resume?active=work";
	}
	
	/**
	 * delete the teacher's workExpInfo
	 * @param work_id
	 * @param session
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/admin/workInfo/destory",method = RequestMethod.POST)
	public String destoryWorkInfo(@RequestParam("workId") Long work_id, HttpSession session) {
		logger.info("#### eduInfo InfoController ####");
		workExpService.destory(Long.valueOf(work_id));
		return "redirect:/admin/resume?active=work";
	}
	/**
	 * update or create the teacher's thesisInfo
	 * @param thesis_Id
	 * @param thesisDetailInfoForm
	 * @param validResult
	 * @param session
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/admin/thesisInfo",method = RequestMethod.POST)
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
		return "redirect:/admin/resume?active=thesis";
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
	@RequestMapping(value = "/admin/selfurl" , method = RequestMethod.POST)
	public String selfUrl(@Valid SelfUrlForm selfUrlForm,
			BindingResult validResult, HttpSession session) {

		logger.info("### in self url controller ###");

		if (validResult.hasErrors()) {
			logger.info("selfUrlForm Validation Failed " + validResult);
			return "redirect:/admin/details?active=url";
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
			return "redirect:/admin/details?active=url";
		}
	}
	
	@RequestMapping(value="/admin/thesis/new" , method = RequestMethod.POST)
	public String addThesis(@RequestParam("thesisId") Long thesis_id,@Valid TeacherThesisDetailInfoForm thesisDetailInfoForm, HttpSession session,
			Model model, BindingResult validResult){
		logger.info("#### workInfo Controller ####");
		if (validResult.hasErrors()) {
			logger.info("eduInfo Validation Failed " + validResult);
			return "redirect:/admin/resume?active=thesis";
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
		return "redirect:/admin/resume?active=thesis";
	}	
	
	@RequestMapping(value="/admin/thesis/destory",method=RequestMethod.POST)
	public String deleThesis(@RequestParam("thesisId") Long thesis_id){
		thesisService.deleteById(Long.valueOf(thesis_id));
		return "redirect:/admin/resume?active=thesis";
	}
	
	@RequestMapping(value="/admin/project/new" , method = RequestMethod.POST)
	public String addProject(@RequestParam("projectId") Long projectId, @Valid TeacherProjectDetailInfoForm projectDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherProjectAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/resume?active=project";
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
			return "redirect:/admin/resume?active=project";
		}
	}
	
	@RequestMapping(value="/admin/project/destory",method=RequestMethod.POST)
	public String deleProject(@RequestParam("projectId") Long project_id){
		projectService.deleteById(Long.valueOf(project_id));
		return "redirect:/admin/resume?active=project";
	}
	
	@RequestMapping(value="/admin/patent/new" , method = RequestMethod.POST)
	public String addPatent(@RequestParam("patentId")Long patentId,@Valid TeacherPatentDetailInfoForm patentDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherPatentAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/resume?active=patent";
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
					
			return "redirect:/admin/resume?active=patent";
		}
	}
	
	@RequestMapping(value="/admin/patent/destory",method=RequestMethod.POST)
	public String delePatent(@RequestParam("patentId")Long patent_id){
		patentService.deleteById(Long.valueOf(patent_id));
		return "redirect:/admin/resume?active=patent";
	}
	
	@RequestMapping(value="/admin/honor/new" , method = RequestMethod.POST)
	public String addHonor(@RequestParam("honorId") Long honorId, @Valid TeacherHonorDetailInfoForm honorDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherProjectAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/resume?active=honor";
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
				honorService.save(honor, userInfo.getUser());
			}
			return "redirect:/admin/resume?active=honor";
		}
	}
	
	@RequestMapping(value="/admin/honor/destory",method=RequestMethod.POST)
	public String deleHonor(@RequestParam("honorId")Long honor_id){
		honorService.deleteById(Long.valueOf(honor_id));
		return "redirect:/admin/resume?active=honor";
	}
	
	
	@RequestMapping(value = "/admin/personalInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse processFormAjaxJson(@Valid TeacherPersonalInfoForm personalInfoForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/admin/teacherContactAjax", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse contactInfoFormAjaxJson(@Valid ContactInfoForm contactInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/eduInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse eduInfoFormAjaxJson(@Valid TeacherEduInfoForm teacherEduInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/admin/workExpInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse workExpInfoFormAjaxJson(@Valid TeacherWorkExpInfoForm workInfoForm, BindingResult result) {
		//logger.info("------into workExp ajax");
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/selfurlInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse selfurInfoFormAjaxJson(@Valid SelfUrlForm selfUrlForm, BindingResult result) {
		//logger.info("------into selfur ajax");
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/admin/thesisInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse thesisInfoFormAjaxJson(@Valid TeacherThesisDetailInfoForm teacherThesisDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/projectInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse projectInfoFormAjaxJson(@Valid TeacherProjectDetailInfoForm teacherProjectDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/patentInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse patentInfoFormAjaxJson(@Valid TeacherPatentDetailInfoForm teacherPatentDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/honorInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse honorInfoFormAjaxJson(@Valid TeacherHonorDetailInfoForm teacherHonorDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}

}
