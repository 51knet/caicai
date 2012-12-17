package com.knet51.ccweb.controllers.teacher.resource;

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
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
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
	
	@RequestMapping(value="/admin/teacher/resource/list")
	public String teacherResouInfo(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize){
		logger.info("#####Into TeacherResouInfoPageController#####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Long user_id = userInfo.getId();
		User user = userService.findOne(user_id);
		Page<Resource> onePage = resourceService.findAllResouByUser(pageNumber, pageSize, user);
		model.addAttribute("page", onePage);
		return "admin.teacher.resource.list";
	}
	@RequestMapping(value="/admin/teacher/resource/new")
	public String teacherResouAdd(HttpSession session,Model model ){
		logger.info("#####Into TeacherResouInfoAdd#####");
		List<ResourceType> listType = resourceTypeService.getAllType();
		model.addAttribute("type", listType);
		return "admin.teacher.resource.new";
	}
	
	@RequestMapping(value="/admin/teacher/resource/type/list")
	public String teacherResouType(Model model){
		logger.info("#### Into TeacherResouType ####");
		List<ResourceType> list = resourceTypeService.getAllType();
		model.addAttribute("list", list);
		return "admin.teacher.resource.type.list";
	}
	
	
	@RequestMapping(value="/admin/teacher/resource/type/add")
	public String teacherResouTypeAdd(){
		
		return "admin.teacher.resource.type.add";
	}
	
	/* teacher front page controller */
	
	@RequestMapping(value="/teacher/{teacher_id}/resource/list")
	public String teacherResourceList(@PathVariable Long teacher_id,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<Resource> onePage = resourceService.findAllResouByUser(pageNumber, pageSize, user);
		model.addAttribute("page", onePage);
		return "teacher.resource.list";
	}
	
	@RequestMapping(value="/teacher/{teacher_id}/resource/view/{resource_id}")
	public String teacherResourceDetail(@PathVariable Long teacher_id,@PathVariable Long resource_id,Model model){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Resource resource = resourceService.findOneById(resource_id);
		model.addAttribute("resource", resource);
		return "teacher.resource.detail";
	}
	
	
	
	

}