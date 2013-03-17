package com.knet51.ccweb.controllers.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.controllers.teacher.TeacherPswForm;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;


/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);


	@Autowired
	private UserService userService;
	
	@Transactional
	@RequestMapping(value = "/admin/user/details")
	public String detailInfoPage(@RequestParam("active") String active,Model model,HttpSession session) {
		if(active == null || active.equals("")){
			active = "avatar";
		}
		model.addAttribute("active", active);
		return "admin.user.details";
	}
	
	@Transactional
	@RequestMapping(value = "/admin/user/changePsw")
	public String changePsw(@Valid TeacherPswForm pswForm,
			BindingResult validResult, HttpSession session, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttr) {
		logger.info("#### changePsw InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("changePsw Validation Failed " + validResult);
			return "redirect:/admin/user/details?active=psw";
		} else {
			logger.info("### changePsw Validation passed. ###");

			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			
			User user = userService.findOne(userInfo.getId());
			String password = user.getPassword();
			if(password.equals(pswForm.getOri_psw())){
				user.setPassword(pswForm.getNew_psw());
				user = userService.updateUser(user);
				userInfo.setUser(user);
				session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
				String message = "密码修改成功";
				redirectAttr.addFlashAttribute("message", message);
			}else{
				logger.info("original password is not correct. Nothing update.");
			}
			return "redirect:/admin/user/details?active=psw";
		}
	}
	
	@RequestMapping(value = "/admin/user/pswInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse pswfurInfoFormAjaxJson(@Valid UserPswForm userPswForm, BindingResult result) {
		//logger.info("------into psw ajax");
		return AjaxValidationEngine.process(result);
	}
}
