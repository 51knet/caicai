package com.knet51.patents.controllers.admin.rolevalid;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.knet51.ccweb.jpa.entities.RoleValid;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.RoleValidService;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.util.ajax.AjaxValidationEngine;
import com.knet51.patents.util.ajax.ValidationResponse;
import com.knet51.patents.util.fileUpLoad.FTPUtil;
import com.knet51.patents.util.fileUpLoad.FileUtil;

@Controller
public class RoleValidController {
	private static final Logger logger = LoggerFactory.getLogger(RoleValidController.class);
	@Autowired
	private RoleValidService validService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/admin/rolevalid/new")
	public String showAddinvestValidPage(HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		return "admin."+userInfo.getRole()+".investvalid.add";
	}
	
	@RequestMapping(value="/admin/rolevalid/add", method = RequestMethod.POST)
	public String addInvestValid(@Valid RoleValidForm roleValidForm, BindingResult validResult,HttpSession session
			,MultipartHttpServletRequest request) throws Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		boolean flag = false;
		logger.info("===== into invest valid controller=====");
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/rolevalid/new";
		}else{
			RoleValid roleValid = new RoleValid();
			roleValid.setContent(roleValidForm.getContent());
			roleValid.setName(roleValidForm.getName());
			roleValid.setPhone(roleValidForm.getPhone());
			roleValid.setDate(new Date());
			roleValid.setUser(userInfo.getUser());
			roleValid.setApplypermit(roleValidForm.getApplypermit());
			roleValid = validService.create(roleValid);
			
			MultipartFile multipartFile = request.getFile("myfiles");
			if(!multipartFile.isEmpty()){
				logger.info("Upload file name:"+multipartFile.getOriginalFilename()); 
				String fileName = multipartFile.getOriginalFilename();
				String path ="/resources/attached/"+userInfo.getId()+"/rolevalid";
				InputStream fileInput = multipartFile.getInputStream();
				flag =  FTPUtil.getInstance().uploadFile(path, fileName, fileInput);
				logger.debug("Upload Path:"+path); 
				FileUtil.createRealPath(path, session);
				if(flag){
					String savePath = path+"/"+fileName;
					roleValid.setSavePath(savePath);
					roleValid.setStatus(GlobalDefs.WAITE);
					validService.update(roleValid);
					return "redirect:/upload/success";
				}else{
					return "redirect:/upload/failed";
				}
			}else{
				return "redirect:/upload/failed";
			}
		}
	}
	
	@RequestMapping(value = "/admin/rolevalid/validInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse patentInfoFormAjaxJson(@Valid RoleValidForm roleValidForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	@RequestMapping(value="/upload/{status}")
	public String uploadStatus(@PathVariable String status){
		return "upload."+status;
	}
}
