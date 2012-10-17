package com.knet51.ccweb.controllers.teacher.announcement;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.AnnouncementService;

@Controller
public class TeacherAnnoInfoPageController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherAnnoInfoPageController.class); 
	@Autowired
	private AnnouncementService annoService;
	
	@RequestMapping(value="/admin/teacher/announcement/detail")
	public String teacherAnno(HttpSession session,Model m){
		logger.info("#### into TeacherAnno ####");
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			User user = userInfo.getUser();
			List<Announcement> list = annoService.findAllByUid(user.getId());
			m.addAttribute("list", list);
			return "admin.teacher.basic";
			//return "teacherAnnoPage";
	}
	@RequestMapping(value="/admin/teacher/announcement/add")
	public String teacherAnnoAdd(){
		logger.info("#### into TeacherAnnoAddPage ####");
		//return "teacherAnnoPage";
		return "admin.teacher.annoAddPage";
	}
}
