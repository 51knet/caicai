package com.knet51.ccweb.controllers.forgotPsw;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class ForgotPswValidateController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/forgotPsw/{randomUrl}/{idString}", method = RequestMethod.GET)
	public String forgotPsw(@PathVariable String randomUrl,
			@PathVariable String idString, HttpSession session) {
		Integer id = Integer.parseInt(idString);
		User result = userService.findOne(id.longValue());
		boolean userConfirmed = (result != null)
				&& randomUrl.equals(result.getForgotPsw());
		if (userConfirmed) {
			session.setAttribute("resetPswId", idString);
			return "fogotPsw.update";
		} else {
			return "home";
		}
	}

}
