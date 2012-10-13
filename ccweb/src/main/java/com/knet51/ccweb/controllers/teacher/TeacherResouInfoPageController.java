package com.knet51.ccweb.controllers.teacher;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Resource;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.ResourceService;

@Controller
public class TeacherResouInfoPageController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherResouInfoPageController.class);
	@Autowired
	private ResourceService service;
	
	@RequestMapping(value="/teacherResou")
	public String teacherResouInfo(HttpSession session,Model m ){
		logger.info("#####Into TeacherResouInfoPageController#####");
		UserInfo userInfo = (UserInfo) session.getAttribute("user");
		User user = userInfo.getUser();
		List<Resource> list = service.listAllByUid(user.getId());
		m.addAttribute("list", list);
		return "teacherResouPage";
	}
	@RequestMapping(value="/teacherResouAdd")
	public String teacherResouAdd(HttpSession session,Model m ){
		logger.info("#####Into TeacherResouInfoAdd#####");
		return "teacherResouAddPage";
	}
	//捕获超出异常之后跳转的view
	/*@ExceptionHandler(Exception.class)
	public String handleException(Exception e,HttpServletRequest request ){
		logger.info("#### Into MaxUploadExceptionHandler ####");
		if(e instanceof org.springframework.web.multipart.MaxUploadSizeExceededException){
			request.setAttribute("error", "文件超出长度");
		}
		return "teacherResouAddPage";
	}*/

}