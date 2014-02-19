package com.knet51.courses.controllers.projects;

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
	
	@RequestMapping("/projects/list")
	public String showprojectsList(Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		try {
			Page<Projects> page = projectsService.findProjectsByStatus(GlobalDefs.PASS, pageNumber, pageSize);
			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "projects.list"; 
	}
	
	@RequestMapping(value="/projects/view/{project_id}")
	public String showprojectsDetail(Model model,@PathVariable Long project_id){
		Projects projects = projectsService.findOne(project_id);
		model.addAttribute("projects", projects);
		return "projects.view";
	}
	
}
