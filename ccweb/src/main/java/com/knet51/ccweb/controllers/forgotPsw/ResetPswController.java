package com.knet51.ccweb.controllers.forgotPsw;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ResetPswController {

	private static final Logger logger = LoggerFactory
			.getLogger(ResetPswController.class);
	@Autowired
	private UserService service;

	@RequestMapping(value = "resetPsw", method = { RequestMethod.POST })
	public String resetPsw(@Valid ResetPswForm resetPswForm,
			BindingResult validResult, HttpSession session) {
		Integer id;
		String forgotPsw;
		User user;
		String idString = (String) session.getAttribute("resetPswId");
		if (idString != null && !idString.equals("")) {
			id = Integer.parseInt(idString);
			user = service.findOne(id.longValue());
		} else {
			return "redirect:/";
		}

		if (user != null) {
			forgotPsw = user.getForgotPsw();
		} else {
			return "redirect:/";
		}
		if (validResult.hasErrors()) {
			return "redirect:/forgotPsw/" + forgotPsw + "/" + id;
		} else {
			String psw = resetPswForm.getPsw();
			user.setPassword(psw);
			user = service.updateUser(user);
			UserInfo userInfo = new UserInfo(user);
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			logger.info(userInfo.getEmail() + " = " + userInfo.getId());
			return "redirect:/admin";
		}
	}
}
