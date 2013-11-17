package com.knet51.ccweb.controllers.admin.teacher.achievement;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.entities.patent.PatentField;
import com.knet51.ccweb.jpa.entities.patent.PatentType;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.achievement.TeacherHonorService;
import com.knet51.ccweb.jpa.services.achievement.TeacherPatentService;
import com.knet51.ccweb.jpa.services.achievement.TeacherProjectService;
import com.knet51.ccweb.jpa.services.achievement.TeacherThesisService;
import com.knet51.ccweb.jpa.services.patent.PatentFieldService;
import com.knet51.ccweb.jpa.services.patent.PatentService;
import com.knet51.ccweb.jpa.services.patent.PatentTypeService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;

@Controller
public class TeacherAchievePageController {
	private static final Logger logger = LoggerFactory.getLogger(TeacherAchievePageController.class);
	@Autowired
	private TeacherThesisService thesisService;
	@Autowired
	private TeacherProjectService projectService;
	@Autowired
	private TeacherPatentService patentService;
	@Autowired
	private TeacherHonorService honorService;
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private PatentService userPatentService;
	@Autowired
	private PatentTypeService patentTypeService;
	@Autowired
	private PatentFieldService patentFieldService;
	
	
	@RequestMapping(value="/admin/achievement/list")
	public String teacherAchievement(HttpSession session,Model model){
		logger.info("#### Into teacher achievement page ####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		List<TeacherThesis> thesis = thesisService.getAllThesisById(user.getId());
		List<TeacherProject> project = projectService.getAllProjectById(user.getId());
		List<TeacherPatent> patent = patentService.getAllPatentById(user.getId());
		List<TeacherHonor> honor = honorService.getAllHonorById(user.getId());
		model.addAttribute("project", project);
		model.addAttribute("projectCount", project.size());
		model.addAttribute("thesis", thesis);
		model.addAttribute("thesisCount", thesis.size());
		model.addAttribute("patent", patent);
		model.addAttribute("patentCount", patent.size());
		model.addAttribute("honor", honor);
		model.addAttribute("honorCount", honor.size());
		return "admin.teacher.achievement.list";
	}
	@RequestMapping(value = "/admin/achievement/thesisInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse thesisInfoFormAjaxJson(@Valid TeacherThesisDetailInfoForm teacherThesisDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/achievement/projectInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse projectInfoFormAjaxJson(@Valid TeacherProjectDetailInfoForm teacherProjectDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/achievement/patentInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse patentInfoFormAjaxJson(@Valid TeacherPatentDetailInfoForm teacherPatentDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	@RequestMapping(value = "/admin/achievement/honorInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse honorInfoFormAjaxJson(@Valid TeacherHonorDetailInfoForm teacherHonorDetailInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping("/admin/patent/list")
	public String showPatentList(HttpSession session,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		try {
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			User user = userInfo.getUser();
			List<Patent> patentList = userPatentService.findPatentListByUser(user);
			Page<Patent> page = userPatentService.findPatentByUser(pageNumber, pageSize, user);
			model.addAttribute("patent", patentList);
			model.addAttribute("patentCount", patentList.size());
			model.addAttribute("page", page);
		} catch (Exception e) {
			// TODO: hadle exception
			e.printStackTrace();
		}
		return "admin.patent.list";
	}
	
	@RequestMapping("/admin/patent/new")
	public String addPatentList(HttpSession session,Model model){
		List<PatentType> pTypeList = patentTypeService.findAllPatentType();
		List<PatentField> pFieldList = patentFieldService.findAll();
		model.addAttribute("pTypeList", pTypeList);
		model.addAttribute("pFieldList", pFieldList);
		return "admin.patent.new";
	}
	
	@RequestMapping("/admin/patent/edit/{patentNum}")
	public String showPatentPreview(HttpSession session,Model model,@PathVariable String patentNum){
		Patent patent = userPatentService.findOne(patentNum);
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		
		if(!userInfo.getId().equals(patent.getUser().getId())){
			return "redirect:/admin";
		}
		return "admin.patent.edit";
	}
	@RequestMapping("/admin/patent/view/{patentNum}")
	public String showPatentView(@PathVariable String patentNum,Model model){
		Patent patent = userPatentService.findOne(patentNum);
		model.addAttribute("patent", patent);
		return "admin.patent.view";
	}
}
