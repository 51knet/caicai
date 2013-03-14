package com.knet51.courses.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.entities.teacher.UserCourse;
import com.knet51.courses.beans.UserCourseBeans;
import com.knet51.courses.jpa.services.ResourceService;
import com.knet51.courses.jpa.services.TeacherCourseService;
import com.knet51.courses.jpa.services.TeacherService;
import com.knet51.courses.jpa.services.UserCourseService;
import com.knet51.courses.util.ajax.AjaxValidationEngine;
import com.knet51.courses.util.ajax.ValidationResponse;
import com.knet51.courses.util.fileUpLoad.FileUtil;

@Controller
public class CourseController {
	@Autowired
	private TeacherCourseService courseService;
	@Autowired
	private ResourceService courseResourceService;
	@Autowired
	private UserCourseService userCourseService;
	@Autowired
	private TeacherService teacherService;

	private static final Logger logger = LoggerFactory
			.getLogger(CourseController.class);

	// @RequestMapping(value="/course/list")
	// public String showAllCourse(Model model){
	// // List<TeacherCourseBeans> tcBeanList =
	// courseService.getAllTeacherCourseBeans();
	// // model.addAttribute("courseList", tcBeanList);
	// List<TeacherCourse> courseList = courseService.findAllCourses();
	// List<String> courseTypeList = courseService.courseTypeList();
	// model.addAttribute("courseList", courseList);
	// model.addAttribute("courseCount", courseList.size());
	// model.addAttribute("courseTypeList", courseTypeList);
	// return "course.list";
	// }
	/**
	 * filter the course by course type
	 * @param courseType
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@RequestMapping(value = "/course/list/type")
	public String showCourseByType(@RequestParam("detail") String courseType,
			Model model) throws Exception {
		courseType = new String(courseType.getBytes("iso-8859-1"), "utf-8");
		List<TeacherCourse> courseList = courseService.findAllCourses();
		List<String> courseTypeList = courseService.courseTypeList();
		List<TeacherCourse> newCourseList = new ArrayList<TeacherCourse>();
		if (courseType.trim() != null && !courseType.trim().equals("全部课程")
				&& !courseType.trim().equals("all")) {
			for (TeacherCourse c : courseList) {
				if (courseType.equals(c.getCourseType())) {
					newCourseList.add(c);
				}
			}
			model.addAttribute("courseType", courseType);
			model.addAttribute("courseList", newCourseList);
			model.addAttribute("courseCount", newCourseList.size());
			model.addAttribute("courseTypeList", courseTypeList);

		} else {
			model.addAttribute("courseList", courseList);
			model.addAttribute("courseType", courseType);
			model.addAttribute("courseCount", courseList.size());
			model.addAttribute("courseTypeList", courseTypeList);
		}
		return "course.list";
	}

	@RequestMapping(value = "/course/view/{course_id}")
	public String showCourseDetail(
			@PathVariable Long course_id,
			Model model,
			HttpSession session,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
		// UserInfo userInfo = (UserInfo)
		// session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		/* zm */
		TeacherCourse course = courseService.findOneById(course_id);
		model.addAttribute("course", course);
		/* lbx */
		Teacher teacher = course.getTeacher();
		List<UserCourse> listUserCourse = userCourseService
				.findByTeachercourseid(course_id);
		double courseMark = 0.0;
		Integer sumPerson = 0;
		Integer studentPerson=0;

		if (listUserCourse.size() == 0) {
			courseMark = 0;
			sumPerson = 0;
		} else {
			studentPerson=listUserCourse.size();
			for (UserCourse userCourse : listUserCourse) {
				if (userCourse.getMark() != null) {
					sumPerson = sumPerson+1;
					courseMark = userCourseService.getMark(course_id);
				}
			}
			List<UserCourseBeans> list = new ArrayList<UserCourseBeans>();
			Page<UserCourse> onePage = userCourseService
					.findUserCourseByTeachercourseid(pageNumber, pageSize,
							course_id);
			UserCourseBeans UserCourseUser;
			for (int i = 0; i < onePage.getContent().size(); i++) {
				UserCourseUser = new UserCourseBeans();
				UserCourse comm = onePage.getContent().get(i);
				User user = null;
				if (comm.getUserid() != null) {
					long userid = comm.getUserid();
					user = userCourseService.findByUserId(userid);
					String photoUrl = user.getPhoto_url();
					String userName = user.getName();
					UserCourseUser.setPhotoUrl(photoUrl);
					UserCourseUser.setUserName(userName);
				}
				if (comm.getCommentDesc() != null) {
					UserCourseUser.setUserCourse(comm);
				}
				list.add(UserCourseUser);
			}
			model.addAttribute("page", onePage);
			model.addAttribute("listUserCourse", list);
		}
		model.addAttribute("sumPerson", sumPerson);//评论人员
		model.addAttribute("studentPerson", studentPerson);//学员
		model.addAttribute("courseMark", courseMark);
		
		model.addAttribute("teacher", teacher);

		List<CourseResource> listResource = courseResourceService
				.getResourceByCourseIdAndStatus(course_id,
						GlobalDefs.STATUS_COURSE_RESOURCE);
		List<CourseResource> courseList;
		Map<String, List<CourseResource>> courseMap = new TreeMap<String, List<CourseResource>>();
		String lessonNum = null;
		for (CourseResource courseResource : listResource) {
			lessonNum = courseResource.getLessonNum();
			courseList = new ArrayList<CourseResource>();
			courseList = courseResourceService
					.getResourceByLessonNumAndCourseId(lessonNum, course_id);
			courseMap.put(lessonNum, courseList);
		}

		// model.addAttribute("id", id);
		model.addAttribute("courseMap", courseMap);
		model.addAttribute("resourceCount", listResource.size());
		return "course.list.view";
	}

	/**
	 * 通过ID查询出一条课程详细资料 author:lbx 判断session是否为空
	 * 
	 * @param model
	 * @param session
	 * @param teacherCourse_id
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/course/study/view/{id}")
	public String listCourseByTeacherCourseId(Model model, HttpSession session,
			@PathVariable Long id) {

		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (userInfo == null) {
			return "redirect:/signin";
		}
		UserCourse userCourse = userCourseService
				.findByTeachercourseidAndUserid(id, userInfo.getId());
		if (userCourse==null) {
			userCourse = new UserCourse();
			userCourse.setTeachercourseid(id);
			userCourse.setUserid(userInfo.getId());
			userCourseService.save(userCourse);
		}
		List<CourseResource> listResource = courseResourceService
				.getResourceByCourseIdAndStatus(id,
						GlobalDefs.STATUS_COURSE_RESOURCE);
		List<CourseResource> courseList = new ArrayList<CourseResource>();
		Map<String, List<CourseResource>> courseMap = new TreeMap<String, List<CourseResource>>();
		String resourceOrder = null;
		for (CourseResource courseResource : listResource) {
			resourceOrder = courseResource.getLessonNum();
			courseList = courseResourceService
					.getResourceByLessonNumAndCourseId(resourceOrder, id);
			courseMap.put(resourceOrder, courseList);
		}
		model.addAttribute("courseMap", courseMap);
		/* zm */
		TeacherCourse teacherCourse = courseService.findOneById(id);
		model.addAttribute("course", teacherCourse);
		model.addAttribute("resourceCount", listResource.size());
		return "course.study.view";
	}

	/**
	 * 通过ID查询出一条课程资料
	 * 
	 * @param model
	 * @param session
	 * @param teacherCourse_id
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/course/study/courseinfo/{id}")
	public String coursesByTeacherCourseId(Model model, HttpSession session,
			@PathVariable Long id) {
		TeacherCourse teacherCourse = courseService.findOneById(id);
		model.addAttribute("course", teacherCourse);
		return "course.study.courseinfo";
	}

	/**
	 * 查询出相关的评论信息
	 * 
	 * @param validResult
	 * @param request
	 * @param session
	 * @param m
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/course/study/comment/{id}")
	public String listUserCourse(
			@PathVariable Long id,
			Model model,
			HttpSession session,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize)
			throws Exception {
		List<UserCourseBeans> list = new ArrayList<UserCourseBeans>();
		Page<UserCourse> onePage = userCourseService
				.findUserCourseByTeachercourseid(pageNumber, pageSize, id);
		UserCourseBeans UserCourseUser;
		for (int i = 0; i < onePage.getContent().size(); i++) {
			UserCourseUser = new UserCourseBeans();
			long userid = onePage.getContent().get(i).getUserid();
			User user = userCourseService.findByUserId(userid);
			UserCourse comm = onePage.getContent().get(i);
			String userName = user.getName();
			String photoUrl = user.getPhoto_url();
			UserCourseUser.setUserCourse(comm);
			UserCourseUser.setPhotoUrl(photoUrl);
			UserCourseUser.setUserName(userName);
			list.add(UserCourseUser);
		}
		Integer sumPerson = 0;
		List<UserCourse> userCourseList = userCourseService
				.findByTeachercourseid(id);
		double courseMark = 0.0;
		for (UserCourse userCourse : userCourseList) {
			if (userCourse.getMark() != null) {
				sumPerson=sumPerson+1;
				courseMark = userCourseService.getMark(id);// 一个视频的评论平均分数
			}
		}
		TeacherCourse teacherCourse = courseService.findOneById(id);
		model.addAttribute("course", teacherCourse);
		// model.addAttribute("listCount", listUserCourse.size());
		model.addAttribute("listUserCourse", list);
		// model.addAttribute("id", id);
		model.addAttribute("page", onePage);
		model.addAttribute("sumPerson", sumPerson);
		model.addAttribute("courseMark", courseMark);
		return "course.study.comment";

	}

	/**
	 * 增加评论内容
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
	@RequestMapping(value = "/course/study/comment/new", method = RequestMethod.POST)
	public String contactInfo(@RequestParam("teachercourseid") Long id,
			@Valid UserCourseForm userCourseForm, BindingResult validResult,
			RedirectAttributes redirectAttr, HttpSession session) {
		logger.info("#### contactInfo InfoController ####");
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (userInfo == null) {
			return "redirect:/signin";
		}
		Long marks = userCourseForm.getMark();
		String userCourseDesc = userCourseForm.getCommentDesc().trim();
		// String message="";
		if (validResult.hasErrors()) {
			logger.info("contactInfo Validation Failed " + validResult);
			return "redirect:/course/study/comment/" + id;
		} else {
			logger.info("### contactInfo Validation passed. ###");
			// UserInfo userInfo = (UserInfo)
			// session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			UserCourse userCourse = userCourseService
					.findByTeachercourseidAndUserid(id, userInfo.getId());
			if (userCourse.getCommentDesc() != null
					&& userCourse.getMark() >= 0) {
				String message = "请不要重复评论";
				redirectAttr.addFlashAttribute("message", message);
				return "redirect:/course/study/comment/" + id;
			} else {
				userCourse.setCommentDesc(userCourseDesc);
				userCourse.setMark(marks);
				Date date = new Date();
				userCourse.setCommentDate(date);
				userCourseService.save(userCourse);
				return "redirect:/course/study/comment/" + id;
			}
		}
	}

	/**
	 * 下载视频
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/course/study/view/resource/{id}")
	public String resourceDownLoad(@PathVariable Long id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CourseResource courseResource = courseResourceService.findById(id);
		String savePath = courseResource.getSavePath();
		String fileName = courseResource.getSaveName();
		FileUtil.downLoad(request, response, savePath, fileName);
		return null;
	}

	/**
	 * 验证输入框是否为空
	 * 
	 * @param UserCourseInfoForm
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/course/study/comment/commentajax", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResponse UserCourseInfoFormAjaxJson(
			@Valid UserCourseForm userCourseForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	

	/**search the teacher or courses
	 * @author zm
	 * @param searchParam
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchCourseOrTeacher(
			@RequestParam("searchParam") String searchParam, Model model)
			throws Exception {
		List<TeacherCourse> courseList = courseService.findAllCourses();
		List<String> courseTypeList = courseService.courseTypeList();
		List<TeacherCourse> newCourseList = new ArrayList<TeacherCourse>();
		String param = searchParam.trim();
		if (param != null && !param.equals("")) {
			for(int i=0;i<courseList.size();i++){
				if(courseList.get(i).getCourseName().contains(param) || 
						courseList.get(i).getTeacher().getUser().getName().contains(param)){
					newCourseList.add(courseList.get(i));
				}
			}
			if(newCourseList.size()>0){
				model.addAttribute("courseList", newCourseList);
				model.addAttribute("courseCount", newCourseList.size());
			}else{
				model.addAttribute("courseList", courseList);
				model.addAttribute("courseCount", courseList.size());
			}
			model.addAttribute("searchParam", param);
			model.addAttribute("courseTypeList", courseTypeList);

		} else {
			model.addAttribute("courseList", courseList);
			model.addAttribute("courseCount", courseList.size());
			model.addAttribute("courseTypeList", courseTypeList);
			model.addAttribute("searchParam", param);
		}
		return "course.list";
	}
	
	@RequestMapping(value="/mycourses")
	public String showUserCourse(HttpSession session,Model model){
		UserInfo currentUser = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(currentUser != null){
			List<UserCourse> userCourseList = userCourseService.findUserCourseByUserid(currentUser.getId());
			List<TeacherCourse> userCourse = new ArrayList<TeacherCourse>();
			for (int i = 0; i < userCourseList.size(); i++) {
				TeacherCourse teacherCourse = courseService.findOneById(userCourseList.get(i).getTeachercourseid());
				userCourse.add(teacherCourse);
			}
			model.addAttribute("userInfo", currentUser.getUser());
			model.addAttribute("userCourse", userCourse);
			model.addAttribute("userCourseCount", userCourse.size());
		}
		return "user.courses.list";
	}
}
