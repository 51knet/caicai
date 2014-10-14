package com.knet51.diplomat.controllers.admin.kefu.projects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.diplomat.controllers.common.defs.GlobalDefs;
import com.knet51.diplomat.jpa.services.projects.ProjectsService;
@Controller
public class KefuProjectsController {
	@Autowired
	private ProjectsService projectsService;
	@RequestMapping(value="/admin/kefu/projects/list/{status}")
	public String showAllprojects(@PathVariable String status,Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<Projects> page = null;
		if(status.equals("pass")){
			page = projectsService.findProjectsByStatus(pageNumber, pageSize, GlobalDefs.PASS);
		}else if(status.equals("waite")){
			page = projectsService.findProjectsByStatus(pageNumber, pageSize, GlobalDefs.WAITE);
		}else{
			page = projectsService.findProjectsPage(pageNumber, pageSize);
		}
		model.addAttribute("page", page);
		return "admin.kefu.projects.list";
	}
	

	

	@RequestMapping(value="/admin/kefu/projects/view/{id}")
	public String showprojectsDetail(Model model,HttpSession session,@PathVariable Long id){
		Projects projects = projectsService.findOne(id);
		model.addAttribute("projects", projects);
		return "admin.kefu.projects.view";
	}
	

	
	@RequestMapping(value="/admin/kefu/projects/status/change",method=RequestMethod.POST)
	public @ResponseBody boolean changeprojectsStatus(@RequestParam("id") Long id){
		boolean flag = false;
		Projects projects = projectsService.findOne(id);
		if(projects!= null ){
			if(projects.getStatus().equals(GlobalDefs.PASS)){
				projects.setStatus(GlobalDefs.WAITE);
			}else{
				projects.setStatus(GlobalDefs.PASS);
			}
		}
		projects = projectsService.update(projects);
		
		if(projects != null){
			flag = true;
		}
		return flag;
	}
}
