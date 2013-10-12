package com.knet51.ccweb.controllers.common;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.Course;
import com.knet51.ccweb.jpa.services.CourseService;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class CommonController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="/searchUser",method=RequestMethod.POST)
	public String findUserByName(@RequestParam("searchParam") String searchParam,@RequestParam("types") String types,Model model){
			List<User> userList = userService.findAllUsers();
			List<Course> courseList = courseService.findAllPublish();
			if(types.equals("courses")){
				List<Course> newCourseList = new ArrayList<Course>();
				if(searchParam.trim() != null ){
					for(int i=0;i<courseList.size();i++){
						if(courseList.get(i).getCourseName().contains(searchParam) ){
							newCourseList.add(courseList.get(i));
						}
					}
					model.addAttribute("courseList", newCourseList);
				}else{
					model.addAttribute("courseList", courseList);
				}
			}else if(types.equals("users")){
				List<User> newUserList = new ArrayList<User>();
				if(searchParam.trim() != null ){
					for(int i=0;i<userList.size();i++){
						if(userList.get(i).getForbidden() == null && userList.get(i).getName()!=null 
								&& userList.get(i).getName().contains(searchParam) ){
							newUserList.add(userList.get(i));
						}
					}
					model.addAttribute("userList", newUserList);
				}else{
					model.addAttribute("userList", userList);
				}
			}else{
				model.addAttribute("courseList", courseList);
				model.addAttribute("userList", userList);
			}
			model.addAttribute("searchParam", searchParam);
			model.addAttribute("types", types);
		
		return "user.search.list";
	}
}
