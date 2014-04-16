package com.knet51.courses.controllers.projects;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.jpa.entities.UserRight;
import com.knet51.ccweb.jpa.entities.projects.BizModul;
import com.knet51.ccweb.jpa.entities.projects.PlanInfo;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.entities.projects.Rzfh;
import com.knet51.ccweb.jpa.entities.projects.TeamInfo;
import com.knet51.courses.beans.UserInfo;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.UserRightService;
import com.knet51.courses.jpa.services.UserService;
import com.knet51.courses.jpa.services.projects.BizModulService;
import com.knet51.courses.jpa.services.projects.PlanInfoService;
import com.knet51.courses.jpa.services.projects.ProjectsService;
import com.knet51.courses.jpa.services.projects.RzfhService;
import com.knet51.courses.jpa.services.projects.TeamInfoService;
import com.knet51.courses.util.MyUtil;


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
	@Autowired
	private UserRightService userRightService;
	
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
		List<Projects> upList = projectsService.findProjectsListByCompleteAndStatus(GlobalDefs.PASS, GlobalDefs.UN_COMPLETE);
		List<Projects> newpList = new ArrayList<Projects>();
		for (Projects projects : upList) {
			if(projects.getCurrentMoney().equals(0L)){
				newpList.add(projects);
			}
		}
		model.addAttribute("npList", newpList);
		model.addAttribute("upList", upList);
		
		List<Rzfh> rzList = rzfhService.findRzfhListByStatusAndTypes(GlobalDefs.PASS, GlobalDefs.RZJG);
		List<Rzfh> fhList = rzfhService.findRzfhListByStatusAndTypes(GlobalDefs.PASS, GlobalDefs.FHYQ);
		model.addAttribute("rzList", rzList);
		model.addAttribute("fhList", fhList);
		return "projects.list";
	}
	
	@RequestMapping(value="/projects/view/{project_id}")
	public String showprojectsDetail(Model model,@PathVariable Long project_id,HttpSession session){
//		UserInfo userInfo = (UserInfo) session
//				.getAttribute(GlobalDefs.SESSION_USER_INFO);
//		if(userInfo != null){
//			List<UserRight> userRightList = userRightService.findUserRightListByUser(userInfo.getUser());
//			model.addAttribute("userRightList", userRightList);
//		}
		try {
			Projects projects = projectsService.findOne(project_id);
//			BizModul bizModul = bizModulService.findByProjects(projects);
//			TeamInfo teamInfo = teamInfoService.findByProjects(projects);
//			PlanInfo planInfo = planInfoService.findByProjects(projects);
			model.addAttribute("projects", projects);
//			model.addAttribute("bizModul", bizModul);
//			model.addAttribute("teamInfo", teamInfo);
//			model.addAttribute("planInfo", planInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "projects.view"; 
	}
	
	@RequestMapping(value="/checkUserRight", method = RequestMethod.POST)
	public @ResponseBody boolean checkUserRight(HttpSession session){
		boolean flag = false;
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(userInfo != null){
			List<UserRight> userRightList = userRightService.findUserRightListByUser(userInfo.getUser());
			for (UserRight userRight : userRightList) {
				if(userRight.getUserRight().equals("investor") || userRight.getUserRight().equals("ledinvestor")){
					flag = true;
				}
			}
		}
		
		return flag;
	}
	
	@RequestMapping(value="/projects/search", method = RequestMethod.GET)
	public String searchProjects(Model model,HttpSession session,@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "9") int pageSize,@RequestParam("searchParam") String searchParam) throws Exception{
		searchParam = new String(searchParam.getBytes("iso-8859-1"), "utf-8").trim();
		String newsearchParam = MyUtil.replaceSpace(searchParam);
		Page<Projects> page = projectsService.findProjectsByStatusAndProjectNameLike(GlobalDefs.PASS,newsearchParam, pageNumber, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("searchParam", searchParam);
		return "projects.secondlist";
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
		model.addAttribute("types", types);
		model.addAttribute("page", page);
		return "rzfh.list";
	}
	
	// projects faq page controller
	
	@RequestMapping(value="/projects/about")
	public String showProjectsAbout(){
		return "projects.about";
	}
	
	@RequestMapping(value="/projects/guide")
	public String showProjectsGuide(){
		return "projects.guide";
	}
	
	@RequestMapping(value="/projects/rules")
	public String showProjectsRules(){
		return "projects.rules";
	}
}
