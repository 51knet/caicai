package com.knet51.ccweb.controllers.enterprise;

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
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.controllers.teacher.course.TeacherCourseInfoForm;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.EnterpriseTeacher;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.CourseType;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.CourseTypeService;
import com.knet51.ccweb.jpa.services.EnterpriseTeacherService;
import com.knet51.ccweb.jpa.services.FriendsRelateService;
import com.knet51.ccweb.jpa.services.TeacherCourseService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
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
	private UserService userService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseTypeService courseTypeService;
	
	/*  admin page controller */
	
	/**
	 * show the enterprise's teacher list
	 * @param session
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/admin/enterprise/teacher/list")
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
	@RequestMapping(value="/admin/enterprise/teacher/new")
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
	@RequestMapping(value="/admin/enterprise/teacher/edit/{id}")
	public String intoEditTeacherPage(@PathVariable Long id,Model model,HttpSession session){
		EnterpriseTeacher teacher = enterpriseTeacherService.findOneById(id);
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(!userInfo.getId().equals(teacher.getUser().getId()) && teacher!=null){
			return "redirect:/admin/enterprise/teacher/list";
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
	@RequestMapping(value = "/admin/enterprise/teacher/teacherInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse courseFormAjaxJson(@Valid EnterpriseTeacherInfoForm enterpriseTeacherInfoForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
	
	
	/*   front page controller   */
	
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
			model.addAttribute("annoCount", annoPage.getContent().size());
			List<Announcement> annoList = announcementService.findAllByUid(id);
			model.addAttribute("annoCount", annoList.size());

			Page<TeacherCourse> pageCourse = courseService
					.findTeacherCourseByTeacherAndPublish(0, 5, enterprise, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
			List<TeacherCourse> courseList = pageCourse.getContent();
			Integer courseCount = courseService.getAllTeacherCourseByTeacheridAndPublish(id, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT).size();
			model.addAttribute("courseList", courseList);
			model.addAttribute("courseCount", courseCount);
			
			List<CourseType> cTypeList = courseTypeService.findAll();
			model.addAttribute("cTypeList", cTypeList);
			
			Page<EnterpriseTeacher> eTeacher = enterpriseTeacherService.findTeacherByEnterprise(0, 3, user);
			List<EnterpriseTeacher> eTeacherList = enterpriseTeacherService.findTeacherByEnterprise(user); 
			model.addAttribute("eTeacher", eTeacher.getContent());
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
}
