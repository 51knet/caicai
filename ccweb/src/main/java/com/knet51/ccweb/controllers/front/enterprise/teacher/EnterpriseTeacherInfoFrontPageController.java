package com.knet51.ccweb.controllers.front.enterprise.teacher;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.EnterpriseTeacher;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseType;
import com.knet51.ccweb.jpa.entities.courses.TeacherCourse;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.services.AnnoPhotoService;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.CourseTypeService;
import com.knet51.ccweb.jpa.services.EnterpriseTeacherService;
import com.knet51.ccweb.jpa.services.FriendsRelateService;
import com.knet51.ccweb.jpa.services.TeacherCourseService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.achievement.TeacherHonorService;

@Controller
public class EnterpriseTeacherInfoFrontPageController {
	private static Logger logger = LoggerFactory
			.getLogger(EnterpriseTeacherInfoFrontPageController.class);

	@Autowired
	private EnterpriseTeacherService enterpriseTeacherService;
	@Autowired
	private TeacherCourseService courseService;
	@Autowired
	private FriendsRelateService friendsRelateService;
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private AnnoPhotoService annoPhotoService;
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseTypeService courseTypeService;
	@Autowired
	private TeacherHonorService honorService;

	
	/**
	 * show the enterprise teacher list
	 * 
	 * @param enterprise_id
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/enterprise/{enterprise_id}/teacher/list")
	public String showEnterpriseTeacher(
			@PathVariable Long enterprise_id,
			Model model,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
		User user = userService.findOne(enterprise_id);
		UserInfo userInfo = new UserInfo(user);

		List<TeacherCourse> courseList = courseService
				.getAllTeacherCourseByUseridAndPublish(enterprise_id,
						GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());

		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", enterprise_id);
		Page<EnterpriseTeacher> onePage = enterpriseTeacherService
				.findTeacherByEnterprise(pageNumber, pageSize, user);
		model.addAttribute("page", onePage);
		return "enterprise.teacher.list";
	}

	/**
	 * show enterprise teacher detail information
	 * 
	 * @param enterprise_id
	 * @param et_id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/enterprise/{enterprise_id}/teacher/view/{et_id}")
	public String showEnterpriseTeacherDetail(@PathVariable Long enterprise_id,
			@PathVariable Long et_id, Model model) {
		User user = userService.findOne(enterprise_id);
		UserInfo userInfo = new UserInfo(user);
		
		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);
		List<TeacherCourse> courseList = courseService
				.getAllTeacherCourseByUseridAndPublish(enterprise_id,
						GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());
		
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", enterprise_id);

		EnterpriseTeacher enterpriseTeacher = enterpriseTeacherService
				.findOneById(et_id);
		model.addAttribute("eTeacher", enterpriseTeacher);
		return "enterprise.teacher.view";
	}

	/**
	 * show the enterprise announcement list
	 * 
	 * @param enterprise_id
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/enterprise/{enterprise_id}/announcement/list")
	public String showEnterpriseAnnoList(
			@PathVariable Long enterprise_id,
			Model model,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
		User user = userService.findOne(enterprise_id);
		UserInfo userInfo = new UserInfo(user);

		List<TeacherCourse> courseList = courseService
				.getAllTeacherCourseByUseridAndPublish(enterprise_id,
						GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());

		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", enterprise_id);

		Page<Announcement> annoPage = announcementService.findAllAnnoByUser(
				pageNumber, pageSize, user);
		model.addAttribute("page", annoPage);
		List<Announcement> annoList = announcementService
				.findAllByUid(enterprise_id);
		model.addAttribute("annoCount", annoList.size());
		return "enterprise.announcement.list";
	}

	/**
	 * show enterprise announcement detail information
	 * 
	 * @param enterprise_id
	 * @param anno_id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/enterprise/{enterprise_id}/announcement/view/{anno_id}")
	public String showEnterpriseAnnoDetail(@PathVariable Long enterprise_id,
			@PathVariable Long anno_id, Model model) {
		User user = userService.findOne(enterprise_id);
		UserInfo userInfo = new UserInfo(user);

		List<TeacherCourse> courseList = courseService
				.getAllTeacherCourseByUseridAndPublish(enterprise_id,
						GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());

		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", enterprise_id);

		Announcement announcement = announcementService.findOneById(anno_id);
		model.addAttribute("announcement", announcement);

		return "enterprise.announcement.view";
	}

	@RequestMapping(value = "/enterprise/{enterprise_id}/resume")
	public String showEnterpriseResume(@PathVariable Long enterprise_id,
			Model model) {
		User user = userService.findOne(enterprise_id);
		UserInfo userInfo = new UserInfo(user);

		List<TeacherCourse> courseList = courseService
				.getAllTeacherCourseByUseridAndPublish(enterprise_id,
						GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());

		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", enterprise_id);

		List<TeacherHonor> honorList = honorService
				.getAllHonorById(enterprise_id);
		model.addAttribute("honorList", honorList);
		model.addAttribute("honorCount", honorList.size());
		return "enterprise.resume";
	}
}
