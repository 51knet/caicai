package com.knet51.patents.controllers.admin.projects;

import java.io.File;
import java.util.Date;
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

import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.UserService;
import com.knet51.patents.jpa.services.projects.ProjectsService;
import com.knet51.patents.util.fileUpLoad.FileUtil;

@Controller
public class ProjectsDetailController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectsDetailController.class);
	private static final long MAX_FILE_SIZE_2M = 2*1024*1024;
	private static final Integer LOGO_WIDTH = 260;
	private static final Integer LOGO_HEIGHT = 190;
	@Autowired
	private ProjectsService projectsService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/admin/projects/add", method = RequestMethod.POST)
	public String addProjects(@Valid ProjectsForm projectsForm, BindingResult validResult,
			HttpSession session,MultipartHttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		logger.info("===== into projects=====");
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/projects/new";
		}else{
			Projects projects = new Projects();
			projects.setCompanyName(projectsForm.getCompanyName());
			projects.setContent(projectsForm.getContent());
			projects.setEmpNumber(projectsForm.getEmpNumber());
			projects.setIndustry(projectsForm.getIndustry());
			projects.setLocation(projectsForm.getLocation());
			projects.setProgess(projectsForm.getProgess());
			projects.setProjectName(projectsForm.getProjectName());
			projects.setTotalMoney(Long.parseLong(projectsForm.getTotalMoney()));
			projects.setBoss(projectsForm.getBoss());
			projects.setPhone(projectsForm.getPhone());
			
			projects.setDate(new Date());
			projects.setStatus(GlobalDefs.WAITE);
			projects.setUser(userInfo.getUser());
			Projects newProjects = projectsService.create(projects);
			
			List<MultipartFile> files = request.getFiles("logoPath");
			for (int i = 0; i < files.size(); i++) {
				MultipartFile multipartFile = files.get(i);
				if(!multipartFile.isEmpty()){
					if(multipartFile.getSize()>MAX_FILE_SIZE_2M){
						redirectAttributes.addFlashAttribute("errorMsg", "图片不得大于2M");
						return "redirect:/admin/projects/new";
					}else{
						logger.info("Upload file name:"+multipartFile.getOriginalFilename()); 
						String fileName = multipartFile.getOriginalFilename();
						String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
						String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/projects/"+newProjects.getId();
						logger.debug("Upload Path:"+path); 
						FileUtil.createRealPath(path, session);
						String previewFile = path+File.separator+"small"+"."+fileExtension;
						File saveDest = new File(path + File.separator + fileName);
						multipartFile.transferTo(saveDest);
						FileUtil.getPreviewImage(saveDest, new File(previewFile), fileExtension,LOGO_WIDTH,LOGO_HEIGHT);
						String savePath = FileUtil.getSavePath("projects", userInfo.getId(), newProjects.getId()+"", request)+"/small"+"."+fileExtension;
						newProjects.setLogoPath(savePath);
					}
				}
			}
			projectsService.update(newProjects);
		}
		return "redirect:/admin/projects/list";
	}
	
	@RequestMapping(value="/admin/projects/edit/edit", method = RequestMethod.POST)
	public String editProjects(@Valid ProjectsForm projectsForm, BindingResult validResult,@RequestParam("projects_id") Long projects_id,
			HttpSession session,MultipartHttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(validResult.hasErrors()){
			logger.info("====="+validResult.toString());
			return "redirect:/admin/projects/edit/"+projects_id;
		}else{
			Projects projects = projectsService.findOne(projects_id);
			projects.setCompanyName(projectsForm.getCompanyName());
			projects.setContent(projectsForm.getContent());
			projects.setEmpNumber(projectsForm.getEmpNumber());
			projects.setIndustry(projectsForm.getIndustry());
			projects.setLocation(projectsForm.getLocation());
			projects.setProgess(projectsForm.getProgess());
			projects.setProjectName(projectsForm.getProjectName());
			projects.setTotalMoney(Long.parseLong(projectsForm.getTotalMoney()));
			projects.setBoss(projectsForm.getBoss());
			projects.setPhone(projectsForm.getPhone());
			
			projects.setDate(new Date());
			projects.setStatus(GlobalDefs.WAITE);
			projects.setUser(userInfo.getUser());
			
			
			List<MultipartFile> files = request.getFiles("coverFile");
			for (int i = 0; i < files.size(); i++) {
				MultipartFile multipartFile = files.get(i);
				if(!multipartFile.isEmpty()){
					if(multipartFile.getSize()>MAX_FILE_SIZE_2M){
						redirectAttributes.addFlashAttribute("errorMsg", "图片不得大于2M");
						return "redirect:/admin/projects/edit/"+projects_id;
					}else{
						logger.info("Upload file name:"+multipartFile.getOriginalFilename()); 
						String fileName = multipartFile.getOriginalFilename();
						String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
						String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/projects/"+projects.getId();
						logger.debug("Upload Path:"+path); 
						FileUtil.createRealPath(path, session);
						String previewFile = path+File.separator+"small"+"."+fileExtension;
						File saveDest = new File(path + File.separator + fileName);
						multipartFile.transferTo(saveDest);
						FileUtil.getPreviewImage(saveDest, new File(previewFile), fileExtension,LOGO_WIDTH,LOGO_HEIGHT);
						String savePath = FileUtil.getSavePath("projects", userInfo.getId(), projects.getId()+"", request)+"/small"+"."+fileExtension;
						projects.setLogoPath(savePath);
					}
				}
			}
			projectsService.update(projects);
		}
		return "redirect:/admin/projects/list";
	}
	
	@RequestMapping(value="/admin/projects/delete",method = RequestMethod.POST)
	public String destoryProjects(@RequestParam("project_id") Long project_id){
		projectsService.dele(project_id);
		return "redirect:/admin/projects/list";
	}
	
}
