package com.knet51.ccweb.controllers.admin.user.courses;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.UserOrder;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.Course;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;
import com.knet51.ccweb.jpa.entities.courses.UserCourse;
import com.knet51.ccweb.jpa.services.CourseResourceService;
import com.knet51.ccweb.jpa.services.CourseService;
import com.knet51.ccweb.jpa.services.OrderService;
import com.knet51.ccweb.jpa.services.UserCourseService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.beans.UserCourseBeans;
import com.knet51.ccweb.controllers.admin.user.courses.UserCourseForm;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;

@Controller
public class UserCourseController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserCourseController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserCourseService userCourseService;
	@Autowired
	private CourseResourceService courseResourceService;
	@Autowired
	private OrderService orderService;

	/**
	 * show user course list
	 * 
	 * @param session
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/admin/mycourse/list")
	public String userCourses(
			HttpSession session,
			Model model,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<Course> userCourseList = new ArrayList<Course>();
		Page<UserCourse> mycourse = userCourseService.findByUserid(pageNumber,
				pageSize, userInfo.getId());
		for (int i = 0; i < mycourse.getContent().size(); i++) {
			Course course = courseService.findOneById(mycourse.getContent()
					.get(i).getTeachercourseid());
			userCourseList.add(course);
		}
		model.addAttribute("courseList", userCourseList);
		model.addAttribute("page", mycourse);
		return "admin." + userInfo.getUser().getRole() + ".mycourse.list";
	}

	/**
	 * show the course resource info from admin page
	 * 
	 * @param course_id
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/admin/mycourse/view/{course_id}")
	public String showUserCourseResource(@PathVariable Long course_id,
			Model model, HttpSession session) {

		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (userInfo == null) {
			return "redirect:/admin";
		}

		UserCourse userCourse = userCourseService
				.findByTeachercourseidAndUserid(course_id, userInfo.getId());
		if (userCourse == null) {
			return "redirect:/admin";
		}
		List<CourseResource> listResource = courseResourceService
				.getAllCourseResourceByCourseIdAndStatus(course_id,
						GlobalDefs.STATUS_COURSE_RESOURCE);
		List<CourseResource> courseList = new ArrayList<CourseResource>();
		Map<Integer, List<CourseResource>> courseMap = new TreeMap<Integer, List<CourseResource>>();
		int resourceOrder = 0;
		for (CourseResource courseResource : listResource) {
			resourceOrder = courseResource.getLessonNum();
			courseList = courseResourceService
					.getResourceByLessonNumAndCourseId(resourceOrder, course_id);
			courseMap.put(resourceOrder, courseList);
		}
		Course course = courseService.findOneById(course_id);
		model.addAttribute("courseMap", courseMap);
		model.addAttribute("course", course);
		model.addAttribute("resourceCount", listResource.size());
		return "course.study.view";
	}

	/**
	 * show course detail info page
	 * 
	 * @param course_id
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/admin/mycourse/courseinfo/{course_id}")
	public String showCourseInfo(@PathVariable Long course_id, Model model,
			HttpSession session) {
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (userInfo == null) {
			return "redirect:/admin";
		}
		UserCourse userCourse = userCourseService
				.findByTeachercourseidAndUserid(course_id, userInfo.getId());
		if (userCourse == null) {
			return "redirect:/admin";
		}
		Course course = courseService.findOneById(course_id);
		model.addAttribute("course", course);
		return "course.study.info.view";
	}

	/**
	 * show a course's comment list
	 * 
	 * @param course_id
	 * @param model
	 * @param session
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/admin/mycourse/comment/{course_id}")
	public String showCommentInfo(
			@PathVariable Long course_id,
			Model model,
			HttpSession session,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (userInfo == null) {
			return "redirect:/admin";
		}
		UserCourse vaildUserCourse = userCourseService
				.findByTeachercourseidAndUserid(course_id, userInfo.getId());
		if (vaildUserCourse == null) {
			return "redirect:/admin";
		}

		List<UserCourseBeans> list = new ArrayList<UserCourseBeans>();
		Page<UserCourse> onePage = userCourseService
				.findUserCourseByTeachercourseid(pageNumber, pageSize,
						course_id);
		UserCourseBeans UserCourseBeans;
		for (int i = 0; i < onePage.getContent().size(); i++) {
			UserCourseBeans = new UserCourseBeans();
			long userid = onePage.getContent().get(i).getUserid();
			User user = userCourseService.findByUserId(userid);
			UserCourse comm = onePage.getContent().get(i);
			String userName = user.getName();
			String photoUrl = user.getPhoto_url();
			UserCourseBeans.setUserCourse(comm);
			UserCourseBeans.setPhotoUrl(photoUrl);
			UserCourseBeans.setUserName(userName);
			list.add(UserCourseBeans);
		}
		Integer sumPerson = 0;
		List<UserCourse> userCourseList = userCourseService
				.findByTeachercourseid(course_id);
		double courseMark = 0.0;
		for (UserCourse userCourse : userCourseList) {
			if (userCourse.getMark() != null) {
				sumPerson = sumPerson + 1;
				courseMark = userCourseService.getMark(course_id);// 一个视频的评论平均分数
			}
		}
		Course course = courseService.findOneById(course_id);
		model.addAttribute("course", course);
		// model.addAttribute("listCount", listUserCourse.size());
		model.addAttribute("listUserCourse", list);
		// model.addAttribute("id", id);
		model.addAttribute("page", onePage);
		model.addAttribute("sumPerson", sumPerson);
		model.addAttribute("courseMark", courseMark);

		return "course.study.comment.view";
	}

	/**
	 * 增加课程评论内容
	 * 
	 * @param UserCourseInfoForm
	 * @param id
	 * @param model
	 * @param session
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/mycourse/comment/new", method = RequestMethod.POST)
	public String contactInfo(@RequestParam("courseid") Long course_id,
			@Valid UserCourseForm userCourseForm, BindingResult validResult,
			RedirectAttributes redirectAttr, HttpSession session) {
		logger.info("#### contactInfo InfoController ####");
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (userInfo == null) {
			return "redirect:/admin";
		}
		UserCourse vaildUserCourse = userCourseService
				.findByTeachercourseidAndUserid(course_id, userInfo.getId());
		if (vaildUserCourse == null) {
			return "redirect:/admin";
		}

		Long marks = userCourseForm.getMark();
		String userCourseDesc = userCourseForm.getCommentDesc().trim();
		// String message="";
		if (validResult.hasErrors()) {
			logger.info("contactInfo Validation Failed " + validResult);
			return "redirect:/admin/mycourse/comment/" + course_id;
		} else {
			logger.info("### contactInfo Validation passed. ###");
			// UserInfo userInfo = (UserInfo)
			// session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			UserCourse userCourse = userCourseService
					.findByTeachercourseidAndUserid(course_id, userInfo.getId());
			if (userCourse.getCommentDesc() != null
					&& userCourse.getMark() >= 0) {
				String message = "请不要重复评论";
				redirectAttr.addFlashAttribute("message", message);
				return "redirect:/admin/mycourse/comment/" + course_id;
			} else {
				userCourse.setCommentDesc(userCourseDesc);
				userCourse.setMark(marks);
				Date date = new Date();
				userCourse.setCommentDate(date);
				userCourseService.save(userCourse);
				return "redirect:/admin/mycourse/comment/" + course_id;
			}
		}
	}

	/**
	 * 验证评论框是否为空
	 * 
	 * @param UserCourseInfoForm
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/admin/mycourse/comment/commentajax", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResponse UserCourseInfoFormAjaxJson(
			@Valid UserCourseForm userCourseForm, BindingResult result) {
		logger.info("==== into commentajax controller ===");
		return AjaxValidationEngine.process(result);
	}

	/**
	 * show pay view
	 * 
	 * @param course_id
	 * @return
	 */
	@RequestMapping(value = "/course/pay/view/{course_id}")
	public String showPayPage(@PathVariable Long course_id, Model model,
			HttpSession session, HttpServletRequest request) {
		boolean paySuccessful = false;
		String password = "";
		String enterPassword = request.getParameter("password");
		model.addAttribute("courseId", course_id);
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user;
		if (userInfo != null) {
			user = userService.findByEmailAddress(userInfo.getEmail());
			password = user.getPassword();
			UserCourse userCourse = userCourseService
					.findByTeachercourseidAndUserid(course_id,
							userInfo.getId());
			
			if (userCourse == null) {
				if (password.equals(enterPassword)) {
					userCourse = new UserCourse();
					userCourse.setTeachercourseid(course_id);
					userCourse.setUserid(userInfo.getId());
					userCourseService.save(userCourse);
					UserOrder userOrder = new UserOrder(user, course_id.toString());
					userOrder.setStatus("完成");
					orderService.createOrder(userOrder);
					paySuccessful = true;
				}
			}else{
				paySuccessful = true;
			}
		}
		model.addAttribute("paySuccessful", paySuccessful);
		return "course.pay.view";
	}

	/**
	 * buy the course in enterprise front page
	 * 
	 * @param course_id
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/course/study/buy/{course_id}")
	public String BuyCourseDetail(@PathVariable Long course_id, Model model,
			HttpSession session) {
		Course course = courseService.findOneById(course_id);
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (userInfo == null) {
			return "redirect:/";
		}
		if (course.getPrice().intValue() > 0) {
			return "redirect:/course/pay/view/" + course_id;
		}
		UserCourse userCourse = userCourseService
				.findByTeachercourseidAndUserid(course_id, userInfo.getId());
		if (userCourse == null) {
			userCourse = new UserCourse();
			userCourse.setTeachercourseid(course_id);
			userCourse.setUserid(userInfo.getId());
			userCourseService.save(userCourse);
		}
		List<CourseResource> listResource = courseResourceService
				.getAllCourseResourceByCourseIdAndStatus(course_id,
						GlobalDefs.STATUS_COURSE_RESOURCE);
		List<CourseResource> courseList = new ArrayList<CourseResource>();
		Map<Integer, List<CourseResource>> courseMap = new TreeMap<Integer, List<CourseResource>>();
		int resourceOrder = 0;
		for (CourseResource courseResource : listResource) {
			resourceOrder = courseResource.getLessonNum();
			courseList = courseResourceService
					.getResourceByLessonNumAndCourseId(resourceOrder, course_id);
			courseMap.put(resourceOrder, courseList);
		}
		model.addAttribute("courseMap", courseMap);
		model.addAttribute("course", course);
		model.addAttribute("resourceCount", listResource.size());
		return "course.study.view";
	}

}
