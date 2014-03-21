package com.knet51.patents.controllers.admin.user.invest;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.knet51.ccweb.jpa.entities.InvestValid;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.InvestValidService;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.util.fileUpLoad.FTPUtil;
import com.knet51.patents.util.fileUpLoad.FileUtil;

@Controller
public class InvestValidController {
	private static final Logger logger = LoggerFactory.getLogger(InvestValidController.class);
	@Autowired
	private InvestValidService validService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/admin/investvalid/new")
	public String showAddinvestValidPage(HttpServletRequest  request){
		String url = request.getHeader("referer");
		logger.info("--- now the url is ----"+url);
		return "admin.investvalid.add";
	}
	
	@RequestMapping(value="/admin/investvalid/add", method = RequestMethod.POST)
	public String addInvestValid(@Valid InvestValidForm investValidForm, BindingResult validResult,HttpSession session
			,MultipartHttpServletRequest request) throws Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		logger.info("===== into invest valid controller=====");
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/invest/valid/add";
		}else{
			InvestValid investValid = new InvestValid();
			investValid.setContent(investValidForm.getContent());
			investValid.setEmail(investValidForm.getEmail());
			investValid.setName(investValidForm.getName());
			investValid.setPhone(investValidForm.getPhone());
			investValid.setUser(userInfo.getUser());
			investValid.setStatus(GlobalDefs.WAITE);
			investValid = validService.create(investValid);
			
			List<MultipartFile> files = request.getFiles("myFiles");
			for (int i = 0; i < files.size(); i++) {
				MultipartFile multipartFile = files.get(i);
				if(!multipartFile.isEmpty()){
					logger.info("Upload file name:"+multipartFile.getOriginalFilename()); 
					String fileName = multipartFile.getOriginalFilename();
					String path ="/resources/attached/"+userInfo.getId()+"/investvalid";
					InputStream fileInput = multipartFile.getInputStream();
					boolean flag =  FTPUtil.getInstance().uploadFile(path, fileName, fileInput);
					logger.debug("Upload Path:"+path); 
					FileUtil.createRealPath(path, session);
					if(flag){
						String savePath = path+"/"+fileName;
						investValid.setSavePath(savePath);
					}
				}
			}
			validService.update(investValid);
		}
		return "redirect:/admin";
	}
}
