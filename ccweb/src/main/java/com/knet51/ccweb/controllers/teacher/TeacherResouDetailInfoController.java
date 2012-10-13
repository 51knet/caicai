package com.knet51.ccweb.controllers.teacher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.Resource;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.ResourceService;
import com.knet51.ccweb.util.fileUpLoad.FileUtil;

@Controller
public class TeacherResouDetailInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherResouInfoPageController.class);
	@Autowired
	private ResourceService service;
	
	@Transactional
	@RequestMapping(value="/teacherResouAddDetail",method=RequestMethod.POST)
	public String teacherResouInfo(HttpSession session,Model m,@RequestParam("desc") String desc, MultipartHttpServletRequest request) throws Exception{
		logger.info("#####Into TeacherResouInfoPageController#####");
		List<MultipartFile> files = request.getFiles("file");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		User user = userInfo.getUser();
		for(int i=0;i<files.size();i++){
			if(!files.get(i).isEmpty()){
				Resource resource = new Resource();
				logger.info("�ϴ��ļ���"+files.get(i).getOriginalFilename()); 
				String fileName = files.get(i).getOriginalFilename();
				String realPath = session.getServletContext().getRealPath("/WEB-INF/temp/");
				resource.setDescription(desc);
				resource.setName(fileName);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = format.format(new Date());
				resource.setDate(date);
				String savePath = FileUtil.saveFile(files.get(i).getInputStream(), fileName, realPath);
				resource.setSavePath(savePath);
				service.create(resource, user);
			}
		}
		List<Resource> list = service.listAllByUid(user.getId());
		m.addAttribute("list", list);
		return "teacherResouPage";
	}

//	@Override
//	public ModelAndView resolveException(HttpServletRequest request,
//			HttpServletResponse response, Object obj, Exception e) {
//		// TODO Auto-generated method stub
//		Map<Object, Object> model = new HashMap<Object, Object>(); 
//		if (e instanceof MaxUploadSizeExceededException){ 
//				model.put("errors", "File size should be less then "+ 
//						((MaxUploadSizeExceededException)e).getMaxUploadSize()+" byte."); 
//		} else{ 
//				model.put("errors", "Unexpected error: " + e.getMessage()); 
//		} 
//		return new ModelAndView("teacherResouAddDetail", (Map) model); 
//		
//	}
	

}
