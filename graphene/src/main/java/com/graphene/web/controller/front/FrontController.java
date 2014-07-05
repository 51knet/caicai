package com.graphene.web.controller.front;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.graphene.web.common.beans.UserInfo;
import com.graphene.web.common.defs.GlobalDefs;
import com.graphene.web.jpa.entity.Activity;
import com.graphene.web.jpa.entity.announcement.Announcement;
import com.graphene.web.jpa.entity.patent.Patent;
import com.graphene.web.jpa.entity.require.TechRequirement;
import com.graphene.web.jpa.entity.resume.EduBackground;
import com.graphene.web.jpa.entity.resume.WorkExp;
import com.graphene.web.jpa.entity.teacher.Teacher;
import com.graphene.web.jpa.entity.tech.Technology;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.service.TeacherService;
import com.graphene.web.service.UserService;
import com.graphene.web.service.activity.ActivityService;
import com.graphene.web.service.announcement.AnnouncementService;
import com.graphene.web.service.patent.PatentService;
import com.graphene.web.service.requirement.TechRequireService;
import com.graphene.web.service.resume.EduBackgroundService;
import com.graphene.web.service.resume.WorkExpService;
import com.graphene.web.service.tech.TechnologyService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class FrontController {

	private static final Logger logger = LoggerFactory
			.getLogger(FrontController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private PatentService patentService;
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private TechnologyService techService;
	@Autowired
	private TechRequireService techRequireService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private WorkExpService workExpService;
	@Autowired
	private EduBackgroundService eduBakService;

	@RequestMapping(value="/front", method = RequestMethod.GET)
	public String showFrontHome(Model m){
		try {
			Page<Announcement> annoPage = announcementService.findAllByType(0, 7, "admin");
			List<Announcement> annoList = annoPage.getContent();
			m.addAttribute("annoList", annoList);
			
			Page<Activity> acPage = activityService.findAllPage(0, 7);
			List<Activity> acList = acPage.getContent();
			m.addAttribute("acList", acList);
			
			Page<Technology> techPage = techService.findAllByStatus(0, 6, GlobalDefs.PASS);
			List<Technology> techList = techPage.getContent();
			m.addAttribute("techList", techList);
			
			Page<Patent> patentPage = patentService.findPatentByStatus(0, 6, GlobalDefs.PASS);
			List<Patent> patentList = patentPage.getContent();
			m.addAttribute("patentList", patentList);
			
			Page<TechRequirement> techReqPage = techRequireService.findRequireByStatus(0, 6, GlobalDefs.PASS);
			List<TechRequirement> techReqList = techReqPage.getContent();
			m.addAttribute("techReqList", techReqList);
			
			Page<User> expertPage = userService.findUserByRole("teacher", 0, 6);
			List<User> expertList = expertPage.getContent();
			m.addAttribute("expertList", expertList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front.home";
	}
	
	@RequestMapping(value = "/id/{id}")
	public String userFront(@PathVariable Long id, HttpSession session,
			Model model) {
		User user;
		UserInfo userInfo;
		try {
			user = userService.findOne(id);
			userInfo = new UserInfo(user);
			model.addAttribute("userInfoModel", userInfo);

			String role = user.getRole();
			if (role.equals("user")) {
				return "redirect:/user/" + id;
			} else if (role.equals("teacher")) {
				return "redirect:/teacher/" + id;
			} else {
				return "404";
			}
		} catch (Exception e) {
			return "404";
		}
	}

	/**
	 * show the teacher's front page
	 * 
	 * @param id
	 * @param model
	 * @param session
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/teacher/{id}")
	public String teacherFront(@PathVariable Long id, Model model,
			HttpSession session)
			throws IOException {
		
		logger.info("#### Into teacher front page ####");
		try {
			User user = userService.findOne(id);
			if (user.getRole().equals("teacher")) {		
				Teacher teacher = teacherService.findOne(id);				
				UserInfo userInfo = new UserInfo(user);
				userInfo.setTeacher(teacher);

				model.addAttribute("userInfo", userInfo);					
				return "teacher.basic";
			} else {
				return "redirect:/id/" + id;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/allies/{user_id}")
	public String alliesFront(@PathVariable Long user_id, Model model,
			HttpSession session, HttpServletResponse response)throws IOException {
			logger.info("#### Into user front page ####");
			User user = userService.findOne(user_id);
			if (user.getMyright().equals("allies")) {
									
				UserInfo userInfo = new UserInfo(user);
				model.addAttribute("userInfo", userInfo);
				
				List<WorkExp> workList = workExpService.findWorkList(user_id);
				model.addAttribute("workInfo", workList);
				List<EduBackground> eduInfo = eduBakService.findEduListByTeacherId(user_id);
				model.addAttribute("eduInfo", eduInfo);
				Map<String,String> map = GlobalDefs.getUserEduExpMap();
				model.addAttribute("levelmap", map);
				
				return "allies.basic";
			} else {
				return "redirect:/id/" + user_id;
			}	
	}
	
	@RequestMapping(value = "/park/{user_id}")
	public String parkFront(@PathVariable Long user_id, Model model,
			HttpSession session, HttpServletResponse response)throws IOException {
			logger.info("#### Into user front page ####");
			User user = userService.findOne(user_id);
			if (user.getMyright().equals("allies")) {
									
				UserInfo userInfo = new UserInfo(user);
				model.addAttribute("userInfo", userInfo);					
				return "park.basic";
			} else {
				return "redirect:/id/" + user_id;
			}	
	}
	
	@RequestMapping(value="/front/announcement/{type}")
	public String showAnnouncement(Model model,@PathVariable String type,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<Announcement> page;
		if(type.equals("park") || type.equals("allies") || type.equals("admin")){
			page = announcementService.findAllByType(pageNumber, pageSize, type);
		}else{
			page = announcementService.findAllAnnoForSuperAdmin(pageNumber, pageSize);
		}
		model.addAttribute("page", page);
		return "front.announcement.list";
	}
	@RequestMapping(value="/front/announcement/view/{id}")
	public String showAnnouncementDetail(Model model,@PathVariable Long id){
		Announcement announcement = announcementService.findOne(id);
		model.addAttribute("announcement", announcement);
		return "front.announcement.view";
	}
	
	@RequestMapping(value="/front/patent/{type}")
	public String showPatent(Model model,@PathVariable String type,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<Patent> page;
		if(type.equals("china") || type.equals("foreign")){
			Integer code = type.equals("china")?GlobalDefs.CHINA:GlobalDefs.FOREIGN;
			page = patentService.findPatentByCountryAndStatus(pageNumber, pageSize, code, GlobalDefs.PASS);
		}else{
			page = patentService.findPatentByStatus(pageNumber, pageSize, GlobalDefs.PASS);
		}
		model.addAttribute("page", page);
		return "front.patent.list";
	}
	@RequestMapping(value="/front/patent/view/{id}")
	public String showPatentDetail(Model model,@PathVariable Long id){
		Patent patent = patentService.findOne(id);
		model.addAttribute("patent", patent);
		return "front.patent.view";
	}
	
	@RequestMapping(value="/front/technology/{type}")
	public String showTechnology(Model model,@PathVariable String type,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<Technology> page;
		page = techService.findAllByStatus(pageNumber, pageSize, GlobalDefs.PASS);
		model.addAttribute("page", page);
		return "front.technology.list";
	}
	@RequestMapping(value="/front/technology/view/{id}")
	public String showTechnologyDetail(Model model,@PathVariable Long id){
		Technology tech = techService.findOne(id);
		model.addAttribute("technology", tech);
		return "front.technology.view";
	}
	
	@RequestMapping(value="/front/requirement/{type}")
	public String showRequirement(Model model,@PathVariable String type,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<TechRequirement> page;
		page = techRequireService.findRequireByStatus(pageNumber, pageSize, GlobalDefs.PASS);
		model.addAttribute("page", page);
		return "front.requirement.list";
	}
	@RequestMapping(value="/front/requirement/view/{id}")
	public String showRequirementDetail(Model model,@PathVariable Long id){
		TechRequirement requirement = techRequireService.findOne(id);
		model.addAttribute("requirement", requirement);
		return "front.requirement.view"; 
	}
	
	@RequestMapping(value="/front/experts/{type}")
	public String showExperts(Model model,@PathVariable String type,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<User> expertPage = userService.findUserByRole("teacher", pageNumber, pageSize);
		model.addAttribute("page", expertPage);
		return "front.experts.list"; 
	}
	@RequestMapping(value="/front/allies/{type}")
	public String showAllies(Model model,@PathVariable String type,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<User> userPage = userService.findUserByRightAndRole("allies","user", pageNumber, pageSize);
		model.addAttribute("page", userPage);
		return "front.allies.list";
	}
	@RequestMapping(value="/front/parks/{type}")
	public String showPolicies(Model model,@PathVariable String type,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize){

		return "front.parks.list";
	}
	
	@RequestMapping(value="/front/activity/list")
	public String showActivity(Model model,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<Activity> page = activityService.findAllPage(pageNumber, pageSize);		
		model.addAttribute("page", page);
		return "front.announcement.list";
	}
	@RequestMapping(value="/front/activity/view/{id}")
	public String showActivityDetail(Model model,@PathVariable Long id){
		Activity activity = activityService.findOne(id);
		model.addAttribute("announcement", activity);
		return "front.announcement.view";
	}
	
}
