package com.knet51.ccweb.controllers.admin.user.courses;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.courses.Course;
import com.knet51.ccweb.jpa.entities.courses.UserCourse;
import com.knet51.ccweb.jpa.services.CourseService;
import com.knet51.ccweb.jpa.services.UserCourseService;
import com.knet51.ccweb.jpa.services.UserService;


@Controller
public class UserCourseController {
	
	public static final long MAX_FILE_SIZE_2M = 2*1024*1024;
	
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserCourseService userCourseService;
	
	
	@RequestMapping(value="/admin/user/course/list")
	public String userCourses(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<Course> userCourseList = new ArrayList<Course>();
		Page<UserCourse> mycourse = userCourseService.findByUserid(pageNumber, pageSize, userInfo.getId());
		for (int i = 0; i < mycourse.getContent().size(); i++) {
			Course course = courseService.findOneById(mycourse.getContent().get(i).getTeachercourseid());
			userCourseList.add(course);
		}
		model.addAttribute("courseList", userCourseList);
		model.addAttribute("page", mycourse);
		return "admin.user.course.list";
	}
}
