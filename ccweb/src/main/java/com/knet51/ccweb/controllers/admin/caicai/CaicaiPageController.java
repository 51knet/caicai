package com.knet51.ccweb.controllers.admin.caicai;

import java.util.List;

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
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.AuthenResource;
import com.knet51.ccweb.jpa.entities.Authentication;
import com.knet51.ccweb.jpa.entities.Recharge;
import com.knet51.ccweb.jpa.entities.RechargeHistory;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;
import com.knet51.ccweb.jpa.entities.courses.TeacherCourse;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.AuthenResourceService;
import com.knet51.ccweb.jpa.services.AuthenticationService;
import com.knet51.ccweb.jpa.services.BlogService;
import com.knet51.ccweb.jpa.services.CourseResourceService;
import com.knet51.ccweb.jpa.services.RechargeHistoryService;
import com.knet51.ccweb.jpa.services.RechargeService;
import com.knet51.ccweb.jpa.services.ResourceService;
import com.knet51.ccweb.jpa.services.TeacherCourseService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;


@Controller
public class CaiCaiPageController {
	private static final Logger logger = LoggerFactory
			.getLogger(CaiCaiPageController.class);
	
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherCourseService courseService;
	@Autowired
	private CourseResourceService courseResourceService;
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private AuthenResourceService authenResourceService;
	@Autowired
	private RechargeService rechargeService;
	
	@Autowired
	private RechargeHistoryService rechargeHistoryService;
	
	
	
	@RequestMapping(value="/admin/caicai")
	public String showSuperAdmin(){
		
		return "redirect:/admin/caicai/authentication";
	}
	
	/*   authentication page and ajax controller  */
	
	/**
	 * show all the authentication
	 * @param model
	 * @param session
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/authentication")
	public String showAllAuthentication(Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize) {
		logger.info("====== into caicai page controller =====");
		Page<Authentication> page = authenticationService.findAll(pageNumber, pageSize);
		model.addAttribute("page", page);
		return "admin.caicai.authentication";
	}
	
	/**
	 * show add refuse reason page for authentication
	 * @param auth_id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/authentication/view/{auth_id}")
	public String showRefuseAuthenReasonPage(@PathVariable Long auth_id,Model model){
		Authentication authentication = authenticationService.findOneById(auth_id);
		List<AuthenResource> aResourceList = authenResourceService.findAllByAuthen(authentication);
		model.addAttribute("authentication", authentication);
		model.addAttribute("aResourceList", aResourceList);
		return "admin.caicai.authentication.detail";
	}
	/**
	 * authentication refuse reason's ajax method
	 * @param refuseForm
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/admin/caicai/authentication/refuseAjax", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse authenticationRefuseInfoFormAjaxJson(@Valid AuthenticationRefuseForm refuseForm, BindingResult result) {
		logger.info("==== into refuse ajax controller ");
		return AjaxValidationEngine.process(result);
	}
	
	/*  operate user page controller   */
	
	/**
	 * show user by role
	 * @param role
	 * @param model
	 * @param session
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/detail/{role}")
	public String showAllUser(@PathVariable String role,
			Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize) {
		logger.info("====== into caicai role controller ====="+role);
		UserInfo userInfo;
		userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		model.addAttribute("userInfoModel", userInfo);
		if (role == null || role.equals("")) {
			role = "user";
		}
		model.addAttribute("active", role);
		Page<User> page = userService.findUserByRole(role, pageNumber, pageSize);
		model.addAttribute("page", page);
		return "admin.caicai.detail";
	}
	
	/*  operate announcement page controller  */
	
	@RequestMapping(value="/admin/caicai/announcement/list")
	public String showAllAnnouncement(Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize) {
		logger.info("====== into caicai announcement controller =====");
		Page<Announcement> page = announcementService.findAllAnnoForSuperAdmin(pageNumber, pageSize);
		model.addAttribute("page", page);
		return "admin.caicai.announcement.list";
	}
	
	/*  operate announcement page controller  */
	
	@RequestMapping(value="/admin/caicai/course/list")
	public String showAllCourse(Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize) {
		logger.info("====== into caicai announcement controller =====");
		Page<TeacherCourse> page = courseService.findCourseByPublishGreaterThanForSuperAdmin(GlobalDefs.PUBLISH_NUM_DELETE, pageNumber, pageSize);
		model.addAttribute("page", page);
		return "admin.caicai.course.list";
	}
	
	/*  operate blog page controller  */
	
	@RequestMapping(value="/admin/caicai/blog/list")
	public String showAllBlog(Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize) {
		logger.info("====== into caicai blog controller =====");
		Page<BlogPost> page = blogService.findAllBlogsNotGarbageAndNotDraftForSuperAdmin(pageNumber, pageSize);
		model.addAttribute("page", page);
		return "admin.caicai.blog.list";
	}
	
	/*  operate resource page controller  */
	
	@RequestMapping(value="/admin/caicai/resource/list")
	public String showAllResource(Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize) {
		logger.info("====== into caicai resource controller =====");
		Page<CourseResource> page = resourceService.findAllByStatusForSuperAdmin(pageNumber, pageSize);
		model.addAttribute("page", page);
		return "admin.caicai.resource.list";
	}
	
	/*  operate recharge card  page controller  */
	
	@RequestMapping(value="/admin/caicai/recharge/list")
	public String showAllRechargecard(Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		logger.info("====== into caicai recharge list controller =====");
		Page<Recharge> page = rechargeService.findAll(pageNumber, pageSize);
		model.addAttribute("page", page);
		return "admin.caicai.recharge.list";
	}
	
	@RequestMapping(value="/admin/caicai/recharge/create")
	public String createNewRechargecard(){
		logger.info("====== into caicai create recharge page controller =====");
		
		return "admin.caicai.recharge.create";
	}
	
	@RequestMapping(value = "/admin/caicai/recharge/createAjax")
	public @ResponseBody ValidationResponse rechargeCardFormAjaxJson(@Valid RechargeCardForm rechargeCardForm, BindingResult result) {
		logger.info("==== into recharge card ajax controller ====="+result);
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value="/admin/caicai/recharge/history/list")
	public String showAllRechargeHistory(Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		logger.info("====== into show all recharge history page controller =====");
		Page<RechargeHistory> page = rechargeHistoryService.findAll(pageNumber, pageSize);
		model.addAttribute("page", page);
		return "admin.caicai.recharge.history.list";
	}
	
}
