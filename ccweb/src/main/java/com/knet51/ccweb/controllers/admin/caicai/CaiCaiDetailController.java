package com.knet51.ccweb.controllers.admin.caicai;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.Authentication;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;
import com.knet51.ccweb.jpa.entities.courses.TeacherCourse;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.AuthenticationService;
import com.knet51.ccweb.jpa.services.BlogService;
import com.knet51.ccweb.jpa.services.CourseResourceService;
import com.knet51.ccweb.jpa.services.ResourceService;
import com.knet51.ccweb.jpa.services.TeacherCourseService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
@Controller
public class CaiCaiDetailController {
	private static final Logger logger = LoggerFactory
			.getLogger(CaiCaiDetailController.class);
	
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
			
			List<TeacherCourse> courseList = courseService.findCourseByUserAndPublishGreaterThanForSuperAdmin(user, GlobalDefs.PUBLISH_NUM_DELETE);
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
					resourceService.UpdateResource(resourceList.get(i));
				}
			}
			
			user.setForbidden(null);
			userService.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/admin/caicai/detail/"+user.getRole();
	}
	
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
			
			List<TeacherCourse> courseList = courseService.findCourseByUserAndPublishGreaterThanForSuperAdmin(user, GlobalDefs.PUBLISH_NUM_DELETE);
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
					resourceService.UpdateResource(resourceList.get(i));
				}
			}
			
			user.setForbidden("yes");
			userService.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/admin/caicai/detail/"+user.getRole();
	}
	
	
	
	@RequestMapping(value="/admin/caicai/authentication/refuse/new")
	public String refuseAuthenticationReason(@RequestParam("auth_id") Long auth_id, Model model,@Valid AuthenticationRefuseForm refuseForm ){
		logger.info("====== into pass authentication controller   =====");
		Authentication authentication = authenticationService.findOneById(auth_id);
		authentication.setReason(refuseForm.getReason());
		authentication.setStatus("refuse");
		authenticationService.updateAuthentication(authentication);
		return "redirect:/admin/caicai/";
	}
	
}
