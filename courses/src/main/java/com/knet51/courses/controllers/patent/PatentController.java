package com.knet51.courses.controllers.patent;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.entities.patent.PatentField;
import com.knet51.ccweb.jpa.entities.patent.PatentType;
import com.knet51.courses.jpa.services.patent.PatentFieldService;
import com.knet51.courses.jpa.services.patent.PatentService;
import com.knet51.courses.jpa.services.patent.PatentTypeService;
import com.knet51.courses.util.MyUtil;

@Controller
public class PatentController {
	@Autowired
	private PatentService patentService;
	@Autowired
	private PatentFieldService patentFieldService;
	@Autowired
	private PatentTypeService patentTypeService;
	
	@RequestMapping(value="/test/search")
	public String testSearch(){
		return "test.search";
	}
	
	@RequestMapping(value="/search/{patent}", method = RequestMethod.GET)
	public String testSearchPatent(@PathVariable String patent,Model model,HttpSession session,@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "2") int pageSize,@RequestParam("patentType")Long patentType_id,@RequestParam("types") String types,@RequestParam("searchParam") String searchParam){
		String newsearchParam = MyUtil.getInstance().replaceSpace(searchParam);
		List<PatentType> patentTypeList = patentTypeService.findAllPatentType();
		model.addAttribute("patentTypeList", patentTypeList);
		PatentType patentType = patentTypeService.findOne(patentType_id);
		Page<Patent> page = patentService.searchPatent(pageNumber, pageSize, patentType, types, newsearchParam);
		model.addAttribute("page", page);
		List<PatentField> fieldList = patentFieldService.findAll();
		model.addAttribute("fieldList", fieldList);
		model.addAttribute("searchParam", searchParam);
		model.addAttribute("active", patent);
		return "patent.search.list";
	}
	
	@RequestMapping(value="/patent/list")
	public String patentList(Model model, HttpSession session,@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "2") int pageSize){
		List<Patent> patentList = patentService.findPatentList();
		Page<Patent> page = patentService.findAll(pageNumber, pageSize);
		List<PatentField> fieldList = patentFieldService.findAll();
		model.addAttribute("page", page);
		model.addAttribute("patentCount", patentList.size());
		model.addAttribute("fieldList", fieldList);
		return "patent.list";
	}
	
	@RequestMapping(value="/patent/{patentField}/list")
	public String patentTypeFilter(@PathVariable String patentField,Model model, HttpSession session,@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "2") int pageSize) throws Exception{
		patentField = new String(patentField.getBytes("iso-8859-1"), "utf-8").trim();
		Page<Patent> page = null;
		List<Patent> patentList = null;
		if(patentField.equals("all")){
			page = patentService.findAll(pageNumber, pageSize);
			patentList = patentService.findPatentList();
		}else{
			page = patentService.findPatentByPatentField(pageNumber, pageSize, patentField);
			patentList = patentService.findPatentByPatentListField(patentField);
		}
		 
		List<PatentField> fieldList = patentFieldService.findAll();
		
		model.addAttribute("fieldList", fieldList);
		model.addAttribute("page", page);
		model.addAttribute("patentCount", patentList.size());
		model.addAttribute("patentField", patentField);
		return "patent.list";
	}
	
	@RequestMapping(value="/patent/view/{patentNum}")
	public String showPatentDetail(@PathVariable String patentNum,Model model){
		Patent patent = patentService.findOne(patentNum);
		model.addAttribute("patent", patent);
		return "patent.view";
	}
}
