package com.knet51.ccweb.controllers.admin.teacher.achievement;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.achievement.TeacherHonorService;
import com.knet51.ccweb.jpa.services.achievement.TeacherPatentService;
import com.knet51.ccweb.jpa.services.achievement.TeacherProjectService;
import com.knet51.ccweb.jpa.services.achievement.TeacherThesisService;

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
	
	@RequestMapping(value="/admin/achievement/thesis/new")
	public String addThesis(@Valid TeacherThesisDetailInfoForm thesisDetailInfoForm, HttpSession session,
			Model model, BindingResult validResult){
		String content = thesisDetailInfoForm.getContent();
		logger.info("#### Into teacherThesisAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/achievement/list";
		}
		else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long id = userInfo.getUser().getId();
			Teacher teacher= teacherService.findOne(id);
			TeacherThesis thesis = new TeacherThesis();
			thesis.setContent(content);
			thesisService.save(thesis, teacher);
			return "redirect:/admin/achievement/list";
		}
	}
	
	@RequestMapping(value="/admin/achievement/thesis/destory/{thesis_id}")
	public String deleThesis(@PathVariable Long thesis_id){
		thesisService.deleteById(thesis_id);
		return "redirect:/admin/achievement/list";
	}
	
	@RequestMapping(value="/admin/achievement/project/new")
	public String addProject(@Valid TeacherProjectDetailInfoForm projectDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherProjectAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/achievement/list";
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
			return "redirect:/admin/achievement/list";
		}
	}
	
	@RequestMapping(value="/admin/achievement/project/destory/{project_id}")
	public String deleProject(@PathVariable Long project_id){
		projectService.deleteById(project_id);
		return "redirect:/admin/achievement/list";
	}
	
	@RequestMapping(value="/admin/achievement/patent/new")
	public String addPatent(@Valid TeacherPatentDetailInfoForm patentDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherPatentAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/achievement/list";
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
			return "redirect:/admin/achievement/list";
		}
	}
	
	@RequestMapping(value="/admin/achievement/patent/destory/{patent_id}")
	public String delePatent(@PathVariable Long patent_id){
		patentService.deleteById(patent_id);
		return "redirect:/admin/achievement/list";
	}
	
	@RequestMapping(value="/admin/achievement/honor/new")
	public String addHonor(@Valid TeacherHonorDetailInfoForm honorDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherProjectAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/achievement/list";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long id = userInfo.getUser().getId();
			Teacher teacher= teacherService.findOne(id);
			TeacherHonor honor = new TeacherHonor();
			honor.setName(honorDetailForm.getHonorName());
			honor.setReason(honorDetailForm.getReason());
			honorService.save(honor, teacher);
			return "redirect:/admin/achievement/list";
		}
	}
	
	@RequestMapping(value="/admin/achievement/honor/destory/{honor_id}")
	public String deleHonor(@PathVariable Long honor_id){
		honorService.deleteById(honor_id);
		return "redirect:/admin/achievement/list";
	}
}
