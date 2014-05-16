package com.knet51.patents.controllers.admin;

import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knet51.patents.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Student;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.UserRight;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.StudentService;
import com.knet51.patents.jpa.services.TeacherService;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.applyright.UserRightService;
import com.knet51.patents.util.ajax.AjaxValidationEngine;
import com.knet51.patents.util.ajax.ValidationResponse;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserRightService userRightService;


	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Model model, HttpSession session) {
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		
		if(userInfo != null){
			List<UserRight> rights = userRightService.findUserRightListByUser(userInfo.getUser());
			for (UserRight userRight : rights) {
				session.setAttribute(userRight.getUserRight(), userRight.getUserRight());
			}
			if (userInfo.getRole().equals("user")) {
				return "redirect:/admin/user";
			} else if (userInfo.getRole().equals("teacher")) {
				return "redirect:/admin/teacher";
			}else{
				return "home";
			} 
		}else {
			return "home";
		}
	}

	@RequestMapping(value = "/admin/user", method = RequestMethod.GET)
	public String adminUser(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);

		if (userInfo != null && userInfo.getRole().equals("user")) {
			Student student = studentService.findOne(userInfo.getId());
			userInfo.setStudent(student);
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			return "redirect:/admin/details?active=photo";
		} else if (userInfo != null && userInfo.getRole().equals("teacher")) {
			return "redirect:/admin/teacher";
		} else {
			return "home";
		}

	}

	@RequestMapping(value = "/admin/teacher", method = RequestMethod.GET)
	public String adminTeacher(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);

		if (userInfo != null && userInfo.getRole().equals("user")) {
			return "redirect:/admin/user";
		} else if (userInfo != null && userInfo.getRole().equals("teacher")) {
			Teacher teacher = teacherService.findOne(userInfo.getId());
			userInfo.setTeacher(teacher);
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			// set default home page to set resume page;
			return "redirect:/admin/details?active=photo";
		} else {
			return "home";
		}
	}




	@RequestMapping(value = "/admin/details")
	public String detailInfoPage(@RequestParam("active") String active,
			Model model, HttpSession session) {
		UserInfo userInfo;
		userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		//model.addAttribute("userInfoModel", userInfo);
		String role = userInfo.getRole();
		if (active == null || active.equals("")) {
			active = "avatar";
		}
		model.addAttribute("active", active);
		if (role.equals("user")) {
			logger.info("-- into admin details user photo ---");
			return "admin.user.details";
		} else if (role.equals("teacher")) {
			return "admin.teacher.details";
		}  else {
			return "404";
		}
	}

	@RequestMapping(value = "/admin/pswInfoCheck", method = RequestMethod.POST)
	public void checkEmailAndPsw(HttpServletResponse response,
			HttpSession session, PswForm pswForm) throws Exception {
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		PrintWriter out = response.getWriter();
		String email = userInfo.getEmail();
		User user = userService.findByEmailAddress(email);
		String password = user.getPassword();
		String oriPsw = pswForm.getOri_psw();
		Integer num = 1;
		if (!password.equals(oriPsw)) {
			num = 0;
		}
		String number = num.toString();
		out.write(number);
		out.flush();
		out.close();
	}
	
	/**
	 * check user's psw in pcenter url
	 * @param response
	 * @param session
	 * @param oriPsw
	 * @throws Exception
	 */
	@RequestMapping(value = "/pcenter/pswInfoCheck", method = RequestMethod.POST)
	public void checkUserEmailAndPsw(HttpServletResponse response,
			HttpSession session, @RequestParam("oriPsw") String oriPsw) throws Exception {
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		PrintWriter out = response.getWriter();
		String email = userInfo.getEmail();
		User user = userService.findByEmailAddress(email);
		String password = user.getPassword();
		Integer num = 1;
		logger.info("-------"+oriPsw);
		if (!password.equals(oriPsw)) {
			num = 0;
		}
		String number = num.toString();
		out.write(number);
		out.flush();
		out.close();
	}


	@RequestMapping(value = "/admin/changePsw")
	public String changePsw(@Valid PswForm pswForm, BindingResult validResult,
			HttpSession session, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttr) {
		logger.info("#### changePsw InfoController ####");

		if (validResult.hasErrors()) {
			logger.info("changePsw Validation Failed " + validResult);
			return "redirect:/admin/details?active=psw";
		} else {
			logger.info("### changePsw Validation passed. ###");

			UserInfo userInfo = (UserInfo) session
					.getAttribute(GlobalDefs.SESSION_USER_INFO);
			String role = userInfo.getRole();
			User user = userService.findOne(userInfo.getId());
			String password = user.getPassword();
			if (password.equals(pswForm.getOri_psw())) {
				user.setPassword(pswForm.getNew_psw());
				user = userService.updateUser(user);
				userInfo.setUser(user);
				if (role.equals("teacher")) {
					Teacher teacher = teacherService.findOne(userInfo.getId());
					userInfo.setTeacher(teacher);
				} 
				session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
				String message = "密码修改成功";
				redirectAttr.addFlashAttribute("message", message);
			} else {
				logger.info("original password is not correct. Nothing update.");
			}
			
			return "redirect:/admin/details?active=psw";
		}
	}
	
	@RequestMapping(value="/admin/banner" , method = RequestMethod.POST)
	public String updateUserBanner(@RequestParam("banner") Long banner_id,HttpSession session){
		logger.info("#### update user photo Controller ####");
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		user.setBanner_id(banner_id);
		userService.updateUser(user);
		userInfo.setUser(user);
		return "redirect:/admin/details?active=banner";
	}
	
	@RequestMapping(value="/admin/photo" , method = RequestMethod.POST)
	public String updateUserPhoto(@RequestParam("photo") String photo,HttpSession session){
		logger.info("#### update user photo Controller ####");
		 String photo_url = "/resources/img/avatar/p"+photo+".jpg";
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		user.setPhoto_url(photo_url);
		userService.updateUser(user);
		userInfo.setUser(user);
		return "redirect:/admin/details?active=photo";
	}
	
	

	@RequestMapping(value = "/admin/pswInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResponse pswfurInfoFormAjaxJson(@Valid PswForm pswForm,
			BindingResult result) {
		// logger.info("------into psw ajax");
		return AjaxValidationEngine.process(result);
	}
	
	/**
	 * validate the psw ajaxmethod in pcenter url
	 * @param pswForm
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/pcenter/pswInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResponse userPswfurInfoFormAjaxJson(@Valid PswForm pswForm,
			BindingResult result) {
		// logger.info("------into psw ajax");
		return AjaxValidationEngine.process(result);
	}

	@RequestMapping(value = "/{selfUrl}")
	public String commonRegister(@PathVariable String selfUrl,
			HttpSession session) {
		User user = userService.findBySelfUrl(selfUrl);
		if (user != null) {
			Long id = user.getId();
			return "redirect:/user/" + id.toString();
		} else {
			return "404";
		}
	}
}
