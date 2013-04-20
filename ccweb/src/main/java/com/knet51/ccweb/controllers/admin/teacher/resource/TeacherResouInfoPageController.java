package com.knet51.ccweb.controllers.admin.teacher.resource;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;
import com.knet51.ccweb.jpa.entities.resource.Resource;
import com.knet51.ccweb.jpa.entities.resource.ResourceType;
import com.knet51.ccweb.jpa.services.ResourceService;
import com.knet51.ccweb.jpa.services.ResourceTypeService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class TeacherResouInfoPageController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherResouInfoPageController.class);
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private ResourceTypeService resourceTypeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TeacherService teacherService;
	
	/**
	 * show a teacher's resources
	 * @param session
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/admin/resource/list")
	public String teacherResouInfo(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize){
		logger.info("#####Into TeacherResouInfoPageController#####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		if(user.getRole().equals("user")){
			return "redirect:/admin";
		}else{
			Page<CourseResource> onePage = resourceService.findAllResouByUserAndStatus(pageNumber, pageSize, user, GlobalDefs.STATUS_RESOURCE);
			model.addAttribute("page", onePage);
			return "admin.resource.list";
		}
	}
	
	/**
	 * show create resource page
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/resource/new")
	public String teacherResouAdd(HttpSession session,Model model ){
		logger.info("#####Into TeacherResouInfoAdd#####");
		List<ResourceType> listType = resourceTypeService.getTypeByTypeStatus(GlobalDefs.STATUS_RESOURCETYPE);
		model.addAttribute("type", listType);
		return "admin.resource.new";
	}
	
	/**
	 * show the resource type list
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/resource/type/list")
	public String teacherResouType(Model model){
		logger.info("#### Into TeacherResouType ####");
		List<ResourceType> list = resourceTypeService.getTypeByTypeStatus(GlobalDefs.STATUS_RESOURCETYPE);
		model.addAttribute("list", list);
		return "admin.resource.type.list";
	}
	
	
	
	/* teacher front page controller */
	/**
	 * show the teacher's resource in front page
	 * @param teacher_id
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/teacher/{teacher_id}/resource/list")
	public String teacherResourceList(@PathVariable Long teacher_id,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<CourseResource> onePage = resourceService.findAllResouByUserAndStatus(pageNumber, pageSize, user, GlobalDefs.STATUS_RESOURCE);
		model.addAttribute("page", onePage);
		return "teacher.resource.list";
	}
	
	/**
	 * show a resource detail information in front page
	 * @param teacher_id
	 * @param resource_id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/teacher/{teacher_id}/resource/view/{resource_id}")
	public String teacherResourceDetail(@PathVariable Long teacher_id,@PathVariable Long resource_id,Model model){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		CourseResource resource = resourceService.findOneById(resource_id);
		model.addAttribute("resource", resource);
		return "teacher.resource.detail";
	}
	
	
	
	

}