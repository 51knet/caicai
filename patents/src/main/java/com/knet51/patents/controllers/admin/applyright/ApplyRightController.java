package com.knet51.patents.controllers.admin.applyright;

import java.io.InputStream;
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

import com.knet51.ccweb.jpa.entities.applyright.ApplyRight;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.applyright.ApplyRightService;
import com.knet51.patents.util.MyUtil;
import com.knet51.patents.util.ajax.AjaxValidationEngine;
import com.knet51.patents.util.ajax.ValidationResponse;
import com.knet51.patents.util.fileUpLoad.FTPUtil;
import com.knet51.patents.util.fileUpLoad.FileUtil;

@Controller
public class ApplyRightController {
	private static final Logger logger = LoggerFactory.getLogger(ApplyRightController.class);
	@Autowired
	private ApplyRightService validService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/admin/applyright/new")
	public String showAddapplyrightPage(HttpSession session, Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		model.addAttribute("active", "invest");
		return "admin."+userInfo.getRole()+".applyright.add";
	}
	
	
	@RequestMapping(value="/admin/applyright/person/add", method = RequestMethod.POST)
	public String addPersonApplyRight(@Valid PersonApplyRightForm applyrightForm, BindingResult validResult,HttpSession session
			,MultipartHttpServletRequest request) throws Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		boolean flag = false;
		logger.info("===== into invest valid controller=====");
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/applyright/new";
		}else{
			ApplyRight applyright = new ApplyRight();
			MyUtil.copyValidBeanToDestBean(applyrightForm, applyright);
			applyright.setDate(new Date());
			applyright.setUser(userInfo.getUser());
			applyright.setStatus(GlobalDefs.WAITE);
			applyright = validService.create(applyright);		
			MultipartFile multipartFile = request.getFile("myfiles");
			if(!multipartFile.isEmpty()){
				String fileName = multipartFile.getOriginalFilename();
				String path ="/resources/attached/"+userInfo.getId()+"/applyright";
				InputStream fileInput = multipartFile.getInputStream();
				flag =  FTPUtil.getInstance().uploadFile(path, fileName, fileInput);			
				logger.debug("Upload Path:"+path); 
				if(flag){
					String savePath = path+"/"+fileName;
					applyright.setSavePath(savePath);
					validService.update(applyright);
					return "redirect:/upload/success";
				}else{
					return "redirect:/upload/failed";
				}
			}else{
				return "redirect:/upload/failed";
			}
		}
	}
	
	@RequestMapping(value="/admin/applyright/company/add", method = RequestMethod.POST)
	public String addCompanyApplyRight(@Valid PersonApplyRightForm applyrightForm, BindingResult validResult,HttpSession session
			,MultipartHttpServletRequest request) throws Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		boolean flag = false;
		logger.info("===== into invest valid controller=====");
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/applyright/new";
		}else{
			ApplyRight applyright = new ApplyRight();
			MyUtil.copyValidBeanToDestBean(applyrightForm, applyright);
			applyright.setDate(new Date());
			applyright.setUser(userInfo.getUser());
			applyright = validService.create(applyright);		
			MultipartFile multipartFile = request.getFile("myfiles");
			if(!multipartFile.isEmpty()){
				String fileName = multipartFile.getOriginalFilename();
				String path ="/resources/attached/"+userInfo.getId()+"/applyright";
				InputStream fileInput = multipartFile.getInputStream();
				flag =  FTPUtil.getInstance().uploadFile(path, fileName, fileInput);			
				logger.debug("Upload Path:"+path); 
				if(flag){
					String savePath = path+"/"+fileName;
					applyright.setSavePath(savePath);
					applyright.setStatus(GlobalDefs.WAITE);
					validService.update(applyright);
					return "redirect:/upload/success";
				}else{
					return "redirect:/upload/failed";
				}
			}else{
				return "redirect:/upload/failed";
			}
		}
	}
	
	
	@RequestMapping(value = "/admin/applyright/validInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse patentInfoFormAjaxJson(@Valid PersonApplyRightForm applyrightForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value="/upload/{status}")
	public String uploadStatus(@PathVariable String status){
		return "upload."+status;
	}
}
