package com.knet51.courses.controllers.technology;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.technology.Technology;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.technology.TechnologyService;

@Controller
public class TechnologyController {
	@Autowired
	private TechnologyService techService;
	
	@RequestMapping(value="/technology/list")
	public String showTechnologPage(Model model,HttpSession session,@RequestParam(value="pageNumber", defaultValue= "0") int pageNumber, 
			@RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<Technology> page = techService.findAllByStatus(pageNumber, pageSize, GlobalDefs.PASS);
		model.addAttribute("page", page);
		return "technology.list";
	}
}
