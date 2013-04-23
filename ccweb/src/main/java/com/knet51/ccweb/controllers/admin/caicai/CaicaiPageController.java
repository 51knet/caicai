package com.knet51.ccweb.controllers.admin.caicai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CaicaiPageController {
	
	@RequestMapping(value="/admin/caicai")
	public String showSuperAdmin(){
		return "admin.caicai.detail";
	}
}
