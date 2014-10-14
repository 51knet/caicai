package com.knet51.diplomat.controllers.common.project;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.diplomat.controllers.common.defs.GlobalDefs;
import com.knet51.diplomat.jpa.services.UserService;
import com.knet51.diplomat.jpa.services.projects.ProjectsService;

public class CommonProjectsController {
	@Autowired
	private ProjectsService projectsService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/{roletype}/{u_id}/projects/list")
	public String showProjectList(@PathVariable String roletype, @PathVariable Long u_id, HttpSession session, Model model,
			@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize ){
		User user = userService.findOne(u_id); 
		Page<Projects> page = projectsService.findProjectsByUserAndStatus(user, GlobalDefs.PASS, pageNumber, pageSize);
		model.addAttribute("page", page);
		return roletype+".project.list";
	}
	
	@RequestMapping(value="/{roletype}/{u_id}/projects/view/p_id")
	public String showprojectView(@PathVariable String roletype, @PathVariable Long u_id,@PathVariable Long p_id,Model model){
		Projects project = projectsService.findOne(p_id);
		model.addAttribute("project", project);
		return roletype+".project.detail";
	}
}
