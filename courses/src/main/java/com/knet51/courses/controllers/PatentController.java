package com.knet51.courses.controllers;

import java.io.UnsupportedEncodingException;
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
import com.knet51.courses.jpa.services.patent.PatentFieldService;
import com.knet51.courses.jpa.services.patent.PatentService;

@Controller
public class PatentController {
	@Autowired
	private PatentService patentService;
	@Autowired
	private PatentFieldService patentFieldService;
	
	@RequestMapping(value="/test/search")
	public String testSearch(){
		return "test.search";
	}
	
	@RequestMapping(value="/test/search/patent", method = RequestMethod.GET)
	public String testSearchPatent(Model model,HttpSession session,@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "2") int pageSize,@RequestParam("types") String types,@RequestParam("searchParam") String searchParam){
		
//		List<String> typeList = new ArrayList<String>();
//		typeList.add("patentNum");
//		typeList.add("patentName");
//		typeList.add("inventer");
//		for (String type : typeList) {
//			if(type.equals(types)){
//				type = searchParam;
//			}else{
//				type="%";
//			}
//			logger.info("=======type="+type);
//		}
		Page<Patent> page = patentService.searchPatent(pageNumber, pageSize, 1L, types, searchParam);
		//Page<Patent> page= patentService.findPatentPage(pageNumber, pageSize, searchParam, searchParam, searchParam);
		//Page<Patent> page = patentService.findPatentByPatentNumAndPatentNameAndInventer(pageNumber, pageSize, searchParam, searchParam, searchParam);
		model.addAttribute("page", page);
		List<PatentField> fieldList = patentFieldService.findAll();
		model.addAttribute("fieldList", fieldList);
		model.addAttribute("searchParam", searchParam);
		return "patent.search.list";
	}
	
	@RequestMapping(value="/test/patent/list")
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
	
	@RequestMapping(value="/test/patent/{patentField}/list")
	public String patentTypeFilter(@PathVariable String patentField,Model model, HttpSession session,@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "2") int pageSize) throws Exception{
		patentField = new String(patentField.getBytes("iso-8859-1"), "utf-8");
		Page<Patent> page = patentService.findPatentByPatentField(pageNumber, pageSize, patentField);
		List<PatentField> fieldList = patentFieldService.findAll();
		
		model.addAttribute("fieldList", fieldList);
		model.addAttribute("page", page);
		return "patent.list";
	}
}
