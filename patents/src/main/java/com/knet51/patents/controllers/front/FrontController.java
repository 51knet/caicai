package com.knet51.patents.controllers.front;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
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


import com.knet51.ccweb.jpa.entities.AnnoPhoto;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.Comment;
import com.knet51.ccweb.jpa.entities.Enterprise;
import com.knet51.ccweb.jpa.entities.EnterpriseTeacher;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;
import com.knet51.ccweb.jpa.entities.courses.CourseType;
import com.knet51.ccweb.jpa.entities.courses.Course;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.entities.timeline.Trends;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.TeacherService;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.announcement.AnnouncementService;
import com.knet51.patents.jpa.services.blog.BlogService;
import com.knet51.patents.jpa.services.course.CourseService;
import com.knet51.patents.jpa.services.patent.PatentService;
import com.knet51.patents.jpa.services.resource.ResourceService;


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
	private ResourceService resourceService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private PatentService patentService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private AnnouncementService announcementService;



	@RequestMapping(value="/courses")
	public void jumpToCourses(ServletResponse response) throws ServletException, IOException{
		HttpServletResponse resp = ((HttpServletResponse)response);
		resp.sendRedirect("/courses");
		return;
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
			} else if (role.equals("enterprise")) {
				return "redirect:/enterprise/" + id;
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
				UserInfo sessionUserInfo = (UserInfo) session
						.getAttribute(GlobalDefs.SESSION_USER_INFO);
		
				Teacher teacher = teacherService.findOne(id);
				Page<BlogPost> page = blogService
						.findAllBlogsNotGarbageAndNotDraft(0, 5, teacher);
				List<BlogPost> blogPosts = page.getContent();
				model.addAttribute("blogPosts", blogPosts);

				Page<Announcement> annoPage = announcementService
						.findAllAnnoByUser(0, 4, user);
				model.addAttribute("annolist", annoPage.getContent());
				List<Announcement> annoList = announcementService
						.findAllByUid(id);
				model.addAttribute("annoCount", annoList.size());

				Page<CourseResource> pageResource = resourceService
						.findAllResouByUserAndStatus(0, 5, user,
								GlobalDefs.STATUS_RESOURCE);
				List<CourseResource> resourceList = pageResource.getContent();
				Integer resourceCount = resourceService.listAllByUser(user)
						.size();
				model.addAttribute("resourceList", resourceList);
				model.addAttribute("resourceCount", resourceCount);
				
				Page<Patent> pagePatent = patentService.findPatentByUserAndStatus(0, 5, user, GlobalDefs.PASS);
				List<Patent> patentList = pagePatent.getContent();
				model.addAttribute("patentList", patentList);

				

				UserInfo userInfo = new UserInfo(user);
				userInfo.setTeacher(teacher);

				model.addAttribute("teacher_id", id);
				model.addAttribute("teacherInfo", userInfo);

				model.addAttribute("role", userInfo.getTeacherRole());
				
				return "teacher.basic";
			} else {
				return "redirect:/id/" + id;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/user/{user_id}")
	public String userFront(@PathVariable Long user_id, Model model,
			HttpSession session, HttpServletResponse response)throws IOException {
			logger.info("#### Into user front page ####");
			User user = userService.findOne(user_id);
			if (user.getRole().equals("user")) {
				UserInfo sessionUserInfo = (UserInfo) session
						.getAttribute(GlobalDefs.SESSION_USER_INFO);
				boolean isFollower = false;										
				UserInfo userInfo = new UserInfo(user);
				model.addAttribute("userInfo", userInfo);
				model.addAttribute("user_id", user_id);

				model.addAttribute("role", userInfo.getRole());
				session.setAttribute("isFollower", isFollower);
							
				return "user.basic";
			} else {
				return "redirect:/id/" + user_id;
			}	
	}
}
