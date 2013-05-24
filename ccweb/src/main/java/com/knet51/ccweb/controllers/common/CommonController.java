package com.knet51.ccweb.controllers.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

	public CommonController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="/course/pay/view/{course_id}")
	public String showPayPage(@PathVariable Long course_id){
		
		return "course.pay.view";
	}

}
