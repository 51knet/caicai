package com.knet51.courses.controllers.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.courses.beans.UserInfo;
import com.knet51.courses.jpa.services.TeacherService;
import com.knet51.courses.jpa.services.UserService;
import com.knet51.courses.jpa.services.patent.PatentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeacherPatentInfoFrontPageController {
	@Autowired
	private PatentService userPatentService;
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value="/teacher/{teacher_id}/patent/list")
	public String annoList(@PathVariable Long teacher_id,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);

		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<Patent> page = userPatentService.findPatentByUser(pageNumber, pageSize, user);
		model.addAttribute("page", page);
		return user.getRole()+".patent.list";
	}
	
	@RequestMapping("/teacher/{teacher_id}/patent/view")
	public String showPatentDetailFrontPage(@PathVariable Long teacher_id,@RequestParam(value = "id") String patentNum,Model model){
		User user = userService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		Teacher teacher = teacherService.findOne(teacher_id);
		userInfo.setTeacher(teacher);
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		
		Patent patent = userPatentService.findOne(patentNum);
		model.addAttribute("patent", patent);
		return user.getRole()+".patent.detail";
	}
}
