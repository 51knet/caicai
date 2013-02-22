package com.knet51.ccweb.controllers.teacher.course;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.resource.ResourceType;
import com.knet51.ccweb.jpa.entities.teacher.CourseLesson;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.services.CourseLessonService;
import com.knet51.ccweb.jpa.services.CourseResourceService;
import com.knet51.ccweb.jpa.services.ResourceTypeService;
import com.knet51.ccweb.jpa.services.TeacherCourseService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.util.fileUpLoad.FileUtil;


@Controller
public class TeacherCourseInfoDetailController {
	private static Logger logger = 
			LoggerFactory.getLogger(TeacherCourseInfoDetailController.class);
	
	@Autowired
	private TeacherCourseService teacherCourseService;
	@Autowired
	private CourseResourceService courseResourceService; 
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseLessonService lessonService;
	@Autowired
	private ResourceTypeService resourceTypeService;
	
	/**
	 * update the teacher's course basic information
	 * @param courseInfoForm
	 * @param validResult
	 * @param session
	 * @param course_id
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/edit",method=RequestMethod.POST)
	public String TeacherCourseUpdateInfo(@Valid TeacherCourseInfoForm courseInfoForm,
			BindingResult validResult, HttpSession session,@RequestParam("id") Long course_id){
		
		logger.info("#### Into TeacherCourseAdd Controller ####");
		if(validResult.hasErrors()){
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "redirect:/admin/teacher/course/edit/"+Long.valueOf(course_id);
		}else{
			TeacherCourse course = teacherCourseService.findOneById(Long.valueOf(course_id));
			String courseName = courseInfoForm.getCourseName();
			String courseDesc = courseInfoForm.getCourseDesc();
			course.setCourseName(courseName);
			course.setCourseDesc(courseDesc);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(new Date());
			course.setCourseDate(date);
			teacherCourseService.updateTeacherCourse(course);
			return "redirect:/admin/teacher/course/list";
		}
	
	}
	/**
	 * remove the course to the recycle bin
	 * @param session
	 * @param course_id
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/destory",method=RequestMethod.POST)
	public String TeacherCourseDele( HttpSession session,@RequestParam("cId") Long course_id){
		logger.info("#### Into TeacherCourseAdd Controller ####");
			//teacherCourseService.deleTeacherCourse(course_id);
			TeacherCourse course = teacherCourseService.findOneById(Long.valueOf(course_id));
			course.setPublish(GlobalDefs.PUBLISH_NUM_RECYCLE);
			teacherCourseService.updateTeacherCourse(course);
			return "redirect:/admin/teacher/course/list";
	}
	/**
	 * delete the course but it will still save in the DB
	 * @param session
	 * @param course_id
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/deleted",method=RequestMethod.POST)
	public String deleFromCourseRecycle( HttpSession session,@RequestParam("cId") Long course_id){
		logger.info("#### Into TeacherCourseAdd Controller ####");
		TeacherCourse course = teacherCourseService.findOneById(Long.valueOf(course_id));
		course.setPublish(GlobalDefs.PUBLISH_NUM_DELETE);
		teacherCourseService.updateTeacherCourse(course);
		return "redirect:/admin/teacher/course/list";
	}
	/**
	 * recover the course which is in recycle bin
	 * @param session
	 * @param course_id
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/recover",method=RequestMethod.POST)
	public String courseRecoverUnpublish( HttpSession session,@RequestParam("cId") Long course_id){
		logger.info("#### Into TeacherCourseAdd Controller ####");
			//teacherCourseService.deleTeacherCourse(course_id);
			TeacherCourse course = teacherCourseService.findOneById(Long.valueOf(course_id));
			course.setPublish(GlobalDefs.PUBLISH_NUM_ADMIN);
			teacherCourseService.updateTeacherCourse(course);
			return "redirect:/admin/teacher/course/list";
	}
	
	/**
	 * create the course resource
	 * @param session
	 * @param model
	 * @param request
	 * @param course_id
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/resource/create",method=RequestMethod.POST)
	public String TeacherCourseResourceAdd(HttpSession session,Model model,
			MultipartHttpServletRequest request,@RequestParam("courseId") Long course_id) throws  Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<MultipartFile> files = request.getFiles("resourceFile");
		String lessonNum = request.getParameter("lessonNum");
		String resourceName = request.getParameter("resourceName");
		for(int i=0;i<files.size();i++){
			if(!files.get(i).isEmpty()){
				MultipartFile multipartFile = files.get(i);
//				if(multipartFile.getSize()>1024*1024){
//					
//					return "redirect:/admin/teacher/course/edit/"+Long.valueOf(course_id)+"/modifycourse";
//				}
				Long type = Long.parseLong(request.getParameter("type"));
				ResourceType resourceType = resourceTypeService.findOneById(type);
				Long courseLessonId = Long.parseLong(request.getParameter("lessonId"));
				//CourseLesson courseLesson = lessonService.findOne(courseLessonId);
				CourseResource resource = new CourseResource();
				logger.info("Upload file name:"+files.get(i).getOriginalFilename()); 	
				String fileName = multipartFile.getOriginalFilename();
				//String name = fileName.substring(0, fileName.indexOf("."));
				resource.setFileName(resourceName);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String date = format.format(new Date());
				resource.setDate(date);
				TeacherCourse teacherCourse = teacherCourseService.findOneById(Long.valueOf(course_id));
				//String realPath = FileUtil.getPath("courseResource", userInfo.getId(), teacherCourse.getCourseName(), session);
				String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/course/"+teacherCourse.getCourseName()+File.separator+lessonNum;
				FileUtil.createRealPath(path, session);
				File saveDest = new File(path + File.separator + fileName);
				multipartFile.transferTo(saveDest);
			//	String saveName = FileUtil.saveFile(files.get(i).getInputStream(), fileName, path);
				String savePath = path+File.separator+fileName;
				resource.setSavePath(savePath);
				resource.setSaveName(fileName);
				resource.setLessonNum(lessonNum);
				resource.setCourse_id(Long.valueOf(course_id));
				resource.setCourseLessonId(courseLessonId);
				resource.setResourceType(resourceType);
				resource.setStatus(GlobalDefs.STATUS_COURSE_RESOURCE);
				courseResourceService.createCourseResource(resource);
			}
		}
		return "redirect:/admin/teacher/course/edit/"+Long.valueOf(course_id)+"/modifycourse";
	}
	
	/**
	 * update the course resource
	 * @param session
	 * @param model
	 * @param request
	 * @param resource_id
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/resource/edit",method=RequestMethod.POST)
	public String TeacherCourseResourceEdit(HttpSession session,Model model,
			MultipartHttpServletRequest request,@RequestParam("resourceId") Long resource_id) throws  Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<MultipartFile> files = request.getFiles("resourceFile");
		String resourceName = request.getParameter("resourceName");
		CourseResource resource = courseResourceService.findOneById(resource_id); 
		Long type = Long.parseLong(request.getParameter("type"));
		ResourceType resourceType = resourceTypeService.findOneById(type);
		for(int i=0;i<files.size();i++){
			if(!files.get(i).isEmpty()){
				MultipartFile multipartFile = files.get(i);
				File oldResource = new File(resource.getSavePath());
				if(oldResource != null){
					oldResource.delete();
				}
				logger.info("Upload file name:"+files.get(i).getOriginalFilename()); 	
				String fileName = multipartFile.getOriginalFilename();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String date = format.format(new Date());
				resource.setDate(date);
				TeacherCourse teacherCourse = teacherCourseService.findOneById(resource.getCourse_id());
				String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/course/"+teacherCourse.getCourseName()+File.separator+resource.getLessonNum();
				FileUtil.createRealPath(path, session);
				File saveDest = new File(path + File.separator + fileName);
				multipartFile.transferTo(saveDest);
				String savePath = path+File.separator+fileName;
				resource.setSavePath(savePath);
				resource.setSaveName(fileName);
				
			}
			resource.setFileName(resourceName);
			resource.setResourceType(resourceType);
			courseResourceService.createCourseResource(resource);
		}
		return "redirect:/admin/teacher/course/edit/"+resource.getCourse_id()+"/modifycourse";
	}
	/**
	 * destory the course resource
	 * @param session
	 * @param model
	 * @param resource_id
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/resource/destory",method=RequestMethod.POST)
	public String destoryTeacherCourseResource(HttpSession session,Model model,
			@RequestParam("resourceId") Long resource_id) throws  Exception{
		CourseResource resource = courseResourceService.findOneById(resource_id); 
		File oldResource = new File(resource.getSavePath());
		if(oldResource != null){
			oldResource.delete();
		}
		courseResourceService.deleCourseResource(resource_id);
		return "redirect:/admin/teacher/course/edit/"+resource.getCourse_id()+"/modifycourse";
	}
	
	/**
	 * download the course resource
	 * @param resource_id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/course/resource/download/{resource_id}")
	public String resourceDownLoad(@PathVariable Long resource_id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.info("-------Into resource DownLoad controller------");
		CourseResource resource = courseResourceService.findOneById(resource_id);
		String savePath = resource.getSavePath();
		String fileName = resource.getSaveName();
		FileUtil.downLoad(request, response, savePath, fileName);
		return null;
	}
	

	/**
	 * 修改课程
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{course_id}/modifycourse")
	public String modifyCreateTeacherCourse(HttpSession session,@PathVariable Long course_id,Model model,HttpServletRequest request){
		TeacherCourse course=teacherCourseService.findOneById(course_id);
		if(course == null){
			return "redirect:/admin/teacher/course/list";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long teacherId=course.getTeacher().getId();
			if(userInfo.getId()!=teacherId){
				return "redirect:/admin/teacher/course/list";
			}
		}
		List<CourseResource> listResource = courseResourceService.getAllCourseResourceByCourseIdAndStatus(course_id, GlobalDefs.STATUS_COURSE_RESOURCE);
		List<CourseResource> courseList;
		List<ResourceType> listType = resourceTypeService.getAllType();
		model.addAttribute("type", listType);
		Map<String, List<CourseResource>> courseMap = new TreeMap<String, List<CourseResource>>();
		String LessonNum = null;
		for (CourseResource courseResource : listResource) {
			courseList = new ArrayList<CourseResource>();
			LessonNum = courseResource.getLessonNum();
			courseList = courseResourceService
					.getResourceByLessonNumAndCourseId(LessonNum,course_id);
			courseMap.put(LessonNum, courseList);
		}
		List<CourseLesson> lessonNumList = lessonService.getMaxLessonNumByCourseId(course_id);
		if(lessonNumList.size()>0){
			model.addAttribute("lesson", lessonNumList.get(0));
		}
		List<CourseLesson> lessonList = lessonService.findCourseLessonByCourseId(course_id);
		model.addAttribute("lessonList", lessonList);
		model.addAttribute("lessonListCount", lessonList.size());
		model.addAttribute("resourceCount", listResource.size());
		model.addAttribute("courseMap", courseMap);
		model.addAttribute("course", course);
		
		return "admin.teacher.course.edit.modifycourse";
	}
	/**
	 * 基本信息
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/basicinfo")
	public String basicMessage(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=teacherCourseService.findOneById(id);
		if(course == null){
			return "redirect:/admin/teacher/course/list";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long teacherId=course.getTeacher().getId();
			if(userInfo.getId()!=teacherId){
				return "redirect:/admin/teacher/course/list";
			}
		}
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.basicinfo";
	}
	/**
	 * 修改基本信息
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/basicinfomodify",method=RequestMethod.POST)
	public String modifyBasicMessage(HttpSession session,@RequestParam("courseId") Long id,Model model,
			HttpServletRequest request,@Valid TeacherCourseInfoForm teacherCourseInfoForm,BindingResult validResult){
		if (validResult.hasErrors()) {
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "redirect:/admin/teacher/course/edit/{id}/basicinfo";
		}else{
		TeacherCourse course=teacherCourseService.findOneById(id);
		String courseName=teacherCourseInfoForm.getCourseName();
		String courseType=teacherCourseInfoForm.getCourseType();
		String courseDesc=teacherCourseInfoForm.getCourseDesc();
		if(courseName!=null||courseType!=null||courseDesc!=null){
			course.setCourseName(courseName);
			course.setCourseType(courseType);
			course.setCourseDesc(courseDesc); 
			teacherCourseService.updateTeacherCourse(course);
		}
		model.addAttribute("course", course);
	}
		return "redirect:/admin/teacher/course/edit/"+id+"/basicinfo";
	}
	/***
	 * 详细信息
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/detailinfo")
	public String detailMessage(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=teacherCourseService.findOneById(id);
		if(course == null){
			return "redirect:/admin/teacher/course/list";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long teacherId=course.getTeacher().getId();
			if(userInfo.getId()!=teacherId){
				return "redirect:/admin/teacher/course/list";
			}
		}
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.detailinfo";
	}
	/***
	 * 修改详细信息
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/detailinfomodify",method=RequestMethod.POST)
	public String modifyDetailMessage(HttpSession session,@RequestParam("courseId") Long id,Model model,HttpServletRequest request){
		String character=request.getParameter("courseCharacter");
		String targetPerson=request.getParameter("targetPerson");
		TeacherCourse course=teacherCourseService.findOneById(id);
		course.setCourseCharacter(character);
		course.setTargetPerson(targetPerson);
		model.addAttribute("course", course);
		return "redirect:/admin/teacher/course/edit/"+id+"/detailinfo";
	}
	/***
	 * 封面
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/cover")
	public String CreateCover(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=teacherCourseService.findOneById(id);
		if(course == null){
			return "redirect:/admin/teacher/course/list";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long teacherId=course.getTeacher().getId();
			if(userInfo.getId()!=teacherId){
				return "redirect:/admin/teacher/course/list";
			}
		}
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.cover";
	}
	/***
	 * 修改封面
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/moidfycover",method=RequestMethod.POST)
	public String modifyCreateCover(HttpSession session,@RequestParam("courseId") Long id,MultipartHttpServletRequest request,Model model) throws Exception{
			List<MultipartFile> files = request.getFiles("coverFile");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		TeacherCourse teacherCourse=teacherCourseService.findOneById(id);
		String courseName = teacherCourse.getCourseName();
		for(int i=0;i<files.size();i++){
			MultipartFile multipartFile = files.get(i);
			if(!files.get(i).isEmpty()){
				//logger.info("Upload file name:"+multipartFile.getOriginalFilename()); 
				String fileName = multipartFile.getOriginalFilename();
				String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
				String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/course/"+courseName;
				logger.debug("Upload Path:"+path); 
				FileUtil.createRealPath(path, session);
				String previewFile = path+File.separator+"small"+"."+fileExtension;
				File saveDest = new File(path + File.separator + fileName);
				multipartFile.transferTo(saveDest);
				FileUtil.getPreviewImage(saveDest, new File(previewFile), fileExtension);
				String savePath = FileUtil.getSavePath("course", userInfo.getId(), courseName, request)+File.separator+"small"+"."+fileExtension;
				teacherCourse.setCourseCover(savePath);
			}      
		}
		TeacherCourse course = teacherCourseService.updateTeacherCourse(teacherCourse);
		model.addAttribute("course", course);
		return "redirect:/admin/teacher/course/edit/"+id+"/cover";
		}
	/**
	 * 修改视频
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/watchvideo")
	public String modifyWatchVideo(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=teacherCourseService.findOneById(id);
		if(course == null){
			return "redirect:/admin/teacher/course/list";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long teacherId=course.getTeacher().getId();
			if(userInfo.getId()!=teacherId){
				return "redirect:/admin/teacher/course/list";
			}
		}
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.watchvideo";
	}
	/**
	 * 权限设置
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/powerprice")
	public String powerPrice(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=teacherCourseService.findOneById(id);
		if(course == null){
			return "redirect:/admin/teacher/course/list";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long teacherId=course.getTeacher().getId();
			if(userInfo.getId()!=teacherId){
				return "redirect:/admin/teacher/course/list";
			}
		}
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.powerprice";
	}
	/**
	 * 修改权限设置
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/powerpricemodify")
	public String modifyPowerPrice(HttpSession session,@RequestParam("courseId") Long id,Model model,HttpServletRequest request){
		Integer status=Integer.parseInt(request.getParameter("status"));
		String pwd=request.getParameter("pwd");
		TeacherCourse course=teacherCourseService.findOneById(id);
		course.setStatus(status);
		course.setPwd(pwd.trim());
		model.addAttribute("course", course);
		return "redirect:/admin/teacher/course/edit/"+id+"/powerprice";
	}
	/**
	 * 取消课程
	 * @returndeleLessonNum
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{id}/deletecourse")
	public String modifyDeleteMessage(HttpSession session,@PathVariable Long id,Model model){
		TeacherCourse course=teacherCourseService.findOneById(id);
		if(course == null){
			return "redirect:/admin/teacher/course/list";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long teacherId=course.getTeacher().getId();
			if(userInfo.getId()!=teacherId){
				return "redirect:/admin/teacher/course/list";
			}
		}
		model.addAttribute("course", course);
		return "admin.teacher.course.edit.deletecourse";
	}
	/**
	 * 删除课程
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/deletecoursemodify")
	public String deleteMessage(HttpSession session,@RequestParam("courseId") Long id,Model model){
		TeacherCourse course=teacherCourseService.findOneById(id);
		if(course == null){
			return "redirect:/admin/teacher/course/list";
		}else{
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Long teacherId=course.getTeacher().getId();
			if(userInfo.getId()!=teacherId){
				return "redirect:/admin/teacher/course/list";
			}
		}
		course.setPublish(GlobalDefs.PUBLISH_NUM_RECYCLE);
		teacherCourseService.createTeacherCourse(course);
		return "redirect:/admin/teacher/course/list";
	}
	
}
