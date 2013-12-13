package com.knet51.ccweb.controllers.admin.enterprise.teacher;

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
import com.knet51.ccweb.jpa.entities.EnterpriseTeacher;
import com.knet51.ccweb.jpa.services.FriendsRelateService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.achievement.TeacherHonorService;
import com.knet51.ccweb.jpa.services.announcement.AnnoPhotoService;
import com.knet51.ccweb.jpa.services.announcement.AnnouncementService;
import com.knet51.ccweb.jpa.services.course.CourseService;
import com.knet51.ccweb.jpa.services.course.CourseTypeService;
import com.knet51.ccweb.jpa.services.enterprise.EnterpriseTeacherService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;

@Controller
public class EnterpriseTeacherInfoPageController {
	private static Logger logger = 
			LoggerFactory.getLogger(EnterpriseTeacherInfoPageController.class);
	
	@Autowired
	private EnterpriseTeacherService enterpriseTeacherService;
	@Autowired
	private CourseService courseService;
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
	
}
