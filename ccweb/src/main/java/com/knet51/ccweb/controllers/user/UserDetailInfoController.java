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
import org.springframework.web.bind.annotation.RequestMethod;

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
	@RequestMapping(value = "/userDetailInfo", method = RequestMethod.POST)
	public String detailInfo(@Valid UserDetailInfoForm detailInfoForm,
			BindingResult validResult, HttpSession session) {
		logger.info("#### DetailInfoController ####");

		logger.info("#### "
				+ (detailInfoForm.getPromote() != null && detailInfoForm
						.getPromote().equals("promote")) + " ####");
		if (detailInfoForm.getPromote() != null
				&& detailInfoForm.getPromote().equals("promote")) {
			return "userTypePage";
		}

		if (validResult.hasErrors()) {
			logger.info("== detailInfoForm Validation Failed " + validResult
					+ " ==");
			return "userInfoPage";
		} else {
			logger.info("### detailInfoForm Validation passed. ###");

			UserInfo userInfo = (UserInfo) session.getAttribute("user");
			
			User user = userInfo.getUser();
			user = userService.findOne(user.getId());

			user.setName(detailInfoForm.getName());
			user.setName(detailInfoForm.getNickName());
			user.setName(detailInfoForm.getGender());
			user = userService.updateUser(user);
			
			userInfo.setUser(user);
			
			session.setAttribute("user", userInfo);
			
			return "userInfoPage";
		}
	}
}
