package com.knet51.ccweb.controllers.admin.teacher.resource;

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
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;
import com.knet51.ccweb.jpa.entities.resource.ResourceType;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.resources.ResourceService;
import com.knet51.ccweb.jpa.services.resources.ResourceTypeService;
import com.knet51.ccweb.util.MyUtil;

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
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		logger.info("#####Into TeacherResouInfoPageController#####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		if(user.getRole().equals("user")){
			return "redirect:/admin";
		}else{
			try {
				List<CourseResource> list = resourceService.listAllByUser(user);
				int pageNum = MyUtil.getPageNumber(pageNumber, list.size(), pageSize);
				
				Page<CourseResource> onePage = resourceService.findAllResouByUserAndStatus(pageNum, pageSize, user, GlobalDefs.STATUS_RESOURCE);
				model.addAttribute("page", onePage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (userInfo.getUser().getRole().equals("teacher")) {
				logger.info("----- teacher resource----");
				return "admin.teacher.resource.list";
			} else if (userInfo.getUser().getRole().equals("enterprise")) {
				logger.info("----- enterprise resource ----");
				return "admin.enterprise.resource.list";
			} else {
				return "404";
			}
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
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		model.addAttribute("type", listType);
		if (userInfo.getUser().getRole().equals("teacher")) {
			return "admin.teacher.resource.new";
		} else if (userInfo.getUser().getRole().equals("enterprise")) {
			return "admin.enterprise.resource.new";
		} else {
			return "404";
		}
	}
	
	/**
	 * show the resource type list
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/resource/type/list")
	public String teacherResouType(Model model,HttpSession session){
		logger.info("#### Into TeacherResouType ####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<ResourceType> list = resourceTypeService.getTypeByTypeStatus(GlobalDefs.STATUS_RESOURCETYPE);
		model.addAttribute("list", list);
		if (userInfo.getUser().getRole().equals("teacher")) {
			return "admin.teacher.resource.type.list";
		} else if (userInfo.getUser().getRole().equals("enterprise")) {
			return "admin.enterprise.resource.type.list";
		} else {
			return "404";
		}
	}
	
}