package com.knet51.ccweb.controllers.teacher.resource;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.resource.Resource;
import com.knet51.ccweb.jpa.entities.resource.ResourceType;
import com.knet51.ccweb.jpa.services.ResourceService;
import com.knet51.ccweb.jpa.services.ResourceTypeService;

@Controller
public class TeacherResouInfoPageController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherResouInfoPageController.class);
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private ResourceTypeService resourceTypeService;
	
	@RequestMapping(value="/admin/teacher/resource/list")
	public String teacherResouInfo(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="2") int pageSize){
		logger.info("#####Into TeacherResouInfoPageController#####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		Page<Resource> onePage = resourceService.findAllResouById(pageNumber, pageSize, user);
		model.addAttribute("page", onePage);
		return "admin.teacher.resource.list";
	}
	@RequestMapping(value="/admin/teacher/resource/add")
	public String teacherResouAdd(HttpSession session,Model model ){
		logger.info("#####Into TeacherResouInfoAdd#####");
		List<ResourceType> listType = resourceTypeService.getAllType();
		model.addAttribute("type", listType);
		return "admin.teacher.resource.add";
	}
	
	@RequestMapping(value="/admin/teacher/resource/type")
	public String teacherResouType(Model model){
		logger.info("#### Into TeacherResouType ####");
		List<ResourceType> list = resourceTypeService.getAllType();
		model.addAttribute("list", list);
		return "admin.teacher.resource.type";
	}
	
	
	@RequestMapping(value="/admin/teacher/resource/type/add")
	public String teacherResouTypeAdd(){
		
		return "admin.teacher.resource.type.add";
	}


}