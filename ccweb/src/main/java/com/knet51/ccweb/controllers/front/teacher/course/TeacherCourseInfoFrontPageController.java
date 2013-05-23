package com.knet51.ccweb.controllers.front.teacher.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;
import com.knet51.ccweb.jpa.entities.courses.CourseType;
import com.knet51.ccweb.jpa.entities.courses.TeacherCourse;
import com.knet51.ccweb.jpa.services.CourseLessonService;
import com.knet51.ccweb.jpa.services.CourseResourceService;
import com.knet51.ccweb.jpa.services.CourseTypeService;
import com.knet51.ccweb.jpa.services.CourseService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserCourseService;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class TeacherCourseInfoFrontPageController {
	private static final Logger logger = LoggerFactory
			.getLogger(TeacherCourseInfoFrontPageController.class);
	public static final long MAX_FILE_SIZE_2M = 2 * 1024 * 1024;
	@Autowired
	private CourseService teacherCourseService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseResourceService courseResourceService;
	@Autowired
	private UserService userService;
	@Autowired
	private CourseLessonService courseLessonService;
	@Autowired
	private UserCourseService userCourseService;
	@Autowired
	private CourseTypeService courseTypeService;

	@RequestMapping(value = "/teacher/{teacher_id}/course/list")
	public String getAllTeacherCourse(
			@PathVariable Long teacher_id,
			Model model,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<TeacherCourse> onePage = teacherCourseService
				.findTeacherCourseByUserAndPublish(pageNumber, pageSize, user,
						GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("page", onePage);
		return "teacher.course.list";
	}

	@RequestMapping(value = "/course/view", method = RequestMethod.POST)
	public String detailCourse(@RequestParam("teacherId") Long teacher_id,
			@RequestParam("courseId") Long course_id,
			@RequestParam("coursepwd") String pwd, Model model) {
		TeacherCourse course = teacherCourseService.findOneById(course_id);
		if (pwd.equals(course.getPwd()) || course.getPwd() == null
				|| course.getPwd().trim().equals("")) {
			Teacher teacher = teacherService.findOne(teacher_id);
			User user = teacher.getUser();
			UserInfo userInfo = new UserInfo(user);
			userInfo.setTeacher(teacher);
			logger.debug(userInfo.toString());
			model.addAttribute("teacherInfo", userInfo);
			model.addAttribute("teacher_id", teacher_id);
			model.addAttribute("course", course);
			List<CourseResource> listResource = courseResourceService
					.getAllCourseResourceByCourseIdAndStatus(course_id,
							GlobalDefs.STATUS_COURSE_RESOURCE);
			List<CourseResource> resourceList;
			Map<String, List<CourseResource>> courseMap = new TreeMap<String, List<CourseResource>>();
			String resourceOrder = null;
			for (CourseResource courseResource : listResource) {
				resourceOrder = courseResource.getLessonNum();
				resourceList = new ArrayList<CourseResource>();
				resourceList = courseResourceService
						.getResourceByLessonNumAndCourseId(resourceOrder,
								course_id);
				courseMap.put(resourceOrder, resourceList);
			}
			List<CourseType> cTypeList = courseTypeService.findAll();
			logger.info("===============" + cTypeList.size());
			model.addAttribute("cTypeList", cTypeList);
			
			List<TeacherCourse> courseList = teacherCourseService
					.getAllTeacherCourseByUseridAndPublish(teacher_id,
							GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
			model.addAttribute("courseList", courseList);
			model.addAttribute("courseCount", courseList.size());
			
			model.addAttribute("resourceCount", listResource.size());
			model.addAttribute("courseMap", courseMap);
			if (user.getRole().equals("teacher")) {
				return "teacher.course.view";
			} else {
				return "enterprise.course.view";
			}

		}
		return "redirect:/";
	}
}
