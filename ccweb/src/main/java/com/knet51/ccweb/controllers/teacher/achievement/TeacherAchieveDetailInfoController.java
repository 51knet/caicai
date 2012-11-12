package com.knet51.ccweb.controllers.teacher.achievement;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherHonorService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherPatentService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherProjectService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherThesisService;

@Controller
public class TeacherAchieveDetailInfoController {
	
	private static Logger logger = 
			LoggerFactory.getLogger(TeacherAchieveDetailInfoController.class);
	@Autowired
	private TeacherThesisService thesisService;
	@Autowired
	private TeacherProjectService projectService;
	@Autowired
	private TeacherPatentService patentService;
	@Autowired
	private TeacherHonorService honorService;
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value="/admin/teacher/achievement/thesis/addThesis")
	public String addThesis(@Valid TeacherThesisDetailInfoForm thesisDetailInfoForm, HttpSession session,
			Model model, BindingResult validResult){
		String content = thesisDetailInfoForm.getContent();
		logger.info("#### Into teacherThesisAddController ####");
		if(validResult.hasErrors()){
			return "admin/teacher/achievement/detail";
		}
		else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long id = userInfo.getUser().getId();
			Teacher teacher= teacherService.findOne(id);
			TeacherThesis thesis = new TeacherThesis();
			thesis.setContent(content);
			thesisService.save(thesis, teacher);
			return "redirect:/admin/teacher/achievement/detail";
		}
	}
	
	@RequestMapping(value="/admin/teacher/achievement/thesis/deleThesis")
	public String deleThesis(@RequestParam("id") Long id){
		thesisService.deleteById(id);
		return "redirect:/admin/teacher/achievement/detail";
	}
	
	@RequestMapping(value="/admin/teacher/achievement/project/addProject")
	public String addProject(@Valid TeacherProjectDetailInfoForm projectDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherProjectAddController ####");
		if(validResult.hasErrors()){
			return "admin/teacher/achievement/detail";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long id = userInfo.getUser().getId();
			Teacher teacher= teacherService.findOne(id);
			TeacherProject project = new TeacherProject();
			project.setTitle(projectDetailForm.getTitle());
			project.setSource(projectDetailForm.getSource());
			project.setDate(projectDetailForm.getDate());
			projectService.save(project, teacher);
			return "redirect:/admin/teacher/achievement/detail";
		}
	}
	
	@RequestMapping(value="/admin/teacher/achievement/project/deleProject")
	public String deleProject(@RequestParam("id") Long id){
		projectService.deleteById(id);
		return "redirect:/admin/teacher/achievement/detail";
	}
	
	@RequestMapping(value="/admin/teacher/achievement/patent/addPatent")
	public String addPatent(@Valid TeacherPatentDetailInfoForm patentDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherPatentAddController ####");
		if(validResult.hasErrors()){
			return "admin/teacher/achievement/detail";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long id = userInfo.getUser().getId();
			Teacher teacher= teacherService.findOne(id);
			TeacherPatent patent = new TeacherPatent();
			patent.setInventer(patentDetailForm.getInventer());
			patent.setName(patentDetailForm.getName());
			patent.setNumber(patentDetailForm.getNumber());
			patent.setType(patentDetailForm.getType());
			patentService.save(patent, teacher);
			return "redirect:/admin/teacher/achievement/detail";
		}
	}
	
	@RequestMapping(value="/admin/teacher/achievement/patent/delePatent")
	public String delePatent(@RequestParam("id") Long id){
		patentService.deleteById(id);
		return "redirect:/admin/teacher/achievement/detail";
	}
	
	@RequestMapping(value="/admin/teacher/achievement/honor/addHonor")
	public String addHonor(@Valid TeacherHonorDetailInfoForm honorDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherProjectAddController ####");
		if(validResult.hasErrors()){
			return "admin/teacher/achievement/detail";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long id = userInfo.getUser().getId();
			Teacher teacher= teacherService.findOne(id);
			TeacherHonor honor = new TeacherHonor();
			honor.setName(honorDetailForm.getName());
			honor.setReason(honorDetailForm.getReason());
			honorService.save(honor, teacher);
			return "redirect:/admin/teacher/achievement/detail";
		}
	}
	
	@RequestMapping(value="/admin/teacher/achievement/honor/deleHonor")
	public String deleHonor(@RequestParam("id") Long id){
		honorService.deleteById(id);
		return "redirect:/admin/teacher/achievement/detail";
	}
}
