package com.knet51.courses.controllers;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Requirement;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.Course;
import com.knet51.ccweb.jpa.entities.courses.UserCourse;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.entities.patent.PatentField;
import com.knet51.ccweb.jpa.entities.patent.PatentType;
import com.knet51.courses.beans.CourseBeans;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.TeacherCourseService;
import com.knet51.courses.jpa.services.TeacherService;
import com.knet51.courses.jpa.services.UserCourseService;
import com.knet51.courses.jpa.services.UserService;
import com.knet51.courses.jpa.services.patent.PatentFieldService;
import com.knet51.courses.jpa.services.patent.PatentService;
import com.knet51.courses.jpa.services.patent.PatentTypeService;
import com.knet51.courses.jpa.services.requirement.RequirementService;

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
	private RequirementService requirementService;
	@Autowired
	private PatentFieldService patentFieldService;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session,
			HttpServletRequest request) {
		logger.info("###### into the HomeController ######");
		List<CourseBeans> cBeans = courseService.getAllCourseBeans();
		List<Teacher> teacherList = teacherService.findAllTeacher();
		List<Patent> patentList = patentService.findPatentList();
		List<Requirement> patentRequire =  new ArrayList<Requirement>();
		List<Requirement> technologyRequire =  new ArrayList<Requirement>();;
		List<Requirement> requirementList = requirementService.findAll();
		for (Iterator iterator = requirementList.iterator(); iterator.hasNext();) {
			Requirement requirement = (Requirement) iterator.next();
			if(requirement.getRequirType().getTypeName().equals("专利需求")){
				patentRequire.add(requirement);
			}else if(requirement.getRequirType().getTypeName().equals("技术需求")){
				technologyRequire.add(requirement);
			}
			
		}
		model.addAttribute("patentRequire", patentRequire);
		model.addAttribute("technologyRequire", technologyRequire);
		
		List<PatentType> patentTypeList = patentTypeService.findAllPatentType();
		List<PatentField> patentFieldList = patentFieldService.findAll();
		
		List<Teacher> teacherLists = new ArrayList<Teacher>();
		List<Teacher> enterPriseList = new ArrayList<Teacher>();
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
		for (Teacher teacher : teacherList) {
			if (teacher.getIsEnterprise() != null) {
				enterPriseList.add(teacher);
				model.addAttribute("enterPriseList", enterPriseList);
			} else {
				teacherLists.add(teacher);
				model.addAttribute("teacherLists", teacherLists);
			}
		}
		model.addAttribute("courseList", cBeans);
		model.addAttribute("courseCount", cBeans.size());
		model.addAttribute("teacherCount", teacherLists.size());
		model.addAttribute("patentList", patentList);
		model.addAttribute("patentCount", patentList.size());
		model.addAttribute("patentFieldList", patentFieldList);
		
		model.addAttribute("requirementList", requirementList);
		model.addAttribute("requirementCount", requirementList.size());
		model.addAttribute("patentTypeList", patentTypeList);
		
		UserInfo currentUser = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (currentUser != null) {
			List<UserCourse> userCourseList = userCourseService
					.findUserCourseByUserid(currentUser.getId());
		
			List<Course> userCourse = new ArrayList<Course>();
			for (UserCourse userCourses : userCourseList) {
				Course course = courseService.findOneById(userCourses.getTeachercourseid());
				userCourse.add(course);
			}
//			for (int i = 0; i < userCourseList.size(); i++) {
//				Course course = courseService.findOneById(userCourseList.get(i)
//						.getTeachercourseid());
//				userCourse.add(course);
//			}
			model.addAttribute("userCourse", userCourse);
			model.addAttribute("userCourseCount", userCourse.size());
		}
		model.addAttribute("active", "patent");
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
}
