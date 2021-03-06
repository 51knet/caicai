package com.knet51.ccweb.controllers.admin.caicai;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Activity;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.Authentication;
import com.knet51.ccweb.jpa.entities.Recharge;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;
import com.knet51.ccweb.jpa.entities.courses.Course;
import com.knet51.ccweb.jpa.services.BlogService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.activity.ActivityService;
import com.knet51.ccweb.jpa.services.announcement.AnnouncementService;
import com.knet51.ccweb.jpa.services.authentication.AuthenticationService;
import com.knet51.ccweb.jpa.services.course.CourseResourceService;
import com.knet51.ccweb.jpa.services.course.CourseService;
import com.knet51.ccweb.jpa.services.resources.ResourceService;
import com.knet51.ccweb.jpa.services.trade.RechargeService;
@Controller
public class CaiCaiDetailController {
	private static final Logger logger = LoggerFactory
			.getLogger(CaiCaiDetailController.class);
	
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
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
	private RechargeService rechargeService;
	@Autowired
	private ActivityService activityService;
	
	/*   operate user controller  detail controller */
	
	/**
	 * free user by id and all the things he has published will show
	 * @param user_id
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/detail/{user_id}/free")
	public String freeUser(@PathVariable Long user_id){
		logger.info("====== into free user controller =====");
		User user = userService.findOne(user_id);
		Teacher teacher = teacherService.findOne(user_id);
		try {
			List<BlogPost> blogList = blogService.findAllBlogsNotGarbageAndNotDraft(teacher);
			if(blogList.size()>0){
				for (int i = 0; i <blogList.size(); i++) {
					blogList.get(i).setForbidden(null);
					blogService.updateBlogPost(blogList.get(i));
				}
			}
			List<Announcement> annoList = announcementService.findAllByUid(user_id);
			if(annoList.size()>0){
				for (int i = 0; i <annoList.size(); i++) {
					annoList.get(i).setForbidden(null);
					announcementService.updateAnnouncement(annoList.get(i));
				}
			}
			List<Course> courseList = courseService.findCourseByUserAndPublishGreaterThanForSuperAdmin(user, GlobalDefs.PUBLISH_NUM_DELETE);
			if(courseList.size()>0){
				for (int i = 0; i <courseList.size(); i++) {
					courseList.get(i).setForbidden(null);
					courseService.updateTeacherCourse(courseList.get(i));
				}
			}
			List<CourseResource> resourceList = resourceService.findAllByUserAndStatusForSuperAdmin(user, GlobalDefs.STATUS_RESOURCE);
			if(resourceList.size()>0){
				for (int i = 0; i <resourceList.size(); i++) {
					resourceList.get(i).setForbidden(null);
					resourceService.updateResource(resourceList.get(i));
				}
			}
			user.setForbidden(null);
			userService.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/caicai/detail/"+user.getRole();
	}
	
	/**
	 * forbid user by id and all the things he has published are forbidden
	 * @param user_id
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/detail/{user_id}/forbid")
	public String forbidUser(@PathVariable Long user_id){
		logger.info("====== into forbid user controller =====");
		User user = userService.findOne(user_id);
		Teacher teacher = teacherService.findOne(user_id);
		try {
			List<BlogPost> blogList = blogService.findAllBlogsNotGarbageAndNotDraft(teacher);
			if(blogList.size()>0){
				for (int i = 0; i <blogList.size(); i++) {
					blogList.get(i).setForbidden("yes");
					blogService.updateBlogPost(blogList.get(i));
				}
			}
			List<Announcement> annoList = announcementService.findAllByUid(user_id);
			if(annoList.size()>0){
				for (int i = 0; i <annoList.size(); i++) {
					annoList.get(i).setForbidden("yes");
					announcementService.updateAnnouncement(annoList.get(i));
				}
			}
			List<Course> courseList = courseService.findCourseByUserAndPublishGreaterThanForSuperAdmin(user, GlobalDefs.PUBLISH_NUM_DELETE);
			if(courseList.size()>0){
				for (int i = 0; i <courseList.size(); i++) {
					courseList.get(i).setForbidden("yes");
					courseService.updateTeacherCourse(courseList.get(i));
				}
			}
			List<CourseResource> resourceList = resourceService.findAllByUserAndStatusForSuperAdmin(user, GlobalDefs.STATUS_RESOURCE);
			if(resourceList.size()>0){
				for (int i = 0; i <resourceList.size(); i++) {
					resourceList.get(i).setForbidden("yes");
					resourceService.updateResource(resourceList.get(i));
				}
			}
			user.setForbidden("yes");
			userService.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/caicai/detail/"+user.getRole();
	}
	
	/**
	 * search user by email
	 * @param searchParam
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/user/search", method = RequestMethod.POST)
	public String searchUserByEmail(@RequestParam("searchParam") String searchParam,Model model){
		String email = searchParam.trim();
		List<User> userList = userService.findAllUsers();
		List<User> newUserList = new ArrayList<User>();
		if(email != null && !email.equals("")){
			for (int i = 0; i <userList.size() ; i++) {
				if(userList.get(i).getEmail().contains(email)){
					newUserList.add(userList.get(i));
				}
			}
			model.addAttribute("userList", newUserList);
			model.addAttribute("userListCount",newUserList.size());
			return "admin.caicai.detail";
		}else{
			return "redirect:/admin/caicai/detail/user";
		}
	}
	
	
	/* operate the authentication detail controller */
	
	/**
	 * pass the authentication by auth_id
	 * @param auth_id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/authentication/pass/{auth_id}")
	public String passAuthentication(@PathVariable Long auth_id,Model model ){
		logger.info("====== into pass authentication controller   =====");
		Authentication authentication = authenticationService.findOneById(auth_id);
		authentication.setStatus("pass");
		authenticationService.updateAuthentication(authentication);
		return "redirect:/admin/caicai/";
	}
	
	/**
	 * add the refused reason for enterprise's authentication
	 * @param auth_id
	 * @param model
	 * @param refuseForm
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/authentication/view/refuse" ,method = RequestMethod.POST)
	public String refuseAuthenticationReason(@RequestParam("auth_id") Long auth_id, Model model
			,@Valid AuthenticationRefuseForm refuseForm,BindingResult validResult ){
		logger.info("====== into pass authentication controller   =====");
		if(validResult.hasErrors()){
			logger.info("refuseForm Validation Failed " + validResult);
			return "redirect:/admin/caicai/authentication/view/"+auth_id;
		}else{
			Authentication authentication = authenticationService.findOneById(auth_id);
			authentication.setReason(refuseForm.getReason());
			authentication.setStatus("refuse");
			authenticationService.updateAuthentication(authentication);
			return "redirect:/admin/caicai/";
		}
	}
	
	
	/* oprate announcement detail controller   */
	
	/**
	 * free a announcement
	 * @param anno_id
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/announcement/{anno_id}/free")
	public String freeAnnouncement(@PathVariable Long anno_id){
		Announcement announcement = announcementService.findOneById(anno_id);
		announcement.setForbidden(null);
		announcementService.updateAnnouncement(announcement);
		return "redirect:/admin/caicai/announcement/list";
	}
	/**
	 * forbid a announcement
	 * @param anno_id
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/announcement/{anno_id}/forbid")
	public String forbidAnnouncement(@PathVariable Long anno_id){
		Announcement announcement = announcementService.findOneById(anno_id);
		announcement.setForbidden("yes");
		announcementService.updateAnnouncement(announcement);
		return "redirect:/admin/caicai/announcement/list";
	}
	/**
	 * search a announcement by searchparam
	 * @param searchParam
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/announcement/search", method = RequestMethod.POST)
	public String searchAnnoByTitle(@RequestParam("searchParam") String searchParam,Model model){
		String title = searchParam.trim();
		List<Announcement> annoList = announcementService.findAllForSuperAdmin();
		List<Announcement> newAnnoList = new ArrayList<Announcement>();
		if(title != null && !title.equals("")){
			for (int i = 0; i <annoList.size() ; i++) {
				if(annoList.get(i).getTitle().contains(title)){
					newAnnoList.add(annoList.get(i));
				}
			}
			model.addAttribute("annoList", newAnnoList);
			model.addAttribute("annoListCount",newAnnoList.size());
			model.addAttribute("searchParam", title);
			return "admin.caicai.announcement.list";
		}else{
			return "redirect:/admin/caicai/announcement/list";
		}
	}
	
	/* oprate announcement detail controller   */
	
	/**
	 * free a announcement
	 * @param anno_id
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/course/{course_id}/free")
	public String freeCourse(@PathVariable Long course_id){
		Course course = courseService.findOneById(course_id);
		course.setForbidden(null);
		courseService.updateTeacherCourse(course);
		return "redirect:/admin/caicai/course/list";
	}
	/**
	 * forbid a announcement
	 * @param anno_id
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/course/{course_id}/forbid")
	public String forbidcourse(@PathVariable Long course_id){
		Course course = courseService.findOneById(course_id);
		course.setForbidden("yes");
		courseService.updateTeacherCourse(course);
		return "redirect:/admin/caicai/course/list";
	}
	/**
	 * search a course by courseName
	 * @param searchParam
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/course/search", method = RequestMethod.POST)
	public String searchCourseByName(@RequestParam("searchParam") String searchParam,Model model){
		String name = searchParam.trim();
		List<Course> courseList = courseService.findAllForAdmin();
		List<Course> newCourseList = new ArrayList<Course>();
		if(name != null && !name.equals("")){
			for (int i = 0; i <courseList.size() ; i++) {
				if(courseList.get(i).getCourseName().contains(name)){
					newCourseList.add(courseList.get(i));
				}
			}
			model.addAttribute("courseList", newCourseList);
			model.addAttribute("courseListCount",newCourseList.size());
			model.addAttribute("searchParam", name);
			return "admin.caicai.course.list";
		}else{
			return "redirect:/admin/caicai/course/list";
		}
	}
	
	/* oprate blog detail controller   */
	
	/**
	 * free a announcement
	 * @param anno_id
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/blog/{blog_id}/free")
	public String freeBlog(@PathVariable Long blog_id){
		BlogPost blogPost = blogService.findOne(blog_id);
		blogPost.setForbidden(null);
		blogService.updateBlogPost(blogPost);
		return "redirect:/admin/caicai/blog/list";
	}
	/**
	 * forbid a announcement
	 * @param anno_id
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/blog/{blog_id}/forbid")
	public String forbidblog(@PathVariable Long blog_id){
		BlogPost blogPost = blogService.findOne(blog_id);
		blogPost.setForbidden("yes");
		blogService.updateBlogPost(blogPost);
		return "redirect:/admin/caicai/blog/list";
	}
	/**
	 * search a course by courseName
	 * @param searchParam
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/blog/search", method = RequestMethod.POST)
	public String searchBlogByTitle(@RequestParam("searchParam") String searchParam,Model model){
		String title = searchParam.trim();
		List<BlogPost> blogList = blogService.findAllBlogsNotGarbageAndNotDraftForSuperAdmin();
		List<BlogPost> newBlogList = new ArrayList<BlogPost>();
		if(title != null && !title.equals("")){
			for (int i = 0; i <blogList.size() ; i++) {
				if(blogList.get(i).getTitle().contains(title)){
					newBlogList.add(blogList.get(i));
				}
			}
			model.addAttribute("blogList", newBlogList);
			model.addAttribute("blogListCount",newBlogList.size());
			model.addAttribute("searchParam", title);
			return "admin.caicai.blog.list";
		}else{
			return "redirect:/admin/caicai/blog/list";
		}
	}
	
/* oprate resource detail controller   */
	
	/**
	 * free a announcement
	 * @param anno_id
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/resource/{resource_id}/free")
	public String freeResource(@PathVariable Long resource_id){
		CourseResource resource = resourceService.findOneById(resource_id);
		resource.setForbidden(null);
		resourceService.updateResource(resource);
		return "redirect:/admin/caicai/resource/list";
	}
	/**
	 * forbid a announcement
	 * @param anno_id
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/resource/{resource_id}/forbid")
	public String forbidResource(@PathVariable Long resource_id){
		CourseResource resource = resourceService.findOneById(resource_id);
		resource.setForbidden("yes");
		resourceService.updateResource(resource);
		return "redirect:/admin/caicai/resource/list";
	}
	/**
	 * search a course by courseName
	 * @param searchParam
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/resource/search", method = RequestMethod.POST)
	public String searchResourceByFileName(@RequestParam("searchParam") String searchParam,Model model){
		String fileName = searchParam.trim();
		List<CourseResource> resourceList = resourceService.findAllByStatusForSuperAdmin();
		List<CourseResource> newResourceList = new ArrayList<CourseResource>();
		if(fileName != null && !fileName.equals("")){
			for (int i = 0; i <resourceList.size() ; i++) {
				if(resourceList.get(i).getFileName().contains(fileName)){
					newResourceList.add(resourceList.get(i));
				}
			}
			model.addAttribute("resourceList", newResourceList);
			model.addAttribute("searchParam", fileName);
			model.addAttribute("resourceListCount",newResourceList.size());
			return "admin.caicai.resource.list";
		}else{
			return "redirect:/admin/caicai/resource/list";
		}
	}
	
	/**
	 * create a rechargeCard id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/caicai/recharge/createCardId",method = RequestMethod.POST)
	public void createCardId(HttpServletResponse response) throws Exception{
		logger.info("==== into createCardId controller ====");
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<16;i++){
			sb.append(random.nextInt(9));
		}
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
		out.flush();
		out.close();
	}
	/**
	 * create a new recharge card
	 * @param cardForm
	 * @param validResult
	 * @return
	 */
	@RequestMapping(value="/admin/caicai/recharge/new" ,method = RequestMethod.POST)
	public String createRechargeCard(@Valid RechargeCardForm cardForm,BindingResult validResult){
		logger.info("==== into create recharge controller ====");
		if(validResult.hasErrors()){
			logger.info("RechargeCardForm Validation Failed " + validResult);
			return "redirect:/admin/caicai/recharge/create";
		}else{
			Recharge recharge = new Recharge();
			recharge.setCardid(cardForm.getCardid());
			recharge.setPrice(Long.parseLong(cardForm.getPrice()));
			recharge.setDate(new Date());
			rechargeService.createRecharge(recharge);
			return "redirect:/admin/caicai/recharge/list";
		}
	}
	
	@RequestMapping(value="/admin/caicai/recharge/search", method = RequestMethod.POST)
	public String searchRechargeCardByCardid(@RequestParam("searchParam") String searchParam,Model model){
		String cardid = searchParam.trim();
		Recharge recharge = null;
		if(cardid != null && !cardid.equals("")){
			recharge = rechargeService.findOneByCardid(cardid);
			model.addAttribute("recharge", recharge);
			model.addAttribute("searchParam", cardid);
			return "admin.caicai.recharge.list";
		}else{
			return "redirect:/admin/caicai/recharge/list";
		}
	}
	
	@RequestMapping(value="/admin/caicai/activity/new", method = RequestMethod.POST)
	public String createActivity(@Valid ActivityForm activityForm, BindingResult result,HttpSession session){
		logger.info("==== into create activity controller ====");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(result.hasErrors()){
			logger.info("activityForm Validation Failed " + result);
			return "redirect:/admin/caicai/activity/create";
		}else{
			Activity activity = new Activity();
			activity.setTitle(activityForm.getTitle());
			activity.setContent(activityForm.getContent());
			activity.setDate(new Date());
			activity.setUser(userInfo.getUser());
			activityService.create(activity);
			return "redirect:/admin/caicai/activity/list";
		}
		
	}
	
	@RequestMapping(value="/admin/caicai/activity/edit", method = RequestMethod.POST)
	public String editActivity(HttpSession session,@Valid ActivityForm activityForm, BindingResult result,@RequestParam("activity_id") Long id){
		logger.info("==== into create activity controller ====");
		//UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(result.hasErrors()){
			logger.info("activityForm Validation Failed " + result);
			return "redirect:/admin/caicai/activity/create";
		}else{
			Activity activity = activityService.findOne(id);
			activity.setTitle(activityForm.getTitle());
			activity.setContent(activityForm.getContent());
			activity.setDate(new Date()); 
			activityService.update(activity);
			return "redirect:/admin/caicai/activity/list";
		}
		
	}
	
	@RequestMapping(value="/admin/caicai/activity/destory", method = RequestMethod.POST)
	public String destoryActivity(HttpSession session,@RequestParam("activity_id") Long id){
		logger.info("==== into destory activity controller ====");		
			activityService.delete(id);
			return "redirect:/admin/caicai/activity/list";

		
	}
	
	
}
