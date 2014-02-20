package com.knet51.patents.controllers.admin.projects;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.projects.ProjectsService;
import com.knet51.patents.util.ajax.AjaxValidationEngine;
import com.knet51.patents.util.ajax.ValidationResponse;

@Controller
public class ProjectsPageController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectsService projectsService;
	
	@RequestMapping("/admin/projects/list")
	public String showprojectsList(HttpSession session,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
	
		
		try {
			
			Page<Projects> page = projectsService.findProjectsByUser(user, pageNumber, pageSize);

			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin."+userInfo.getRole()+".projects.list";
	}
	
	@RequestMapping("/admin/projects/new")
	public String addprojectsList(HttpSession session,Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Map<String,String> projectsField = GlobalDefs.getProjectsField();
		model.addAttribute("projectsField", projectsField);
		return "admin."+userInfo.getRole()+".projects.new";
	}
	
	@RequestMapping("/admin/projects/edit/{project_id}")
	public String showprojectsPreview(HttpSession session,Model model,@PathVariable Long project_id){
		Projects projects = projectsService.findOne(project_id);
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(!userInfo.getId().equals(projects.getUser().getId())){
			return "redirect:/admin";
		}
		
		model.addAttribute("projects", projects);
		
		Map<String,String> projectsField = GlobalDefs.getProjectsField();
		model.addAttribute("projectsField", projectsField);
		return "admin."+userInfo.getRole()+".projects.edit";
	}
	@RequestMapping(value="/admin/projects/view/{project_id}")
	public String showprojectsDetail(Model model,HttpSession session,@PathVariable Long project_id){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Projects projects = projectsService.findOne(project_id);
		model.addAttribute("projects", projects);
		return "admin."+userInfo.getRole()+".projects.view";
	}
	
	@RequestMapping(value = "/admin/projects/projectsInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse projectsInfoFormAjaxJson(@Valid ProjectsForm projectsForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/admin/projects/edit/edit/projectsInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse projectsInfoFormUpdateAjaxJson(@Valid ProjectsForm projectsForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
}
