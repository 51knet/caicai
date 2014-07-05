package com.graphene.web.controller.admin.kefu.technology;

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

import com.graphene.web.common.defs.GlobalDefs;
import com.graphene.web.jpa.entity.tech.Technology;
import com.graphene.web.service.tech.TechnologyService;





@Controller
public class KefuTechnologyController {
	
	@Autowired
	private TechnologyService technologyService;
	
	@RequestMapping(value="/admin/kefu/technology/list/{status}")
	public String showAlltechnology(@PathVariable String status,Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<Technology> page = null;
		if(status.equals("all")){
			page = technologyService.findAll(pageNumber, pageSize);
		}else if(status.equals("pass")){
			page = technologyService.findAllByStatus(pageNumber, pageSize, GlobalDefs.PASS);
		}else if(status.equals("waite")){
			page = technologyService.findAllByStatus(pageNumber, pageSize, GlobalDefs.WAITE);
		}else if(status.equals("focus")){
			page = technologyService.findAllByFocus(pageNumber, pageSize, GlobalDefs.HOME_FOCUS);
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
			if(technology.getFocus().equals(GlobalDefs.HOME_FOCUS)){
				technology.setFocus(GlobalDefs.HOME_FOCUS_NOT);
			}else{
				technology.setFocus(GlobalDefs.HOME_FOCUS);
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
			if(technology.getStatus().equals(GlobalDefs.PASS)){
				technology.setStatus(GlobalDefs.WAITE);
			}else{
				technology.setStatus(GlobalDefs.PASS);
			}
		}
		Technology newtechnology = technologyService.update(technology);
		
		if(newtechnology != null){
			flag = true;
		}
		return flag;
	}
	
}
