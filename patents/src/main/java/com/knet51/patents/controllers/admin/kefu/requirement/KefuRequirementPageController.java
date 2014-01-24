package com.knet51.patents.controllers.admin.kefu.requirement;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.jpa.entities.PatentRequirement;
import com.knet51.ccweb.jpa.entities.Requirement;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.requirement.PatentRequirementService;
import com.knet51.patents.jpa.services.requirement.RequirementService;

@Controller
public class KefuRequirementPageController {
	
	@Autowired
	private RequirementService requirementService;
	@Autowired
	private PatentRequirementService patentRequirementService;
	
	@RequestMapping(value="/admin/kefu/requirement/list/{status}")
	public String showAllRequirement(@PathVariable String status,Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<Requirement> page = null;
		if(status.equals("pass")){
			page = requirementService.findRequireByStatus(pageNumber, pageSize, GlobalDefs.PATENT_PASS);
		}else if(status.equals("waite")){
			page = requirementService.findRequireByStatus(pageNumber, pageSize, GlobalDefs.PATENT_WAITE);
		}else{
			page = requirementService.findRequireAll(pageNumber, pageSize);
		}
		model.addAttribute("page", page);
		return "admin.kefu.requirement.list";
	}


	@RequestMapping(value="/admin/kefu/requirement/view/{require_id}")
	public String showRequirementDetail(Model model,HttpSession session, @PathVariable Long require_id){
		Requirement requirement = requirementService.findOne(require_id);
		model.addAttribute("requirement", requirement);
		return "admin.kefu.requirement.view";
	}
	
	
	@RequestMapping(value="/admin/kefu/requirement/status/change")
	public @ResponseBody boolean changeRequirementStatus(@RequestParam("id") Long require_id, @RequestParam("status") Integer status){
		boolean flag = false;
		Requirement requirement = requirementService.findOne(require_id);
		if(requirement!= null && status!= null){
			if(status.equals(GlobalDefs.PATENT_PASS)){
				requirement.setStatus(GlobalDefs.REQUIREMENT_WAITE);
			}else{
				requirement.setStatus(GlobalDefs.REQUIREMENT_PASS);
			}
		}
		Requirement newRequirement = requirementService.update(requirement);
		
		if(newRequirement != null){
			flag = true;
		}
		return flag;
	}
	
	@RequestMapping(value="/admin/kefu/patentrequirement/list/{status}")
	public String showAllPatentRe(@PathVariable String status,Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<PatentRequirement> page = null;
		if(status.equals("pass")){
			page = patentRequirementService.findAllByStatus(pageNumber, pageSize, GlobalDefs.REQUIREMENT_PASS);
		}else if(status.equals("waite")){
			page = patentRequirementService.findAllByStatus(pageNumber, pageSize, GlobalDefs.REQUIREMENT_WAITE);
		}else{
			page = patentRequirementService.findAll(pageNumber, pageSize);
		}
		model.addAttribute("page", page);
		return "admin.kefu.patentRequirement.list";
	}


	@RequestMapping(value="/admin/kefu/patentrequirement/view/{require_id}")
	public String showPatentReDetail(Model model,HttpSession session, @PathVariable Long require_id){
		PatentRequirement patentRequirement = patentRequirementService.findOne(require_id);
		model.addAttribute("patentRequirement", patentRequirement);
		return "admin.kefu.patentRequirement.view";
	}
	
	
	@RequestMapping(value="/admin/kefu/patentrequirement/status/change")
	public @ResponseBody boolean changePatentRequirementStatus(@RequestParam("pre_id") Long require_id){
		boolean flag = false;
		PatentRequirement patentRequirement = patentRequirementService.findOne(require_id);
		if(patentRequirement!= null ){
			if(patentRequirement.getStatus().equals(GlobalDefs.PATENT_PASS)){
				patentRequirement.setStatus(GlobalDefs.PATENT_WAITE);
			}else{
				patentRequirement.setStatus(GlobalDefs.PATENT_PASS);
			}
		}
		PatentRequirement newRequirement = patentRequirementService.update(patentRequirement);
		
		if(newRequirement != null){
			flag = true;
		}
		return flag;
	}
	
}
