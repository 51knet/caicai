package com.knet51.courses.controllers.technology;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.technology.Technology;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.controllers.login.LoginForm;
import com.knet51.courses.jpa.services.UserService;
import com.knet51.courses.jpa.services.technology.TechnologyService;

@Controller
public class TechnologyController {
	@Autowired
	private TechnologyService techService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/technology/list")
	public String showTechnologPage(Model model,HttpSession session,@RequestParam(value="pageNumber", defaultValue= "0") int pageNumber, 
			@RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<Technology> page = techService.findAllByStatus(pageNumber, pageSize, GlobalDefs.PASS);
		model.addAttribute("page", page);
		return "technology.list";
	}
	
	@RequestMapping(value="/technology/view/{tech_id}")
	public String showTechnologyView(Model model,@PathVariable Long tech_id){
		Technology technology = techService.findOne(tech_id);
		model.addAttribute("technology", technology);
		return "technology.view";
	}
	
	@RequestMapping(value = "/technology/view/checkLogin", method = RequestMethod.POST)
	public @ResponseBody String checkEmailAndPsw(HttpServletResponse response,
			LoginForm loginForm) throws Exception {
		String email = loginForm.getEmail();
		String pwd = loginForm.getPassword();

		User user = null;
		boolean value = false;
		value = userService.login(email, pwd);
		user = userService.getValidUser(email, pwd);
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
