package com.knet51.courses.controllers.teacher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
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

import com.knet51.courses.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.EduBackground;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.courses.jpa.services.TeacherService;
import com.knet51.courses.jpa.services.UserService;
import com.knet51.courses.jpa.services.achievement.EduBackgroundService;
import com.knet51.courses.jpa.services.annoncement.AnnouncementService;
import com.knet51.courses.jpa.services.patent.PatentService;

@Controller
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;
	@Autowired
	private AnnouncementService annoService;
	@Autowired
	private EduBackgroundService eduBackgroundService;
	@Autowired
	private PatentService patentService;
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
	/**
	 * show all teacher
	 * @param session
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/teacher/list")
	public String showAllTeacher(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		logger.info("==== into teacher list controller =====");
		Page<Teacher> teacherPage = null;
		List<Teacher> teacherList = null;
		teacherPage = teacherService.findAllTeacher(pageNumber, pageSize);
		//List<Teacher> teacher = teacherPage.getContent();
		model.addAttribute("page", teacherPage);
		model.addAttribute("teacherList", teacherList);

		return "teacher.list";
	}
	
	/**
	 * show teacher's courses by teacher_id
	 * @param teacher_id
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/teacher/{teacher_id}")
	public String showTeacherInfoById(@PathVariable Long teacher_id,Model model,HttpSession session) throws ServletException, IOException{
//		List<String> courseTypeList = courseService.getCourseTypeByTeacherId(teacher_id);
//		List<Course> teacherCourseList=courseService.getAllCourseByTeacherId(teacher_id);
//		model.addAttribute("teacherCourseList", teacherCourseList);
//		model.addAttribute("teacher", teacher);
//		model.addAttribute("courseCount", teacherCourseList.size());
//		model.addAttribute("courseTypeList", courseTypeList);
//		return "teacher.teacherInfo";
	//	HttpServletResponse resp = ((HttpServletResponse)response);
	//	resp.sendRedirect("/patents/teacher/"+teacher_id);
	//	return;
		logger.info("#### Into teacher front page ####");
		User user = userService.findOne(teacher_id);
		if (user.getRole().equals("teacher")) {
			Teacher teacher=teacherService.findOne(teacher_id);
		
			Page<Patent> page = patentService.findPatentByUser(0, 4, user);
			model.addAttribute("patentPage", page);

			Page<Announcement> annoPage = annoService.findAllAnnoByUser(0, 4, user);
			model.addAttribute("annolist", annoPage.getContent());
			model.addAttribute("annoCount", annoPage.getContent().size());
			
			List<EduBackground> eduInfo = eduBackgroundService.findEduListByTeacherId(teacher_id);
			model.addAttribute("eduInfo", eduInfo);
			model.addAttribute("eduCount", eduInfo.size());
			
			UserInfo userInfo = new UserInfo(user);
			userInfo.setTeacher(teacher);

			model.addAttribute("teacher_id", teacher_id);
			model.addAttribute("teacherInfo", userInfo);
			model.addAttribute("role", userInfo.getTeacherRole());
			
			return "teacher.basic";
		} else {
			return "redirect:/teacher/list";
		}
		
	}
	
	
}
