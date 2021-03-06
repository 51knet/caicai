package com.knet51.patents.controllers.admin.teacher.achievement;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.admin.patent.PatentForm;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.entities.patent.PatentType;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;
import com.knet51.patents.jpa.services.TeacherService;
import com.knet51.patents.jpa.services.achievement.TeacherHonorService;
import com.knet51.patents.jpa.services.achievement.TeacherPatentService;
import com.knet51.patents.jpa.services.achievement.TeacherProjectService;
import com.knet51.patents.jpa.services.achievement.TeacherThesisService;
import com.knet51.patents.jpa.services.patent.PatentFieldService;
import com.knet51.patents.jpa.services.patent.PatentService;
import com.knet51.patents.jpa.services.patent.PatentTypeService;

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
	@Autowired
	private PatentTypeService patentTypeService;
	@Autowired
	private PatentFieldService patentFieldService;
	@Autowired
	private PatentService userPatentService;
	
	@RequestMapping(value="/admin/achievement/thesis/new" , method = RequestMethod.POST)
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
	
	@RequestMapping(value="/admin/achievement/project/new",method = RequestMethod.POST)
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
	
	@RequestMapping(value="/admin/achievement/patent/new",method = RequestMethod.POST)
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
	
	@RequestMapping(value="/admin/achievement/honor/new",method = RequestMethod.POST)
	public String addHonor(@Valid TeacherHonorDetailInfoForm honorDetailForm, HttpSession session,
			Model model,BindingResult validResult){
		logger.info("#### Into teacherProjectAddController ####");
		if(validResult.hasErrors()){
			return "redirect:/admin/achievement/list";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			TeacherHonor honor = new TeacherHonor();
			honor.setName(honorDetailForm.getHonorName());
			honor.setReason(honorDetailForm.getReason());
			honorService.save(honor, userInfo.getUser());
			return "redirect:/admin/achievement/list";
		}
	}
	
	@RequestMapping(value="/admin/achievement/honor/destory/{honor_id}")
	public String deleHonor(@PathVariable Long honor_id){
		honorService.deleteById(honor_id);
		return "redirect:/admin/achievement/list";
	}
}
