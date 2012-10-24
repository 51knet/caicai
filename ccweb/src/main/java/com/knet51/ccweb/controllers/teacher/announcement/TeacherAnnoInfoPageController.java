package com.knet51.ccweb.controllers.teacher.announcement;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class TeacherAnnoInfoPageController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherAnnoInfoPageController.class); 
	@Autowired
	private AnnouncementService annoService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/admin/teacher/announcement/detail")
	public String teacherAnno(HttpSession session,Model m ,@RequestParam(value="pageNumber",defaultValue="0") 
								int pageNumber, @RequestParam(value="pageSize", defaultValue="2") int pageSize){
		logger.info("#### into TeacherAnno ####");
			Long id = getId(session);
			User user = userService.findOne(id);
			Page<Announcement> page = annoService.findAllAnnoById(pageNumber, pageSize, user);
			List<Announcement> list = annoService.findAllByUid(id);
			m.addAttribute("page", page);
			m.addAttribute("list",list);
			return "admin.tacher.annoDetail";
	}
	
	@RequestMapping(value="/admin/teacher/announcement/add")
	public String teacherAnnoAdd(){
		logger.info("#### into TeacherAnnoAddPage ####");
		return "admin.teacher.annoAddPage";
	}
	
	@RequestMapping(value="/admin/teacher/announcement/detailOne")
	public String detailAnnoInfo(@RequestParam("id") Long id, Model m){
		//System.out.println(id);
		Announcement ann = annoService.findOneById(id);
		m.addAttribute("ann", ann);
		return "admin.teacher.detailAnnInfo";
	}
	
	public Long getId(HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Long id = userInfo.getUser().getId();
		return id;
	}
}
