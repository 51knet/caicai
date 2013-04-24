package com.knet51.ccweb.controllers.admin.caicai;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Authentication;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.AuthenticationService;
import com.knet51.ccweb.jpa.services.UserService;


@Controller
public class CaicaiPageController {
	private static final Logger logger = LoggerFactory
			.getLogger(CaicaiPageController.class);
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/admin/caicai")
	public String showSuperAdmin(){
		
		return "redirect:/admin/caicai/authentication";
	}
	
	@RequestMapping(value="/admin/caicai/authentication")
	public String showSuperAdmin(Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize) {
		logger.info("====== into caicai page controller =====");
		Page<Authentication> page = authenticationService.findAll(pageNumber, pageSize);
		model.addAttribute("page", page);
		return "admin.caicai.authentication";
	}
	
	
	@RequestMapping(value="/admin/caicai/detail/{role}")
	public String showAllUser(@PathVariable String role,
			Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize) {
		logger.info("====== into caicai page controller ====="+role);
		UserInfo userInfo;
		userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		model.addAttribute("userInfoModel", userInfo);
		if (role == null || role.equals("")) {
			role = "user";
		}
		model.addAttribute("active", role);
		Page<User> page = userService.findUserByRole(role, pageNumber, pageSize);
		model.addAttribute("page", page);
		return "admin.caicai.detail";
	}
}
