package com.knet51.ccweb.controllers.front;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;

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

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.AnnoPhoto;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.Enterprise;
import com.knet51.ccweb.jpa.entities.EnterpriseTeacher;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;
import com.knet51.ccweb.jpa.entities.courses.CourseType;
import com.knet51.ccweb.jpa.entities.courses.Course;
import com.knet51.ccweb.jpa.entities.timeline.Trends;
import com.knet51.ccweb.jpa.services.AnnoPhotoService;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.AuthenticationService;
import com.knet51.ccweb.jpa.services.BlogService;
import com.knet51.ccweb.jpa.services.CommentService;
import com.knet51.ccweb.jpa.services.CourseTypeService;
import com.knet51.ccweb.jpa.services.EnterpriseService;
import com.knet51.ccweb.jpa.services.EnterpriseTeacherService;
import com.knet51.ccweb.jpa.services.FriendsRelateService;
import com.knet51.ccweb.jpa.services.ResourceService;
import com.knet51.ccweb.jpa.services.CourseService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.TrendsService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.achievement.TeacherHonorService;
import com.knet51.ccweb.jpa.services.achievement.TeacherPatentService;
import com.knet51.ccweb.jpa.services.achievement.TeacherProjectService;
import com.knet51.ccweb.jpa.services.achievement.TeacherThesisService;

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
	private EnterpriseService enterpirseService;
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
	private CourseService courseService;
	@Autowired
	private FriendsRelateService friendsRelateService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private EnterpriseTeacherService enterpriseTeacherService;
	@Autowired
	private CourseTypeService courseTypeService;
	@Autowired
	private AnnoPhotoService annoPhotoService;
	@Autowired
	private TrendsService trendsService;
	@Autowired
	private CommentService commentService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session,
			HttpServletRequest request) {
		UserInfo sessionUserInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);

		Cookie[] cookies = request.getCookies();
		String email = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(GlobalDefs.COOKIE_IDENTITY)) {
					String val = cookie.getValue(); // the value in cookie was
													// encoded
					email = new String(Base64.decode(val.getBytes()),
							Charset.forName("US-ASCII"));
					logger.debug("cookie encodedEmail:" + val
							+ ";decodedEmail:" + email);
					break;
				}
			}
			if (email != null && !email.equals("")) {
				User user = userService.findByEmailAddress(email); 
				sessionUserInfo = new UserInfo(user);
				session.setAttribute(GlobalDefs.SESSION_USER_INFO,
						sessionUserInfo);

				return "redirect:/admin";
			}
		}

		// we can achieve auto login through above code,
		// comment it out for now since I am not quite clear how we should
		// control the auto login

		if (sessionUserInfo != null) {
			return "redirect:/admin";
		} else {
			return "home";
		}
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
			HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		logger.info("#### Into teacher front page ####");
		try {
			User user = userService.findOne(id);
			if (user.getRole().equals("teacher")) {
				UserInfo sessionUserInfo = (UserInfo) session
						.getAttribute(GlobalDefs.SESSION_USER_INFO);
				boolean isFollower = false;
				if (sessionUserInfo != null) { // this is only valid when user
												// logged in and see teacher
												// home
												// page
					User sessionUser = sessionUserInfo.getUser();
					isFollower = friendsRelateService.isTheFollower(id,
							sessionUser.getId());
				}

				Teacher teacher = teacherService.findOne(id);
				Page<BlogPost> page = blogService
						.findAllBlogsNotGarbageAndNotDraft(0, 5, teacher);
				List<BlogPost> blogPosts = page.getContent();
				model.addAttribute("blogPosts", blogPosts);

				Page<Announcement> annoPage = announcementService
						.findAllAnnoByUser(0, 4, user);
				model.addAttribute("annolist", annoPage.getContent());
				model.addAttribute("annoCount", annoPage.getContent().size());

				Page<CourseResource> pageResource = resourceService
						.findAllResouByUserAndStatus(0, 5, user,
								GlobalDefs.STATUS_RESOURCE);
				List<CourseResource> resourceList = pageResource.getContent();
				Integer resourceCount = resourceService.listAllByUser(user)
						.size();
				model.addAttribute("resourceList", resourceList);
				model.addAttribute("resourceCount", resourceCount);

				Page<Course> pageCourse = courseService
						.findTeacherCourseByUserAndPublish(0, 5, user,
								GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
				List<Course> courseList = pageCourse.getContent();
				Integer courseCount = courseService
						.getAllTeacherCourseByUseridAndPublish(id,
								GlobalDefs.PUBLISH_NUM_ADMIN_FRONT).size();
				model.addAttribute("courseList", courseList);
				model.addAttribute("courseCount", courseCount);

				List<Announcement> annoList = announcementService
						.findAllByUid(id);
				model.addAttribute("annoCount", annoList.size());

				UserInfo userInfo = new UserInfo(user);
				userInfo.setTeacher(teacher);

				model.addAttribute("teacher_id", id);
				model.addAttribute("teacherInfo", userInfo);

				model.addAttribute("role", userInfo.getTeacherRole());
				
				Integer fansCount = friendsRelateService.getAllFans(id).size();
				Integer hostCount = friendsRelateService.getAllHost(id).size();
				session.setAttribute("isFollower", isFollower);
				session.setAttribute("fansCount", fansCount);
				session.setAttribute("hostCount", hostCount);
				return "teacher.basic";
			} else {
				return "redirect:/id/" + id;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "404";
		}
	}

	@RequestMapping(value = "/enterprise/{user_id}")
	public String enterpriseFront(@PathVariable Long user_id, Model model,
			HttpSession session, HttpServletResponse response)
			throws IOException {
		logger.info("#### Into enterprise front page ####");
		try {
			User user = userService.findOne(user_id);
			if (user.getRole().equals("enterprise")) {
				Enterprise enterprise = enterpirseService.findOne(user_id);
				UserInfo sessionUserInfo = (UserInfo) session
						.getAttribute(GlobalDefs.SESSION_USER_INFO);
				boolean isFollower = false;
				if (sessionUserInfo != null) { // this is only valid when user
												// logged in and see enterprise
												// home
												// page
					User sessionUser = sessionUserInfo.getUser();
					isFollower = friendsRelateService.isTheFollower(user_id,
							sessionUser.getId());
				}

				Page<Announcement> annoPage = announcementService
						.findAllAnnoByUser(0, 4, user);
				model.addAttribute("annolist", annoPage.getContent());
				List<Announcement> annoList = announcementService
						.findAllByUid(user_id);
				model.addAttribute("annoCount", annoList.size());
				List<AnnoPhoto> annoPhoto = annoPhotoService
						.findAnnoPhotoByUserid(user.getId());
				model.addAttribute("annoPhoto", annoPhoto);

				// Page<EnterpriseCourse> pageCourse = courseService
				// .findEnterpriseCourseByEnterpriseAndPublish(0, 6, enterprise,
				// GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
				// List<EnterpriseCourse> courseList = pageCourse.getContent();
				List<Course> courseList = courseService
						.getAllTeacherCourseByUseridAndPublish(user_id,
								GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
				model.addAttribute("courseList", courseList);
				model.addAttribute("courseCount", courseList.size());

				List<CourseType> cTypeList = courseTypeService.findAll();
				model.addAttribute("cTypeList", cTypeList);

				// Page<EnterpriseEnterprise> eEnterprise =
				// enterpriseEnterpriseService.findEnterpriseByEnterprise(0, 6,
				// user);
				List<EnterpriseTeacher> eTeacherList = enterpriseTeacherService
						.findTeacherByEnterprise(user);
				model.addAttribute("eTeacher", eTeacherList);
				model.addAttribute("eTeacherCount", eTeacherList.size());

				UserInfo userInfo = new UserInfo(user);
				userInfo.setEnterprise(enterprise);

				Integer fansCount = friendsRelateService.getAllFans(user_id)
						.size();
				Integer hostCount = friendsRelateService.getAllHost(user_id)
						.size();

				model.addAttribute("userInfo", userInfo);
				model.addAttribute("user_id", user_id);

				model.addAttribute("role", userInfo.getRole());
				session.setAttribute("isFollower", isFollower);
				session.setAttribute("fansCount", fansCount);
				session.setAttribute("hostCount", hostCount);
				return "enterprise.basic";
			} else {
				return "redirect:/id/" + user_id;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "404";
		}
	}
	
	@RequestMapping(value = "/user/{user_id}")
	public String userFront(@PathVariable Long user_id, Model model,
			HttpSession session, HttpServletResponse response)
			throws IOException {
		logger.info("#### Into enterprise front page ####");
		try {
			User user = userService.findOne(user_id);
			if (user.getRole().equals("user")) {
				UserInfo sessionUserInfo = (UserInfo) session
						.getAttribute(GlobalDefs.SESSION_USER_INFO);
				boolean isFollower = false;
				if (sessionUserInfo != null) { // this is only valid when user
												// logged in and see 
												// home
												// page
					User sessionUser = sessionUserInfo.getUser();
					isFollower = friendsRelateService.isTheFollower(user_id,
							sessionUser.getId());
				}
						
				UserInfo userInfo = new UserInfo(user);
				Integer fansCount = friendsRelateService.getAllFans(user_id)
						.size();
				Integer hostCount = friendsRelateService.getAllHost(user_id)
						.size();

				model.addAttribute("userInfo", userInfo);
				model.addAttribute("user_id", user_id);

				model.addAttribute("role", userInfo.getRole());
				session.setAttribute("isFollower", isFollower);
				session.setAttribute("fansCount", fansCount);
				session.setAttribute("hostCount", hostCount);
				List<Trends> myTrends = trendsService.showAllTrendsByUserId(user_id);
				model.addAttribute("myTrend", myTrends);
				
				return "user.basic";
			} else {
				return "redirect:/id/" + user_id;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "404";
		}
	}
}
