package com.knet51.courses.controllers.projects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.UserService;
import com.knet51.courses.jpa.services.projects.ProjectsService;

@Controller
public class ProjectsPageController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectsService projectsService;
	
	@RequestMapping("/projects/list/{status}")
	public String showprojectsPage(Model model,@PathVariable String status,@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "9") int pageSize){
		Page<Projects> page ;
		if(status.equals("complete") ){
			page = projectsService.findProjectsByCompleteAndStatus(pageNumber, pageSize, GlobalDefs.COMPLETE, GlobalDefs.PASS);
		}else if(status.equals("uncomplete") ){
			page = projectsService.findProjectsByCompleteAndStatus(pageNumber, pageSize, GlobalDefs.UN_COMPLETE, GlobalDefs.PASS);
		}else{
			page = projectsService.findProjectsByStatus(pageNumber, pageSize, GlobalDefs.PASS);
		}
		model.addAttribute("page", page);
		return "projects.secondlist"; 
	}
	
	@RequestMapping("/projects/list")
	public String showprojectsList(Model model,@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "9") int pageSize){
		Page<Projects> page = projectsService.findProjectsByStatus(pageNumber, pageSize, GlobalDefs.PASS);

		model.addAttribute("page", page);
		return "projects.list"; 
	}
	
	@RequestMapping(value="/projects/view/{project_id}")
	public String showprojectsDetail(Model model,@PathVariable Long project_id){
		Projects projects = projectsService.findOne(project_id);
		model.addAttribute("projects", projects);
		return "projects.view";
	}
	
}
