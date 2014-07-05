package com.graphene.web.controller.admin.kefu.patent;



import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.graphene.web.jpa.entity.patent.Patent;
import com.graphene.web.service.UserService;
import com.graphene.web.service.patent.PatentFieldService;
import com.graphene.web.service.patent.PatentService;
import com.graphene.web.service.patent.PatentTypeService;
import com.graphene.web.util.MyUtil;


@Controller
public class KefuPatentController {
	private static final Logger logger = LoggerFactory.getLogger(KefuPatentController.class);
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
			page = patentService.findPatentByCountryAndStatus(pageNumber, pageSize,GlobalDefs.PASS, GlobalDefs.CHINA);
		}else if(status.equals("foreign")){
			page = patentService.findPatentByCountryAndStatus(pageNumber, pageSize, GlobalDefs.PASS,GlobalDefs.FOREIGN);
		}else if(status.equals("pass")){
			page = patentService.findPatentByStatus(pageNumber, pageSize, GlobalDefs.PASS);
		}else if(status.equals("waite")){
			page = patentService.findPatentByStatus(pageNumber, pageSize, GlobalDefs.WAITE);
		}else if(status.equals("focus")){
			page = patentService.findPatentByFocus(pageNumber, pageSize, GlobalDefs.HOME_FOCUS);
		}
		model.addAttribute("page", page);
		return "admin.kefu.patent.list";
	}
	

	

	@RequestMapping(value="/admin/kefu/patent/view/{patent_id}")
	public String showPatentDetail(Model model,HttpSession session,@PathVariable Long patent_id){
		Patent patent = patentService.findOne(patent_id);
		model.addAttribute("patent", patent);
		return "admin.kefu.patent.view";
	}
	
	@RequestMapping(value="/admin/kefu/patent/focus/change" ,method=RequestMethod.POST)
	public @ResponseBody boolean changePatentFocus(@RequestParam("patent_id") Long patent_id){
		boolean flag = false;
		Patent patent = patentService.findOne(patent_id);
		if(patent!= null ){
			if(patent.getFocus().equals(GlobalDefs.HOME_FOCUS)){
				patent.setFocus(GlobalDefs.HOME_FOCUS_NOT);
			}else{
				patent.setFocus(GlobalDefs.HOME_FOCUS);
			}
		}
		Patent newPatent = patentService.update(patent);
		if(newPatent != null){
			flag = true;
		}
		return flag;
	}
	
	@RequestMapping(value="/admin/kefu/patent/status/change",method=RequestMethod.POST)
	public @ResponseBody boolean changePatentStatus(@RequestParam("patent_id") Long patent_id){
		boolean flag = false;
		Patent patent = patentService.findOne(patent_id);
		if(patent!= null ){
			if(patent.getStatus().equals(GlobalDefs.PASS)){
				patent.setStatus(GlobalDefs.WAITE);
			}else{
				patent.setStatus(GlobalDefs.PASS);
			}
		}
		Patent newPatent = patentService.update(patent);
		
		if(newPatent != null){
			flag = true;
		}
		return flag;
	}
	
	@RequestMapping(value="/admin/kefu/search/patent", method = RequestMethod.GET)
	public String searchPatent(Model model,HttpSession session,@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,@RequestParam("searchParam") String searchParam) throws Exception{
		searchParam = new String(searchParam.getBytes("iso-8859-1"), "utf-8").trim();
		String newsearchParam = MyUtil.replaceTheSpace(searchParam);
		Page<Patent> page = patentService.findPatentByPatentNameLike(pageNumber, pageSize, newsearchParam);
	
		model.addAttribute("page", page);
		model.addAttribute("searchParam", searchParam);
		return "admin.kefu.patent.list";
	}
	

}
