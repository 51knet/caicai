package com.knet51.ccweb.controllers.enterprise;

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
import com.knet51.ccweb.jpa.entities.EnterpriseTeacher;
import com.knet51.ccweb.jpa.services.EnterpriseTeacherService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;

@Controller
public class EnterpriseTeacherInfoPageController {
	private static Logger logger = 
			LoggerFactory.getLogger(EnterpriseTeacherInfoPageController.class);
	
	@Autowired
	private EnterpriseTeacherService enterpriseTeacherService;
	
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
	
	@RequestMapping(value="/admin/enterprise/teacher/new")
	public String createTeacherPage(){
		logger.info("======= into create enterprise teacher page controller");
		return "admin.enterprise.teacher.new";
	}
	
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
	
	
	@RequestMapping(value = "/admin/enterprise/teacher/teacherInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse courseFormAjaxJson(@Valid EnterpriseTeacherInfoForm enterpriseTeacherInfoForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
}
