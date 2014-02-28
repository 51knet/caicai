package com.knet51.courses.controllers.projects;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.projects.BizModul;
import com.knet51.ccweb.jpa.entities.projects.PlanInfo;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.entities.projects.Rzfh;
import com.knet51.ccweb.jpa.entities.projects.TeamInfo;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.UserService;
import com.knet51.courses.jpa.services.projects.BizModulService;
import com.knet51.courses.jpa.services.projects.PlanInfoService;
import com.knet51.courses.jpa.services.projects.ProjectsService;
import com.knet51.courses.jpa.services.projects.RzfhService;
import com.knet51.courses.jpa.services.projects.TeamInfoService;


@Controller
public class ProjectsPageController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectsService projectsService;
	@Autowired
	private RzfhService rzfhService;
	@Autowired
	private BizModulService bizModulService;
	@Autowired
	private TeamInfoService teamInfoService;
	@Autowired
	private PlanInfoService planInfoService;
	
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
	public String showprojectsList(Model model){
		List<Projects> cpList = projectsService.findProjectsListByCompleteAndStatus(GlobalDefs.PASS, GlobalDefs.COMPLETE);
		List<Projects> upList = projectsService.findProjectsListByCompleteAndStatus(GlobalDefs.PASS, GlobalDefs.UN_COMPLETE);
		model.addAttribute("cpList", cpList);
		model.addAttribute("upList", upList);
		return "projects.list";
	}
	
	@RequestMapping(value="/projects/view/{project_id}")
	public String showprojectsDetail(Model model,@PathVariable Long project_id){
		Projects projects = projectsService.findOne(project_id);
		BizModul bizModul = bizModulService.findByProjects(projects);
		TeamInfo teamInfo = teamInfoService.findByProjects(projects);
		PlanInfo planInfo = planInfoService.findByProjects(projects);
		model.addAttribute("projects", projects);
		model.addAttribute("bizModul", bizModul);
		model.addAttribute("teamInfo", teamInfo);
		model.addAttribute("planInfo", planInfo);
		return "projects.view"; 
	}
	
	@RequestMapping(value="/rzfh/list/{types}")
	public String showAllRzfh(@PathVariable String types,Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<Rzfh> page = null;
		if(types.equals("rzjg")){
			page = rzfhService.findRzfhByStatusAndTypes(pageNumber, pageSize, GlobalDefs.PASS, GlobalDefs.RZJG);
		}else if(types.equals("fhyq")){
			page = rzfhService.findRzfhByStatusAndTypes(pageNumber, pageSize, GlobalDefs.PASS, GlobalDefs.FHYQ);
		}else{
			page = rzfhService.findAll(pageNumber, pageSize);
		}
		model.addAttribute("page", page);
		return "rzfh.list";
	}
}
