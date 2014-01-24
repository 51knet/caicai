package com.knet51.patents.controllers.admin.kefu.technology;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.knet51.ccweb.jpa.entities.technology.Technology;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.technology.TechnologyService;


@Controller
public class KefuTechnologyPageController {
	
	@Autowired
	private TechnologyService technologyService;
	
	@RequestMapping(value="/admin/kefu/technology/list/{status}")
	public String showAlltechnology(@PathVariable String status,Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<Technology> page = null;
		if(status.equals("all")){
			page = technologyService.findAll(pageNumber, pageSize);
		}else if(status.equals("pass")){
			page = technologyService.findAllByStatus(pageNumber, pageSize, GlobalDefs.TECH_PASS);
		}else if(status.equals("waite")){
			page = technologyService.findAllByStatus(pageNumber, pageSize, GlobalDefs.TECH_WAITE);
		}else if(status.equals("focus")){
			page = technologyService.findAllByFocus(pageNumber, pageSize, GlobalDefs.PATENT_HOME_FOCUS);
		}
		model.addAttribute("page", page);
		return "admin.kefu.technology.list";
	}
	

	

	@RequestMapping(value="/admin/kefu/technology/view/{tech_id}")
	public String showtechnologyDetail(Model model,HttpSession session, @PathVariable Long tech_id){
		Technology technology = technologyService.findOne(tech_id);
		model.addAttribute("technology", technology);
		return "admin.kefu.technology.view";
	}
	
	@RequestMapping(value="/admin/kefu/technology/focus/change" ,method=RequestMethod.POST)
	public @ResponseBody boolean changeTechnologyFocus(@RequestParam("tech_id") Long tech_id){
		System.out.println("====== technology focus change ======"+tech_id);
		boolean flag = false;
		Technology technology = technologyService.findOne(tech_id);
		if(technology!= null ){
			if(technology.getFocus().equals(GlobalDefs.TECH_HOME_FOCUS)){
				technology.setFocus(GlobalDefs.TECH_HOME_FOCUS_NOT);
			}else{
				technology.setFocus(GlobalDefs.TECH_HOME_FOCUS);
			}
		}
		Technology newtechnology = technologyService.update(technology);
		if(newtechnology != null){
			flag = true;
		}
		return flag;
	}
	
	@RequestMapping(value="/admin/kefu/technology/status/change",method=RequestMethod.POST)
	public @ResponseBody boolean changeTechnologyStatus(@RequestParam("tech_id") Long tech_id){
		boolean flag = false;
		Technology technology = technologyService.findOne(tech_id);
		if(technology!= null ){
			if(technology.getStatus().equals(GlobalDefs.TECH_PASS)){
				technology.setStatus(GlobalDefs.TECH_WAITE);
			}else{
				technology.setStatus(GlobalDefs.TECH_PASS);
			}
		}
		Technology newtechnology = technologyService.update(technology);
		
		if(newtechnology != null){
			flag = true;
		}
		return flag;
	}
	
//	@RequestMapping(value="/admin/kefu/search/technology", method = RequestMethod.GET)
//	public String searchTechnology(Model model,HttpSession session,@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
//			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,@RequestParam("searchParam") String searchParam) throws Exception{
//		searchParam = new String(searchParam.getBytes("iso-8859-1"), "utf-8").trim();
//		String newsearchParam = MyUtil.replaceSpace(searchParam);
//		Page<Technology> page = technologyService.findtechnologyBytechnologyNameLike(pageNumber, pageSize, newsearchParam);
//	
//		model.addAttribute("page", page);
//		model.addAttribute("searchParam", searchParam);
//		return "admin.kefu.technology.list";
//	}
}
