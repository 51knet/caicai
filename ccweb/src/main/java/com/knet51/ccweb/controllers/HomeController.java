package com.knet51.ccweb.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
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

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.Student;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;
import com.knet51.ccweb.jpa.entities.resource.Resource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.BlogService;
import com.knet51.ccweb.jpa.services.FriendsRelateService;
import com.knet51.ccweb.jpa.services.ResourceService;
import com.knet51.ccweb.jpa.services.StudentService;
import com.knet51.ccweb.jpa.services.TeacherCourseService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherHonorService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherPatentService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherProjectService;
import com.knet51.ccweb.jpa.services.teacherAchievement.TeacherThesisService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ResourceService resourceService;

	@Autowired
	private TeacherHonorService honorService;

	@Autowired
	private TeacherProjectService projectService;

	@Autowired
	private TeacherPatentService patentService;

	@Autowired
	private TeacherThesisService thesisService;
	@Autowired
	private StudentService studentService;

	@Autowired
	private TeacherCourseService courseService;

	@Autowired
	private FriendsRelateService friendsRelateService;

	@Autowired
	private BlogService blogService;

	@Autowired
	private AnnouncementService announcementService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session,
			HttpServletRequest request) {
		/*
		 * Cookie[] cookies = request.getCookies(); String email = null; if
		 * (cookies != null) { for (Cookie cookie : cookies) {
		 * if(cookie.getName().equals(GlobalDefs.COOKIE_IDENTITY)) { String val
		 * = cookie.getValue(); // the value in cookie was encoded email = new
		 * String(Base64.decode(val.getBytes()), Charset.forName("US-ASCII"));
		 * logger.debug("cookie encodedEmail:"+val+";decodedEmail:"+email);
		 * break; } } if (email != null) { User user =
		 * userService.findByEmailAddress(email); // confirmed users; UserInfo
		 * userInfo = new UserInfo(user);
		 * session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
		 * 
		 * return "redirect:/admin"; } }
		 */
		// we can achieve auto login through above code,
		// comment it out for now since I am not quite clear how we should
		// control the auto login
		return "home";
	}

	@RequestMapping(value = "/user/{id}")
	public String userFront(@PathVariable String id, HttpSession session,
			Model model) {
		User user;
		UserInfo userInfo;
		try {
			user = userService.findOne(Long.parseLong(id));
			userInfo = new UserInfo(user);
			model.addAttribute("userInfoModel", userInfo);

			String role = user.getRole();
			if (role.equals("user")) {
				return "user.basic";
			} else if (role.equals("teacher")) {
				return "redirect:/teacher/" + id;
			}else if(role.equals("student")) {
				return "redirect:/student/"+ id;
			}else {
				return "404";
			}
		} catch (Exception e) {
			// TODO: refining exception;
			return "404";
		}

	}

	@RequestMapping(value = "/teacher/{id}")
	public String teacherFront(@PathVariable Long id, Model model,
			HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		logger.info("#### Into teacher front page ####");
		try {
			User user = userService.findOne(id);
			UserInfo sessionUserInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			boolean isFollower = false;
			if (sessionUserInfo!=null) { // this is only valid when user logged in and see teacher home page
				User sessionUser = sessionUserInfo.getUser();
				isFollower = friendsRelateService.isTheFollower(id, sessionUser.getId());
			}
//			Announcement announcement = announcementService.findLatestByUid(id);
//			if(announcement!=null) {
//				String anno = announcement.getContent();
//				String annoContent = (anno.length()>100) ? anno.substring(0, 100) : anno;
//				model.addAttribute("annoContent", annoContent);
//				model.addAttribute("annoId", announcement.getId());
//			}
			
			

			Teacher teacher = teacherService.findOne(id);
			Page<BlogPost> page = blogService.findAllBlogs(0, 5, teacher);
			List<BlogPost> blogPosts = page.getContent();
			model.addAttribute("blogPosts", blogPosts);
			
			Page<Announcement> annoPage = announcementService.findAllAnnoByUser(0, 4, user);
			model.addAttribute("annolist", annoPage.getContent());
			model.addAttribute("annoCount", annoPage.getContent().size());
			Page<Resource> pageResource = resourceService.findAllResouByUser(0,
					5, user);
			List<Resource> resourceList = pageResource.getContent();
			Integer resourceCount = resourceService.listAllByUid(id).size();
			model.addAttribute("resourceList", resourceList);
			model.addAttribute("resourceCount", resourceCount);

			Page<TeacherHonor> pageHonor = honorService.findAllHonorByTeacher(
					0, 2, teacher);
			List<TeacherHonor> honorList = pageHonor.getContent();
			Integer honorCount = honorService.getAllHonorById(id).size();
			model.addAttribute("honorList", honorList);
			model.addAttribute("honorCount", honorCount);

			Page<TeacherPatent> pagePatent = patentService
					.findAllPatentByTeacher(0, 2, teacher);
			List<TeacherPatent> patentList = pagePatent.getContent();
			Integer patentCount = patentService.getAllPatentById(id).size();
			model.addAttribute("patentList", patentList);
			model.addAttribute("patentCount", patentCount);

			Page<TeacherThesis> pageThesis = thesisService
					.findAllThesisByTeacher(0, 2, teacher);
			List<TeacherThesis> thesisList = pageThesis.getContent();
			Integer thesisCount = thesisService.getAllThesisById(id).size();
			model.addAttribute("thesisList", thesisList);
			model.addAttribute("thesisCount", thesisCount);
			

			Page<TeacherProject> pageProject = projectService
					.findAllProjectByTeacher(0, 2, teacher);
			List<TeacherProject> projectList = pageProject.getContent();
			Integer projectCount = projectService.getAllProjectById(id).size();
			model.addAttribute("projectList", projectList);
			model.addAttribute("projectCount", projectCount);

			Page<TeacherCourse> pageCourse = courseService
					.findAllCourseByTeacher(0, 5, teacher);
			List<TeacherCourse> courseList = pageCourse.getContent();
			Integer courseCount = courseService.getAllTeacherCourseById(id)
					.size();
			model.addAttribute("courseList", courseList);
			model.addAttribute("courseCount", courseCount);
			
			List<Announcement> annoList = announcementService.findAllByUid(id);
			model.addAttribute("annoCount", annoList.size());

			UserInfo userInfo = new UserInfo(user);
			//userInfo.setAnnouncement(announcement);
			userInfo.setTeacher(teacher);

			Integer fansCount = friendsRelateService.getAllFans(id).size();
			Integer hostCount = friendsRelateService.getAllHost(id).size();

			model.addAttribute("teacher_id", id);
			model.addAttribute("teacherInfo", userInfo);
			//model.addAttribute("announcement", announcement);
		
			model.addAttribute("role", userInfo.getTeacherRole());
			
			//FIXME: WTF?!!!  
			// model.addAttribute("followValue",followValue);
			session.setAttribute("isFollower", isFollower);
			// model.addAttribute("fansCount", fansCount);
			session.setAttribute("fansCount", fansCount);
			// model.addAttribute("hostCount", hostCount);
			session.setAttribute("hostCount", hostCount);
			return "teacher.basic";
		} catch (Exception e) {
			e.printStackTrace();
			return "404";
		}
	}
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);

		if (userInfo != null && userInfo.getRole().equals("user")) {
			return "redirect:/admin/user";
		} else if (userInfo != null && userInfo.getRole().equals("teacher")) {
			return "redirect:/admin/teacher";
		} else if(userInfo!=null&&userInfo.getRole().equals("student")) {
			return "redirect:/admin/student";
		} else {
		return "home";
		}
	}

	@RequestMapping(value = "/admin/user", method = RequestMethod.GET)
	public String adminUser(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);

		if (userInfo != null && userInfo.getRole().equals("user")) {
			return "admin.user";
		} else if (userInfo != null && userInfo.getRole().equals("teacher")) {
			return "redirect:/admin/teacher";
		}else if(userInfo!=null&&userInfo.getRole().equals("student")) {
			return "redirect:/admin/student";
		} else {
			return "home";
		}

	}

	@RequestMapping(value = "/admin/teacher", method = RequestMethod.GET)
	public String adminTeacher(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);

		if (userInfo != null && userInfo.getRole().equals("user")) {
			return "redirect:/admin/user";
		} else if (userInfo != null && userInfo.getRole().equals("teacher")) {
			Teacher teacher = teacherService.findOne(userInfo.getId());
			userInfo.setTeacher(teacher);
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			// set default home page to set resume page;
			return "redirect:/admin/teacher/resume?active=personal";
		}else {
			return "home";
		}

	}
	@RequestMapping(value = "/admin/student", method = RequestMethod.GET)
	public String adminStudent(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! the client locale is " + locale.toString());
		
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		
		if (userInfo != null && userInfo.getRole().equals("user")) {
			return "redirect:/admin/user";
		} else if (userInfo != null && userInfo.getRole().equals("student")) {
			Student student=studentService.findOne(userInfo.getId());
			userInfo.setStudent(student);
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			// set default home page to set resume page;
			return "redirect:/admin/student/resume?active=personal";
		}else {
			return "home";
		}
		
	}

	@RequestMapping(value = "/{selfUrl}")
	public String commonRegister(@PathVariable String selfUrl,
			HttpSession session) {
		User user = userService.findBySelfUrl(selfUrl);
		if (user != null) {
			Long id = user.getId();
			return "redirect:/user/" + id.toString();
		} else {
			return "404";
		}
	}
}
