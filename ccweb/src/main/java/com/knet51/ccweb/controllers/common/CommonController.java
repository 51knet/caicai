package com.knet51.ccweb.controllers.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.courses.Course;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;
import com.knet51.ccweb.jpa.entities.courses.UserCourse;
import com.knet51.ccweb.jpa.services.CourseResourceService;
import com.knet51.ccweb.jpa.services.CourseService;
import com.knet51.ccweb.jpa.services.UserCourseService;

@Controller
public class CommonController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private UserCourseService userCourseService;
	@Autowired
	private CourseResourceService courseResourceService;
	
	/**
	 * show pay view
	 * @param course_id
	 * @return
	 */
	@RequestMapping(value="/course/pay/view/{course_id}")
	public String showPayPage(@PathVariable Long course_id){
		
		return "course.pay.view";
	}
	
	/**
	 * buy the course in enterprise front page
	 * @param course_id
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/course/study/buy/{course_id}")
	public String BuyCourseDetail(@PathVariable Long course_id,Model model,HttpSession session){
		Course course = courseService.findOneById(course_id);
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (userInfo == null) {
			return "redirect:/admin";
		}
		if(course.getPrice().intValue()>0){
			return "redirect:/course/pay/view/"+course_id;
		}
		UserCourse userCourse = userCourseService
				.findByTeachercourseidAndUserid(course_id, userInfo.getId());
		if (userCourse==null) {
			userCourse = new UserCourse();
			userCourse.setTeachercourseid(course_id);
			userCourse.setUserid(userInfo.getId());
			userCourseService.save(userCourse);
		}
		List<CourseResource> listResource = courseResourceService
				.getAllCourseResourceByCourseIdAndStatus(course_id,
						GlobalDefs.STATUS_COURSE_RESOURCE);
		List<CourseResource> courseList = new ArrayList<CourseResource>();
		Map<Integer, List<CourseResource>> courseMap = new TreeMap<Integer, List<CourseResource>>();
		int resourceOrder = 0;
		for (CourseResource courseResource : listResource) {
			resourceOrder = courseResource.getLessonNum();
			courseList = courseResourceService
					.getResourceByLessonNumAndCourseId(resourceOrder, course_id);
			courseMap.put(resourceOrder, courseList);
		}
		model.addAttribute("courseMap", courseMap);
		model.addAttribute("course", course);
		model.addAttribute("resourceCount", listResource.size());
		return "course.study.view";
	}
	

}
