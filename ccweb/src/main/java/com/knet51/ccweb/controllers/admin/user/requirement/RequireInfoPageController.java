package com.knet51.ccweb.controllers.admin.user.requirement;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Requirement;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.requirement.RequirTypeService;
import com.knet51.ccweb.jpa.services.requirement.RequirementService;

@Controller
public class RequireInfoPageController {
	@Autowired
	private RequirementService requirementService;
	@Autowired
	private RequirTypeService requirTypeService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/admin/requirement/list")
	public String showRequireList(HttpSession session,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		Page<Requirement> page = requirementService.findRequireByUser(pageNumber, pageSize, user);
		model.addAttribute("page", page);
		return "admin."+user.getRole()+".requirement.list";
	}

}
