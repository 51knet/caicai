package com.knet51.courses.controllers.login;

import java.nio.charset.Charset;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.courses.beans.UserInfo;
import com.knet51.courses.controllers.login.LoginForm;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.courses.util.ajax.AjaxValidationEngine;
import com.knet51.courses.util.ajax.ValidationResponse;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	@Autowired
	private UserService service;

	@RequestMapping(value = "/signin")
	public String signin(@Valid LoginForm loginForm, BindingResult result,HttpSession session, 
			@RequestParam("currentUrl") String currentUrl,HttpServletRequest request,HttpServletResponse response) {
		
		currentUrl = currentUrl.replaceAll("/courses", "");
		if (result.hasErrors()) {
			logger.info("LoginForm Validation Failed " + result);
			return "redirect:"+currentUrl;
		} else {
			logger.debug("loginForm :" + loginForm.toString());
			String email = loginForm.getEmail();
			String psw = loginForm.getPassword();
			User user = service.getValidUser(email, psw);
			boolean succeed = user != null;
			logger.info("Login result " + succeed);
			if (succeed) {
//				UserInfo userInfo = new UserInfo(user);
//				session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			
				
				String randomUrl = user.getRandomUrl();
				String forbidden = user.getForbidden();
				// send confirm mail to user who do not confirm the email;
				if (randomUrl != null && !(randomUrl.equals("pass"))) {
					session.setAttribute("nonValidatedUser", user);
					return "redirect:"+currentUrl;
				}
				//
				if (forbidden != null && forbidden.equals("yes")) {
					return "redirect:"+currentUrl;
				}
				// confirmed users;
				// if (loginForm.getRemeberMe() == 1) {
				String encodedEmail = new String(
						Base64.encode(email.getBytes()),
						Charset.forName("US-ASCII"));
				logger.debug(encodedEmail);
				Cookie cookie = new Cookie(GlobalDefs.COOKIE_IDENTITY,
						encodedEmail);
				// cookie.setDomain("localhost");
				cookie.setPath("/");
				// cookie.setMaxAge(60 * 60 * 24 * 14);
				response.addCookie(cookie);
				// }
				UserInfo userInfo = new UserInfo(user);
				session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
				return "redirect:"+currentUrl;
			} else {
				return "redirect:"+currentUrl;
			}
		}

	}

	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		session.removeAttribute(GlobalDefs.SESSION_USER_INFO);
		String killCookie = "";
		Cookie cookie = new Cookie(GlobalDefs.COOKIE_IDENTITY, killCookie);
		// cookie.setDomain("localhost");
		cookie.setPath("/");
		// cookie.setMaxAge(60 * 60 * 24 * 14);
		response.addCookie(cookie);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/patent/checkEmailAndPassword", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResponse processFormAjaxJson(@Valid LoginForm loginForm,
			BindingResult result, HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
//	/**
//	 * author lbx
//	 * 
//	 * @param session
//	 * @param request
//	 * @param 邮箱验证是否存在
//	 * @param password
//	 * @param response
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/checkemailajax",method = { RequestMethod.POST,
//			RequestMethod.GET })
//	public void checkEmail(HttpSession session, HttpServletRequest request,
//			@RequestParam("email") String email, HttpServletResponse response)
//			throws Exception {
//		PrintWriter out = response.getWriter();
//		User user = service.getValidEmail(email);
//		Integer num = 1;
//		if (user == null) {
//			num = 0;
//		}
//		String number = num.toString();
//		out.write(number);
//		out.flush();
//		out.close();
//	}
	
	@RequestMapping(value = "/patent/checkLogin", method = RequestMethod.POST)
	public @ResponseBody String checkEmailAndPsw(HttpServletResponse response,
			LoginForm loginForm) throws Exception {
		String email = loginForm.getEmail();
		String pwd = loginForm.getPassword();

		User user = null;
		boolean value = false;
		value = service.login(email, pwd);
		user = service.getValidUser(email, pwd);
		Integer num = 1;
		if (value == false) {
			num = 0;
		}
		if (user != null && user.getForbidden().equals("yes")) {
			num = 0;
		}
		String number = num.toString();
		return number;
	}
	
	@RequestMapping(value = "/projects/view/checkEmailAndPassword", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResponse processProjectDetailFormAjaxJson(@Valid LoginForm loginForm,
			BindingResult result, HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value = "/projects/view/checkLogin", method = RequestMethod.POST)
	public @ResponseBody String loginProjectDetail(HttpServletResponse response,
			LoginForm loginForm) throws Exception {
		String email = loginForm.getEmail();
		String pwd = loginForm.getPassword();

		User user = null;
		boolean value = false;
		value = service.login(email, pwd);
		user = service.getValidUser(email, pwd);
		Integer num = 1;
		if (value == false) {
			num = 0;
		}
		if (user != null && user.getForbidden().equals("yes")) {
			num = 0;
		}
		String number = num.toString();
		return number;
	}

}
