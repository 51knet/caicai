package com.knet51.courses.controllers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.Activity;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.EduBackground;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.WorkExp;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.entities.patent.PatentField;
import com.knet51.ccweb.jpa.entities.patent.PatentType;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.entities.requirement.PatentRequirement;
import com.knet51.ccweb.jpa.entities.requirement.Requirement;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;
import com.knet51.ccweb.jpa.entities.technology.Technology;
import com.knet51.courses.beans.UserInfo;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.TeacherCourseService;
import com.knet51.courses.jpa.services.TeacherService;
import com.knet51.courses.jpa.services.UserCourseService;
import com.knet51.courses.jpa.services.UserService;
import com.knet51.courses.jpa.services.achievement.EduBackgroundService;
import com.knet51.courses.jpa.services.achievement.TeacherHonorService;
import com.knet51.courses.jpa.services.achievement.TeacherPatentService;
import com.knet51.courses.jpa.services.achievement.TeacherProjectService;
import com.knet51.courses.jpa.services.achievement.TeacherThesisService;
import com.knet51.courses.jpa.services.achievement.WorkExpService;
import com.knet51.courses.jpa.services.activity.ActivityService;
import com.knet51.courses.jpa.services.annoncement.AnnouncementService;
import com.knet51.courses.jpa.services.patent.PatentFieldService;
import com.knet51.courses.jpa.services.patent.PatentService;
import com.knet51.courses.jpa.services.patent.PatentTypeService;
import com.knet51.courses.jpa.services.projects.ProjectsService;
import com.knet51.courses.jpa.services.requirement.PatentRequirementService;
import com.knet51.courses.jpa.services.requirement.TechRequirementService;
import com.knet51.courses.jpa.services.technology.TechnologyService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private TeacherCourseService courseService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserCourseService userCourseService;
	@Autowired
	private UserService userService;
	@Autowired
	private PatentService patentService;
	@Autowired
	private PatentTypeService patentTypeService;
	@Autowired
	private TechRequirementService techRequirementService;
	@Autowired
	private PatentFieldService patentFieldService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private PatentRequirementService patentRequirementService;
	@Autowired
	private TechnologyService technologyService;
	@Autowired
	private ProjectsService projectsService;
	
	@Autowired
	private EduBackgroundService eduBackgroundService;
	@Autowired
	private WorkExpService workExpService;
	@Autowired
	private TeacherThesisService thesisService;
	@Autowired
	private TeacherProjectService projectService;
	@Autowired
	private TeacherPatentService teacherPatentService;
	@Autowired
	private TeacherHonorService honorService;
	@Autowired
	private AnnouncementService annoService;
	
	
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session,
			HttpServletRequest request) {
		logger.info("###### into the HomeController ######");
		Page<Teacher> teacherPage = teacherService.findAllTeacher(0, 23);
		model.addAttribute("teacherPage", teacherPage);
		
		Page<Patent> chinaPatentPage = patentService.findPatentPageByCountryAndFocus(GlobalDefs.PATENT_CHINA, GlobalDefs.HOME_FOCUS, 0, 8);
		Page<Patent> foreignPatentPage = patentService.findPatentPageByCountryAndFocus(GlobalDefs.PATENT_FOREIGN, GlobalDefs.HOME_FOCUS, 0, 8);
		model.addAttribute("chinaPatentPage", chinaPatentPage);
		model.addAttribute("foreignPatentPage", foreignPatentPage);
		
		Page<PatentRequirement> patentRequire =  patentRequirementService.findAllByStatus(0, 8, GlobalDefs.PASS);
		Page<Requirement> technologyRequire = techRequirementService.findRequireByStatus(0, 8,GlobalDefs.PASS);
		model.addAttribute("patentRequire", patentRequire);
		model.addAttribute("technologyRequire", technologyRequire);
		
		Page<Activity> activitys = activityService.findAllByFilePathIsNotNull(0, 8);
		model.addAttribute("activitys", activitys);
		model.addAttribute("activityCount", activitys.getContent().size());
		
		List<PatentType> patentTypeList = patentTypeService.findAllPatentType();
		List<PatentField> patentFieldList = patentFieldService.findAll();
		session.setAttribute("patentFieldList", patentFieldList);
		model.addAttribute("patentTypeList", patentTypeList);
		
		Page<Technology> technologies = technologyService.findAllByFocusAndStatus(0, 9, GlobalDefs.HOME_FOCUS, GlobalDefs.PASS);
		model.addAttribute("technologys", technologies);
		
		String email;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equalsIgnoreCase(GlobalDefs.COOKIE_IDENTITY)) {
					email = new String(Base64.decode(c.getValue().getBytes()),
							Charset.forName("US-ASCII"));
					if(email != null && !email.equals("")){
						User user = userService.getValidEmail(email);
						if (user != null) {
							UserInfo userInfo = new UserInfo(user);
							session.setAttribute(GlobalDefs.SESSION_USER_INFO,
									userInfo);
						}
					}else{
						session.removeAttribute(GlobalDefs.SESSION_USER_INFO);
					}
				}
			}
		}

		session.setAttribute("teacherCount", GlobalDefs.HOME_TEACHER_COUNT);
		session.setAttribute("patentCount", GlobalDefs.HOME_PATENT_COUNT);
		session.setAttribute("requirementCount", GlobalDefs.HOME_PATENT_REQUIRE_COUNT);
		session.setAttribute("patentTradeCount", GlobalDefs.HOME_PATENT_TRADE_COUNT);
		session.setAttribute("patentCNCount", GlobalDefs.HOME_PATENT_CN_COUNT);
		
		model.addAttribute("active", "patent");
		
		Page<Projects> cpList = projectsService.findProjectsByCompleteAndStatus(0,3,GlobalDefs.PASS, GlobalDefs.COMPLETE);
		model.addAttribute("cpList", cpList);
		return "home";
	}

	@RequestMapping(value = "/course/study", method = RequestMethod.GET)
	public String adminStudent(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (userInfo != null) {
			return "redirect:/course/list/type";
		} else {
			return "redirect:/signin";
		}

	}
	
	@RequestMapping(value="/jumpToPatents", method = RequestMethod.GET)
	public void jumpToPatents(HttpServletResponse response) throws IOException{
		response.sendRedirect("/patents");
		return ;
	}
	
	// diplomat
	@RequestMapping(value="/diplomat") 
	public String showDiplomat(Model model,@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize){
		Page<Teacher> page = teacherService.findAllEnterpriseByisEnterprise(pageNumber, pageSize, "diplomat");
		model.addAttribute("page", page);
		return "diplomat.list";
	}
	@RequestMapping(value="/diplomat/{id}")
	public String showDiplomat(Model model, @PathVariable Long id){
		User user = userService.findOne(id);
		Teacher teacher=teacherService.findOne(id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);

		model.addAttribute("teacher_id", id);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("role", userInfo.getTeacherRole());
		model.addAttribute("teacher", teacher);
		return "diplomat.basic";
	}
	
	@RequestMapping(value="/diplomat/{teacher_id}/resume")
	public String showDiplomatResume(Model model, @PathVariable Long teacher_id){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		
		List<EduBackground> eduInfo = eduBackgroundService.findEduListByTeacherId(teacher_id);
		model.addAttribute("eduInfo", eduInfo);
		model.addAttribute("eduCount", eduInfo.size());

		List<WorkExp> workInfo = workExpService.findWorkList(teacher_id);
		model.addAttribute("workInfo", workInfo);
		model.addAttribute("workCount", workInfo.size());


		List<TeacherThesis> thesisList = thesisService.getAllThesisById(teacher_id);
		model.addAttribute("thesisList", thesisList);
		model.addAttribute("thesisCount", thesisList.size());

		List<TeacherProject> projectList = projectService
				.getAllProjectById(teacher_id);
		model.addAttribute("projectList", projectList);
		model.addAttribute("projectCount", projectList.size());

		List<TeacherHonor> honorList = honorService.getAllHonorById(teacher_id);
		model.addAttribute("honorList", honorList);
		model.addAttribute("honorCount", honorList.size());
		return "diplomat.resume";
	}
	
	/* diplomat front page */
	@RequestMapping(value="/diplomat/{teacher_id}/announcement/list")
	public String annoList(@PathVariable Long teacher_id,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<Announcement> page = annoService.findAllAnnoByUser(pageNumber, pageSize, user);
		model.addAttribute("page", page);
		return "diplomat.announcement.list";
	}
	
	@RequestMapping(value="/diplomat/{teacher_id}/announcement/view/{anno_id}")
	public String detailAnno(@PathVariable Long teacher_id,@PathVariable Long anno_id ,Model model){
		
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Announcement announcement = annoService.findOneById(anno_id);
		model.addAttribute("announcement", announcement);
		return "diplomat.announcement.detail";
	}
	
	@RequestMapping(value="/diplomat/{teacher_id}/project/list")
	public String projectList(@PathVariable Long teacher_id,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<Announcement> page = annoService.findAllAnnoByUser(pageNumber, pageSize, user);
		model.addAttribute("page", page);
		return "diplomat.project.list";
	}
	
	@RequestMapping(value="/diplomat/{teacher_id}/requirement/list")
	public String requirementList(@PathVariable Long teacher_id,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<Announcement> page = annoService.findAllAnnoByUser(pageNumber, pageSize, user);
		model.addAttribute("page", page);
		return "diplomat.requirement.list";
	}
	

}
