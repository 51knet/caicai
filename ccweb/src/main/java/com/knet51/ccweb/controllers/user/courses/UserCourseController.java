package com.knet51.ccweb.controllers.user.courses;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.services.UserService;


@Controller
public class UserCourseController {
	
	public static final long MAX_FILE_SIZE_2M = 2*1024*1024;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/admin/user/course/list")
	public String userCourses(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		return "admin.user.course.list";
	}
}
