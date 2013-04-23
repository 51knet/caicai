package com.knet51.ccweb.controllers.admin.enterprise.teacher;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.admin.teacher.course.TeacherCourseInfoForm;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.AnnoPhoto;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.EnterpriseTeacher;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;
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
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;

@Controller
public class EnterpriseTeacherInfoPageController {
	private static Logger logger = 
			LoggerFactory.getLogger(EnterpriseTeacherInfoPageController.class);
	
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
	
	/*  admin page controller */
	
	/**
	 * show the enterprise's teacher list
	 * @param session
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/admin/eteacher/list")
	public String showAllTeachersInEnterprise(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		logger.info("======= into enterprise teacher controller");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Page<EnterpriseTeacher> page = enterpriseTeacherService.findTeacherByEnterprise(pageNumber, pageSize, userInfo.getUser());
		model.addAttribute("page", page);
		return "admin.enterprise.teacher.list";
	}
	/**
	 * just into the create enterprise teacher
	 * @return
	 */
	@RequestMapping(value="/admin/eteacher/new")
	public String createTeacherPage(){
		logger.info("======= into create enterprise teacher page controller");
		return "admin.enterprise.teacher.new";
	}
	
	/**
	 * into edit teacher page
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/admin/eteacher/edit/{id}")
	public String intoEditTeacherPage(@PathVariable Long id,Model model,HttpSession session){
		EnterpriseTeacher teacher = enterpriseTeacherService.findOneById(id);
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(!userInfo.getId().equals(teacher.getUser().getId()) && teacher!=null){
			return "redirect:/admin";
		}
		model.addAttribute("eTeacher", teacher);
		return "admin.enterprise.teacher.update";
	}
	
	/**
	 * validate the post form
	 * @param enterpriseTeacherInfoForm
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/admin/eteacher/teacherInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse courseFormAjaxJson(@Valid EnterpriseTeacherInfoForm enterpriseTeacherInfoForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
	
	
	/*   front page controller   */
	
	/**
	 * enterprise home page
	 * @param id
	 * @param model
	 * @param session
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/enterprise/{id}")
	public String teacherFront(@PathVariable Long id, Model model,
			HttpSession session, HttpServletResponse response)
			throws IOException {
		logger.info("#### Into enterprise front page ####");
		try {
			User user = userService.findOne(id);
			Teacher enterprise = teacherService.findOne(id);
			UserInfo sessionUserInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			boolean isFollower = false;
			if (sessionUserInfo!=null) { // this is only valid when user logged in and see teacher home page
				User sessionUser = sessionUserInfo.getUser();
				isFollower = friendsRelateService.isTheFollower(id, sessionUser.getId());
			}
			
			Page<Announcement> annoPage = announcementService.findAllAnnoByUser(0, 4, user);
			model.addAttribute("annolist", annoPage.getContent());
			List<Announcement> annoList = announcementService.findAllByUid(id);
			model.addAttribute("annoCount", annoList.size());
			List<AnnoPhoto> annoPhoto = annoPhotoService.findAnnoPhotoByUserid(user.getId());
			model.addAttribute("annoPhoto", annoPhoto);
			
//			Page<TeacherCourse> pageCourse = courseService
//					.findTeacherCourseByTeacherAndPublish(0, 6, enterprise, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
//			List<TeacherCourse> courseList = pageCourse.getContent();
			List<TeacherCourse> courseList = courseService.getAllTeacherCourseByTeacheridAndPublish(id, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
			model.addAttribute("courseList", courseList);
			model.addAttribute("courseCount", courseList.size());
			
			List<CourseType> cTypeList = courseTypeService.findAll();
			model.addAttribute("cTypeList", cTypeList);
			
			//Page<EnterpriseTeacher> eTeacher = enterpriseTeacherService.findTeacherByEnterprise(0, 6, user);
			List<EnterpriseTeacher> eTeacherList = enterpriseTeacherService.findTeacherByEnterprise(user); 
			model.addAttribute("eTeacher", eTeacherList);
			model.addAttribute("eTeacherCount", eTeacherList.size());

			UserInfo userInfo = new UserInfo(user);
			userInfo.setTeacher(enterprise);

			Integer fansCount = friendsRelateService.getAllFans(id).size();
			Integer hostCount = friendsRelateService.getAllHost(id).size();

			model.addAttribute("teacher_id", id);
			model.addAttribute("teacherInfo", userInfo);
		
			model.addAttribute("role", userInfo.getTeacherRole());		
			session.setAttribute("isFollower", isFollower);
			session.setAttribute("fansCount", fansCount);
			session.setAttribute("hostCount", hostCount);
			return "enterprise.basic";
		} catch (Exception e) {
			e.printStackTrace();
			return "404";
		}
	}
	/**
	 * show enterprise course list
	 * @param id
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/enterprise/{id}/course/list")
	public String getAllEnterpriseCourse(@PathVariable Long id,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		User user = userService.findOne(id);
		Teacher teacher = teacherService.findOne(id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", id);
		Page<TeacherCourse> onePage = courseService.findTeacherCourseByUserAndPublish(pageNumber, pageSize, user, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("page", onePage);
		
		List<TeacherCourse> courseList = courseService.getAllTeacherCourseByTeacheridAndPublish(id, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());
		
		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);
		
		return "enterprise.course.list";
	}
	/**
	 * filter the course by course type
	 * @param teacher_id
	 * @param type_id
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/enterprise/{enterprise_id}/course/type/{type_id}")
	public String filterCourseByType(@PathVariable Long enterprise_id, @PathVariable Long type_id,Model model,
			@RequestParam(value="pageNumber",defaultValue="0") int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		User user = userService.findOne(enterprise_id);
		Teacher teacher = teacherService.findOne(enterprise_id);
		CourseType cType = courseTypeService.findOneById(type_id);
		UserInfo userInfo = new UserInfo(user);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", enterprise_id);
		Page<TeacherCourse> onePage = courseService.findTeacherCourseByUserAndPublishAndCType(pageNumber, pageSize, user, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT,cType);
		model.addAttribute("page", onePage);
		
		List<TeacherCourse> courseList = courseService.getAllTeacherCourseByTeacheridAndPublish(enterprise_id, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());
		
		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);
		return "enterprise.course.list";
	}
	/**
	 * show the enterprise teacher list
	 * @param enterprise_id
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/enterprise/{enterprise_id}/teacher/list")
	public String showEnterpriseTeacher(@PathVariable Long enterprise_id,Model model,
			@RequestParam(value="pageNumber",defaultValue="0") int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		User user = userService.findOne(enterprise_id);
		UserInfo userInfo = new UserInfo(user);
		
		List<TeacherCourse> courseList = courseService.getAllTeacherCourseByTeacheridAndPublish(enterprise_id, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());
		
		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", enterprise_id);
		Page<EnterpriseTeacher> onePage = enterpriseTeacherService.findTeacherByEnterprise(pageNumber, pageSize, user);
		model.addAttribute("page", onePage);
		return "enterprise.teacher.list";
	}
	/**
	 * show enterprise teacher detail information
	 * @param enterprise_id
	 * @param et_id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/enterprise/{enterprise_id}/teacher/view/{et_id}")
	public String showEnterpriseTeacherDetail(@PathVariable Long enterprise_id,@PathVariable Long et_id,Model model){
		User user = userService.findOne(enterprise_id);
		UserInfo userInfo = new UserInfo(user);
		Integer courseCount = courseService.getAllTeacherCourseByTeacheridAndPublish(enterprise_id, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT).size();
		model.addAttribute("courseCount", courseCount);
		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", enterprise_id);
		
		EnterpriseTeacher enterpriseTeacher = enterpriseTeacherService.findOneById(et_id);
		model.addAttribute("eTeacher", enterpriseTeacher);
		return "enterprise.teacher.view";
	}
	/**
	 * show the enterprise announcement list
	 * @param enterprise_id
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/enterprise/{enterprise_id}/announcement/list")
	public String showEnterpriseAnnoList(@PathVariable Long enterprise_id,Model model,
			@RequestParam(value="pageNumber",defaultValue="0") int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		User user = userService.findOne(enterprise_id);
		UserInfo userInfo = new UserInfo(user);
		
		List<TeacherCourse> courseList = courseService.getAllTeacherCourseByTeacheridAndPublish(enterprise_id, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());
		
		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", enterprise_id);
		
		Page<Announcement> annoPage = announcementService.findAllAnnoByUser(pageNumber, pageSize, user);
		model.addAttribute("page", annoPage);
		List<Announcement> annoList = announcementService.findAllByUid(enterprise_id);
		model.addAttribute("annoCount", annoList.size());
		return "enterprise.announcement.list";
	}
	/**
	 * show enterprise announcement detail information
	 * @param enterprise_id
	 * @param anno_id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/enterprise/{enterprise_id}/announcement/view/{anno_id}")
	public String showEnterpriseAnnoDetail(@PathVariable Long enterprise_id,@PathVariable Long anno_id,Model model){
		User user = userService.findOne(enterprise_id);
		UserInfo userInfo = new UserInfo(user);
		
		List<TeacherCourse> courseList = courseService.getAllTeacherCourseByTeacheridAndPublish(enterprise_id, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
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
	
	@RequestMapping(value="/enterprise/{enterprise_id}/resume")
	public String showEnterpriseResume(@PathVariable Long enterprise_id,Model model){
		User user = userService.findOne(enterprise_id);
		UserInfo userInfo = new UserInfo(user);
		
		List<TeacherCourse> courseList = courseService.getAllTeacherCourseByTeacheridAndPublish(enterprise_id, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());
		
		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", enterprise_id);
		
		List<TeacherHonor> honorList = honorService.getAllHonorById(enterprise_id);
		model.addAttribute("honorList", honorList);
		model.addAttribute("honorCount", honorList.size());
		return "enterprise.resume";
	}
}
