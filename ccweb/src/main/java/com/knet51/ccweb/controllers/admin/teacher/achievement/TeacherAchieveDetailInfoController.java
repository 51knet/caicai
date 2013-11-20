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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.entities.patent.PatentField;
import com.knet51.ccweb.jpa.entities.patent.PatentType;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.achievement.TeacherHonorService;
import com.knet51.ccweb.jpa.services.achievement.TeacherPatentService;
import com.knet51.ccweb.jpa.services.achievement.TeacherProjectService;
import com.knet51.ccweb.jpa.services.achievement.TeacherThesisService;
import com.knet51.ccweb.jpa.services.patent.PatentFieldService;
import com.knet51.ccweb.jpa.services.patent.PatentService;
import com.knet51.ccweb.jpa.services.patent.PatentTypeService;

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
	
	@RequestMapping(value="/admin/patent/add",method = RequestMethod.POST)
	public String addPatent(@Valid PatentForm patentForm, BindingResult validResult,HttpSession session,
			@RequestParam("patentType") Long type_id,Model model){
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/patent/new";
		}else{	
			Patent patent = new Patent();
			PatentType patentType = patentTypeService.findOne(type_id);
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			patent.setAddress(patentForm.getAddress());
			patent.setAgency(patentForm.getAgency());
			patent.setAgent(patentForm.getAgent());
			patent.setApplicant(patentForm.getApplicant());
			patent.setApplicationDate(patentForm.getApplicationDate());
			patent.setClassNum(patentForm.getClassNum());
			patent.setInventer(patentForm.getInventer());
			patent.setMainClassNum(patentForm.getMainClassNum());
			patent.setPatentName(patentForm.getPatentName());
			patent.setPatentNum(patentForm.getPatentNum());
			patent.setPublishDate(patentForm.getPublishDate());
			patent.setPublishNum(patentForm.getPublishNum());
			patent.setSummary(patentForm.getSummary());
			patent.setStatus(GlobalDefs.PATENT_STORE);
			patent.setPatentType(patentType);
			patent.setUser(userInfo.getUser());
			patent.setPatentField(patentForm.getPatentField());
			
			
			userPatentService.create(patent);
			model.addAttribute("patent", patent);
			return "admin.patent.view";
		}
	}
	
	@RequestMapping(value="/admin/patent/edit/add",method = RequestMethod.POST)
	public String editPatent(@Valid PatentForm patentForm, BindingResult validResult,HttpSession session,
			@RequestParam("patentType") Long type_id,Model model){
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/patent/edit/"+patentForm.getPatentNum();
		}else{	
			Patent patent = userPatentService.findOne(patentForm.getPatentNum());
			PatentType patentType = patentTypeService.findOne(type_id);
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			patent.setAddress(patentForm.getAddress());
			patent.setAgency(patentForm.getAgency());
			patent.setAgent(patentForm.getAgent());
			patent.setApplicant(patentForm.getApplicant());
			patent.setApplicationDate(patentForm.getApplicationDate());
			patent.setClassNum(patentForm.getClassNum());
			patent.setInventer(patentForm.getInventer());
			patent.setMainClassNum(patentForm.getMainClassNum());
			patent.setPatentName(patentForm.getPatentName());
			patent.setPatentNum(patentForm.getPatentNum());
			patent.setPublishDate(patentForm.getPublishDate());
			patent.setPublishNum(patentForm.getPublishNum());
			patent.setSummary(patentForm.getSummary());
			patent.setStatus(GlobalDefs.PATENT_STORE);
			patent.setPatentType(patentType);
			patent.setUser(userInfo.getUser());
			patent.setPatentField(patentForm.getPatentField());
			
			userPatentService.update(patent);
			model.addAttribute("patent", patent);
			return "admin.patent.view";
		}
	}
	
	@RequestMapping(value="/admin/patent/delete",method = RequestMethod.POST)
	public String destoryPatent(@RequestParam("patentNum") String patentNum){
		userPatentService.delete(patentNum);
		return "redirect:/admin/patent/list";
	}
}
