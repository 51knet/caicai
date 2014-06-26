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
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.jpa.entities.Activity;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.entities.patent.PatentField;
import com.knet51.ccweb.jpa.entities.patent.PatentType;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.ccweb.jpa.entities.requirement.PatentRequirement;
import com.knet51.ccweb.jpa.entities.requirement.Requirement;
import com.knet51.ccweb.jpa.entities.technology.Technology;
import com.knet51.courses.beans.UserInfo;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.TeacherCourseService;
import com.knet51.courses.jpa.services.TeacherService;
import com.knet51.courses.jpa.services.UserCourseService;
import com.knet51.courses.jpa.services.UserService;
import com.knet51.courses.jpa.services.activity.ActivityService;
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
	
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session,
			HttpServletRequest request) {
		logger.info("###### into the HomeController ######");
		List<Teacher> teacherList = teacherService.findAllTeacher();
		System.out.println("1===================="+new Date());
		List<Patent> chinaPatentList = patentService.findPatentByCountryAndFocus(GlobalDefs.PATENT_CHINA, GlobalDefs.HOME_FOCUS);
		List<Patent> foreignPatentList = patentService.findPatentByCountryAndFocus(GlobalDefs.PATENT_FOREIGN, GlobalDefs.HOME_FOCUS);
		model.addAttribute("chinaPatentList", chinaPatentList);
		model.addAttribute("foreignPatentList", foreignPatentList);
		
		System.out.println("2===================="+new Date());
		List<PatentRequirement> patentRequire =  patentRequirementService.findAllListByStatus(GlobalDefs.PASS);
		List<Requirement> technologyRequire = techRequirementService.findRequireListByStatus(GlobalDefs.PASS);
		model.addAttribute("patentRequire", patentRequire);
		model.addAttribute("technologyRequire", technologyRequire);
		
		List<Activity> activityList = activityService.findAllList();
		model.addAttribute("activityList", activityList);
		System.out.println("3===================="+new Date());
		List<PatentType> patentTypeList = patentTypeService.findAllPatentType();
		List<PatentField> patentFieldList = patentFieldService.findAll();
		session.setAttribute("patentFieldList", patentFieldList);
		model.addAttribute("patentTypeList", patentTypeList);
		System.out.println("4===================="+new Date());
		List<Teacher> teacherLists = new ArrayList<Teacher>();
		List<Teacher> enterPriseList = new ArrayList<Teacher>();
		for (Teacher teacher : teacherList) {
			if (teacher.getIsEnterprise() != null) {
				enterPriseList.add(teacher);
				model.addAttribute("enterPriseList", enterPriseList);
			} else {
				teacherLists.add(teacher);
				model.addAttribute("teacherLists", teacherLists);
			}
		}
		
		List<Technology> technologies = technologyService.findListByFocusAndStatus(GlobalDefs.HOME_FOCUS, GlobalDefs.PASS);
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
//		model.addAttribute("courseList", cBeans);
//		model.addAttribute("courseCount", cBeans.size());
		//model.addAttribute("teacherCount", );
		session.setAttribute("teacherCount", GlobalDefs.HOME_TEACHER_COUNT);
		session.setAttribute("patentCount", GlobalDefs.HOME_PATENT_COUNT);
		session.setAttribute("requirementCount", GlobalDefs.HOME_PATENT_REQUIRE_COUNT);
		session.setAttribute("patentTradeCount", GlobalDefs.HOME_PATENT_TRADE_COUNT);
		session.setAttribute("patentCNCount", GlobalDefs.HOME_PATENT_CN_COUNT);
		
		UserInfo currentUser = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
//		if (currentUser != null) {
//			List<UserCourse> userCourseList = userCourseService
//					.findUserCourseByUserid(currentUser.getId());
//		
//			List<Course> userCourse = new ArrayList<Course>();
//			for (UserCourse userCourses : userCourseList) {
//				Course course = courseService.findOneById(userCourses.getTeachercourseid());
//				userCourse.add(course);
//			}
////			for (int i = 0; i < userCourseList.size(); i++) {
////				Course course = courseService.findOneById(userCourseList.get(i)
////						.getTeachercourseid());
////				userCourse.add(course);
////			}
//			model.addAttribute("userCourse", userCourse);
//			model.addAttribute("userCourseCount", userCourse.size());
//		}
		model.addAttribute("active", "patent");
		
		List<Projects> cpList = projectsService.findProjectsListByCompleteAndStatus(GlobalDefs.PASS, GlobalDefs.COMPLETE);
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
	
}
