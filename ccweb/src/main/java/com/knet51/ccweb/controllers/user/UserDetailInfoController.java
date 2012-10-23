package com.knet51.ccweb.controllers.user;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserDetailInfoController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDetailInfoController.class);

	@Autowired
	private UserService userService;

	@Transactional
	@RequestMapping(value = "/admin/user/details")
	public String detailInfo(@Valid UserDetailInfoForm detailInfoForm,
			BindingResult validResult, HttpSession session) {
		logger.info("#### DetailInfoController ####");

		logger.info("#### "
				+ (detailInfoForm.getPromote() != null && detailInfoForm
						.getPromote().equals("promote")) + " ####");
		if (detailInfoForm.getPromote() != null
				&& detailInfoForm.getPromote().equals("promote")) {
			return "user.dispatcher";
		}

		if (validResult.hasErrors()) {
			logger.info("== detailInfoForm Validation Failed " + validResult
					+ " ==");
			return "admin.user.details";
		} else {
			logger.info("### detailInfoForm Validation passed. ###");

			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			User user = userService.findOne(userInfo.getId());

			user.setName(detailInfoForm.getName());
			user.setCell_phone(detailInfoForm.getCellPhone());
			user.setGender(detailInfoForm.getGender());
			user = userService.updateUser(user);
			
			userInfo.setUser(user);
			
			session.setAttribute("userInfo", userInfo);
			
			return "admin.user.details";
		}
	}
}
