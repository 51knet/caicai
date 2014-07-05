package com.graphene.web.controller.admin.applyright;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.graphene.web.common.beans.UserInfo;
import com.graphene.web.common.defs.GlobalDefs;
import com.graphene.web.jpa.entity.applyright.AlliesApplyRight;
import com.graphene.web.jpa.entity.applyright.ParkApplyRight;
import com.graphene.web.service.UserService;
import com.graphene.web.service.applyright.AlliesRightService;
import com.graphene.web.service.applyright.ParkRightService;
import com.graphene.web.util.MyUtil;
import com.graphene.web.util.ajax.AjaxValidationEngine;
import com.graphene.web.util.ajax.ValidationResponse;
import com.graphene.web.util.fileUpload.FileUtil;



@Controller
public class ApplyRightController {
	private static final Logger logger = LoggerFactory.getLogger(ApplyRightController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private ParkRightService parkRightService;
	@Autowired
	private AlliesRightService allieRightService;
	
	@RequestMapping(value="/admin/applyright/new")
	public String showAddapplyrightPage(HttpSession session, Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		model.addAttribute("active", "allies");
		List<String> universityList = new ArrayList<String>();
		String universityFilePath = "";
		BufferedReader br;
		universityFilePath = session.getServletContext().getRealPath("/");
		universityFilePath += "resources\\university\\universities.property";
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					universityFilePath), "utf-8"));
			String data = ""; 
			while ((data = br.readLine()) != null) {
				universityList.add(data);
			}
			br.close();
		} catch (FileNotFoundException e) {
			universityList.add(" ");
		} catch (IOException e) {
			universityList.add(" ");
		}
		model.addAttribute("universityList", universityList);
		return "admin."+userInfo.getRole()+".applyright.add";
	}
	
	/* person applyright */
	
	@RequestMapping(value="/admin/applyright/allies/add", method = RequestMethod.POST)
	public String addAllieApplyRight(@Valid AlliesRightForm allieRightForm, 
			BindingResult validResult,HttpSession session,MultipartHttpServletRequest request) throws Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		logger.info("===== into person valid controller=====");
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/applyright/new";
		}else{
			AlliesApplyRight applyright = new AlliesApplyRight();
			MyUtil.copyValidBeanToDestBean(allieRightForm, applyright);
			applyright.setDate(new Date());
			applyright.setUser(userInfo.getUser());
			applyright.setStatus(GlobalDefs.WAITE);
			applyright = allieRightService.create(applyright);
			MultipartFile file = request.getFile("myfiles");
			if(!file.isEmpty()){
				String fileName = file.getOriginalFilename();
				String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/applyright/allies";
				FileUtil.getInstance().createRealPath(path, session);
				File saveDest = new File(path + File.separator + fileName);
				file.transferTo(saveDest);
				String savePath = "/resources/attached/"+userInfo.getId()+"/applyright/allies/"+fileName;
				applyright.setAlliesFile(savePath);
				allieRightService.update(applyright);
				return "redirect:/upload/success";
			}else{
				return "redirect:/upload/failed";
			}	
		}
	}
	
	@RequestMapping(value = "/admin/applyright/alliesValidInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse AlliesvalidFormAjaxJson(@Valid AlliesRightForm alliesForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value="/admin/applyright/park/add", method = RequestMethod.POST)
	public String addParkApplyRight(@Valid ParkRightForm parkRightForm, BindingResult validResult,HttpSession session
			,MultipartHttpServletRequest request) throws Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		boolean flag = false;
		logger.info("===== into person valid controller=====");
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/applyright/new";
		}else{
			ParkApplyRight applyright = new ParkApplyRight();
			MyUtil.copyValidBeanToDestBean(parkRightForm, applyright);
			applyright.setDate(new Date());
			applyright.setUser(userInfo.getUser());
			applyright.setStatus(GlobalDefs.WAITE);
			applyright = parkRightService.create(applyright);		
			return "redirect:/upload/success";		
		}
	}
	
	@RequestMapping(value = "/admin/applyright/parkValidInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse parkValidFormAjaxJson(@Valid ParkRightForm parkRightForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	
	@RequestMapping(value="/upload/{status}")
	public String uploadStatus(@PathVariable String status){
		return "upload."+status;
	}
}
