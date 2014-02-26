package com.knet51.patents.controllers.admin.kefu.rzfh;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knet51.ccweb.jpa.entities.projects.Rzfh;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.projects.RzfhService;
import com.knet51.patents.util.fileUpLoad.FileUtil;
@Controller
public class KefuRzfhDetailController {
	private static final Logger logger = LoggerFactory.getLogger(KefuRzfhDetailController.class);
	private static final long MAX_FILE_SIZE_2M = 2*1024*1024;
	private static final Integer LOGO_WIDTH = 260;
	private static final Integer LOGO_HEIGHT = 190;

	@Autowired
	private RzfhService rzfhService;
	
	@RequestMapping(value="/admin/kefu/rzfh/add", method = RequestMethod.POST)
	public String addRzfh(@Valid RzfhForm rzfhForm, BindingResult validResult,@RequestParam("types") String types,
			HttpSession session,MultipartHttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/kefu/rzfh/new";
		}else{
			Rzfh rzfh = new Rzfh();
			
			rzfh.setContent(rzfhForm.getContent());
			rzfh.setName(rzfhForm.getName());
			rzfh.setStatus(GlobalDefs.PASS);
			rzfh.setTypes(types);
			rzfh.setWebUrl(rzfhForm.getWebUrl());
			rzfh.setUser(userInfo.getUser());
			
			Rzfh newRzfh = rzfhService.create(rzfh);
			
			List<MultipartFile> files = request.getFiles("logoPath");
			for (int i = 0; i < files.size(); i++) {
				MultipartFile multipartFile = files.get(i);
				if(!multipartFile.isEmpty()){
					if(multipartFile.getSize()>MAX_FILE_SIZE_2M){
						redirectAttributes.addFlashAttribute("errorMsg", "图片不得大于2M");
						return "redirect:/admin/kefu/rzfh/new";
					}else{
						logger.info("Upload file name:"+multipartFile.getOriginalFilename()); 
						String fileName = multipartFile.getOriginalFilename();
						String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
						String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/rzfh/"+newRzfh.getId();
						logger.debug("Upload Path:"+path); 
						FileUtil.createRealPath(path, session);
						String previewFile = path+File.separator+"small"+"."+fileExtension;
						File saveDest = new File(path + File.separator + fileName);
						multipartFile.transferTo(saveDest);
						FileUtil.getPreviewImage(saveDest, new File(previewFile), fileExtension,LOGO_WIDTH,LOGO_HEIGHT);
						String savePath = FileUtil.getSavePath("rzfh", userInfo.getId(), newRzfh.getId()+"", request)+"/small"+"."+fileExtension;
						newRzfh.setLogoPath(savePath);
					}
				}
			}
			rzfhService.create(newRzfh);
		}
		return "redirect:/admin/kefu/rzfh/list/all";
	}
	
	@RequestMapping(value="/admin/kefu/rzfh/edit/edit", method = RequestMethod.POST)
	public String editRzfh(@Valid RzfhForm rzfhForm, BindingResult validResult,@RequestParam("rzfh_id") Long rzfh_id,@RequestParam("types") String types,
			HttpSession session,MultipartHttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		logger.info("========== into rzfh update controller");
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/kefu/rzfh/list/all";
		}else{
			Rzfh rzfh = rzfhService.findOne(rzfh_id);
			
			rzfh.setContent(rzfhForm.getContent());
			rzfh.setName(rzfhForm.getName());
			rzfh.setStatus(GlobalDefs.PASS);
			if(!types.trim().equals("")){
				rzfh.setTypes(types);
			}
			rzfh.setWebUrl(rzfhForm.getWebUrl());
				
			List<MultipartFile> files = request.getFiles("logoPath");
			for (int i = 0; i < files.size(); i++) {
				MultipartFile multipartFile = files.get(i);
				if(!multipartFile.isEmpty()){
					if(multipartFile.getSize()>MAX_FILE_SIZE_2M){
						redirectAttributes.addFlashAttribute("errorMsg", "图片不得大于2M");
						return "redirect:/admin/kefu/rzfh/new";
					}else{
						logger.info("Upload file name:"+multipartFile.getOriginalFilename()); 
						String fileName = multipartFile.getOriginalFilename();
						String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
						String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/rzfh/"+rzfh.getId();
						logger.debug("Upload Path:"+path); 
						FileUtil.createRealPath(path, session);
						String previewFile = path+File.separator+"small"+"."+fileExtension;
						File saveDest = new File(path + File.separator + fileName);
						multipartFile.transferTo(saveDest);
						FileUtil.getPreviewImage(saveDest, new File(previewFile), fileExtension,LOGO_WIDTH,LOGO_HEIGHT);
						String savePath = FileUtil.getSavePath("rzfh", userInfo.getId(), rzfh.getId()+"", request)+"/small"+"."+fileExtension;
						rzfh.setLogoPath(savePath);
					}
				}
			}
			rzfhService.update(rzfh);
		}
		return "redirect:/admin/kefu/rzfh/list/all";
	}
	
	@RequestMapping(value="/admin/kefu/rzfh/delete",method = RequestMethod.POST)
	public String destoryProjects(@RequestParam("rzfh_id") Long rzfh_id){
		rzfhService.dele(rzfh_id);
		return "redirect:/admin/kefu/rzfh/list/all";
	}
}
