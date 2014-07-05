package com.graphene.web.controller.forgotPsw;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.service.UserService;
import com.graphene.web.util.mailSender.MailSender;



/**
 * Handles requests for the application home page.
 */
@Controller
public class ForgotPswController {
	private static final String DEFAULT_URL= "http://www.51knet.com/ccweb";
	private static final Logger logger = LoggerFactory
			.getLogger(ForgotPswController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/forgotPsw", method = RequestMethod.POST)
	public String forgotPsw(@Valid ForgotPswForm forgotPswForm,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.info("ForgotPswForm Validation Failed " + result);
			return "home";
		} else {
			logger.debug("ForgotPswForm :" + forgotPswForm.toString());
			String email = forgotPswForm.getForgotemail();
			boolean mailSuccess = false;
			User findUser = userService.findByEmailAddress(email);
			if (findUser != null) {
				
				String randomUrl = MailSender.getInstance()
						.produceRandomString();
				findUser.setForgotPsw(randomUrl);
				findUser = userService.updateUser(findUser);
				randomUrl += "/";
				randomUrl += findUser.getId();
				mailSuccess = MailSender.getInstance().SendMail(email,
						DEFAULT_URL+"/forgotPsw/" + randomUrl);
				if (mailSuccess) {
					String hrefString = email;
					hrefString = hrefString
							.substring(hrefString.indexOf("@") + 1);
					model.addAttribute("hrefString", hrefString);
					return "fogotPsw.successful";
				} else {
					return "404";
				}
			} else {
				return "home";
			}
		}
	}
}
