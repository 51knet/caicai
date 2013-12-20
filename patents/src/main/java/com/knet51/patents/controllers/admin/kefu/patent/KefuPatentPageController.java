package com.knet51.patents.controllers.admin.kefu.patent;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.patent.PatentFieldService;
import com.knet51.patents.jpa.services.patent.PatentService;
import com.knet51.patents.jpa.services.patent.PatentTypeService;
@Controller
public class KefuPatentPageController {
	private static final Logger logger = LoggerFactory.getLogger(KefuPatentPageController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private PatentService patentService;
	@Autowired
	private PatentTypeService patentTypeService;
	@Autowired
	private PatentFieldService patentFieldService;
	
	@RequestMapping(value="/admin/kefu/patent/list/{status}")
	public String showAllPatent(@PathVariable String status,Model model, HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		Page<Patent> page = null;
		if(status.equals("all")){
			page = patentService.findPatent(pageNumber, pageSize);
		}else if(status.equals("china")){
			page = patentService.findPatentByCountry(pageNumber, pageSize, GlobalDefs.PATENT_CHINA);
		}else if(status.equals("foreign")){
			page = patentService.findPatentByCountry(pageNumber, pageSize, GlobalDefs.PATENT_FOREIGN);
		}else if(status.equals("pass")){
			page = patentService.findPatentByStatus(pageNumber, pageSize, GlobalDefs.PATENT_PASS);
		}else if(status.equals("waite")){
			page = patentService.findPatentByStatus(pageNumber, pageSize, GlobalDefs.PATENT_WAITE);
		}
		model.addAttribute("page", page);
		return "admin.kefu.patent.list";
	}
	

	

	@RequestMapping(value="/admin/kefu/patent/view")
	public String showPatentDetail(Model model,HttpSession session, @RequestParam(value = "id") String patentNum){
		Patent patent = patentService.findOne(patentNum);
		model.addAttribute("patent", patent);
		return "admin.kefu.patent.view";
	}
	
	@RequestMapping(value="/admin/kefu/patent/focus/change")
	public @ResponseBody boolean changePatentFocus(@RequestParam("patentNum") String patentNum, @RequestParam("focus") Integer focus){
		boolean flag = false;
		Patent patent = patentService.findOne(patentNum);
		if(patent!= null && focus!= null){
			if(focus.equals(GlobalDefs.PATENT_HOME_FOCUS)){
				patent.setFocus(GlobalDefs.PATENT_HOME_FOCUS_NOT);
			}else{
				patent.setFocus(GlobalDefs.PATENT_HOME_FOCUS);
			}
		}
		Patent newPatent = patentService.update(patent);
		if(newPatent != null){
			flag = true;
		}
		return flag;
	}
	
	@RequestMapping(value="/admin/kefu/patent/status/change")
	public @ResponseBody boolean changePatentStatus(@RequestParam("patentNum") String patentNum, @RequestParam("status") Integer status){
		boolean flag = false;
		Patent patent = patentService.findOne(patentNum);
		if(patent!= null && status!= null){
			if(status.equals(GlobalDefs.PATENT_PASS)){
				patent.setStatus(GlobalDefs.PATENT_WAITE);
			}else{
				patent.setStatus(GlobalDefs.PATENT_PASS);
			}
		}
		Patent newPatent = patentService.update(patent);
		
		if(newPatent != null){
			flag = true;
		}
		return flag;
	}
	

}
