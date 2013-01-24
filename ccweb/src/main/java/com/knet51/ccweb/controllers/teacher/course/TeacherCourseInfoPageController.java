package com.knet51.ccweb.controllers.teacher.course;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.controllers.teacher.TeacherPersonalInfoForm;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.CourseLesson;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.services.CourseLessonService;
import com.knet51.ccweb.jpa.services.CourseResourceService;
import com.knet51.ccweb.jpa.services.TeacherCourseService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;
import com.knet51.ccweb.util.fileUpLoad.FileUtil;

@Controller
public class TeacherCourseInfoPageController {
	private static final Logger logger = 
			LoggerFactory.getLogger(TeacherCourseInfoPageController.class);
	@Autowired
	private TeacherCourseService teacherCourseService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private CourseResourceService courseResourceService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseLessonService lessonService;
	
	@RequestMapping(value="/admin/teacher/course/list")
	public String teacherCourseInfo(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		logger.info("#####Into TeacherCourseInfoPageController#####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Teacher teacher = teacherService.findOne(userInfo.getId());
		Page<TeacherCourse> onePage =teacherCourseService.findTeacherCourseByTeacherAndPublishGreaterThan(pageNumber, pageSize, teacher,GlobalDefs.PUBLISH_NUM_DELETE);
		//Page<TeacherCourse> page = teacherCourseService.findTeacherCourseByTeacherAndPublish(pageNumber, pageSize, teacher, publish)
		model.addAttribute("page", onePage);
		return "admin.teacher.course.list";
	}
	
	@RequestMapping(value="/admin/teacher/course/list/{publish}")
	public String teacherCoursePublished(@PathVariable Integer publish,HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		logger.info("##### Into teacherCoursePublished #####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Teacher teacher = teacherService.findOne(userInfo.getId());
		Page<TeacherCourse> onePage = teacherCourseService.findTeacherCourseByTeacherAndPublish(pageNumber, pageSize, teacher, publish);
		model.addAttribute("page", onePage);
		return "admin.teacher.course.list";
	}
	
	@RequestMapping(value="/admin/teacher/course/new")
	public String courseAdd(){
		return "admin.teacher.course.new";
	}
	

	
	@RequestMapping(value="/admin/teacher/course/view/{course_id}")
	public String detailCourseInfo(@PathVariable Long course_id,Model model){
		TeacherCourse course = teacherCourseService.findOneById(course_id);
		model.addAttribute("course", course);

		List<CourseResource> listResource = courseResourceService.getResourceByCourseId(course_id);
		List<CourseResource> courseList;
		Map<String, List<CourseResource> > courseMap = new TreeMap<String, List<CourseResource> >();
		String lessonNum = null;
		for (CourseResource courseResource : listResource) {
			courseList = new ArrayList<CourseResource>();
			lessonNum = courseResource.getLessonNum();
			courseList = courseResourceService
					.getResourceByLessonNumAndCourseId(lessonNum,course_id);
			courseMap.put(lessonNum, courseList);
		}
		model.addAttribute("resourceCount", listResource.size());
		model.addAttribute("courseMap", courseMap);
		return "admin.teacher.course.view";
	}
	
	@RequestMapping(value="/admin/teacher/course/edit/{course_id}")
	public String updateCourseInfo(@PathVariable Long course_id,Model model){
		TeacherCourse course = teacherCourseService.findOneById(course_id);
		model.addAttribute("course", course);
		return "admin.teacher.course.edit";
	}
	
	@RequestMapping(value="/admin/teacher/{course_id}/resource/new")
	public String addTeacherCourseResource(Model model,@PathVariable Long course_id){
		model.addAttribute("id",course_id);
		return "admin.teacher.course.resource.new";
	}
	
	/*@RequestMapping(value="/admin/teacher/teacherCourse/detailCourse")
	public String detailCourse(@RequestParam("id") Long id, Model m){
		TeacherCourse course = teacherCourseService.findOneById(id);
		List<CourseResource> resourceList = courseResourceService.getAllCourseResourceById(id);
		m.addAttribute("course", course);
		m.addAttribute("resourceList",resourceList);
		return "admin.teacher.teacherCourse.detailCourse";
	}*/
	
	@RequestMapping(value="/teacher/{teacher_id}/course/list")
	public String getAllTeacherCourse(@PathVariable Long teacher_id,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<TeacherCourse> onePage = teacherCourseService.findTeacherCourseByTeacherAndPublish(pageNumber, pageSize, teacher, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("page", onePage);
		return "teacher.course.list";
	}
	
	@RequestMapping(value="/teacher/{teacher_id}/course/view/{course_id}")
	public String detailCourse(@PathVariable Long teacher_id, @PathVariable Long course_id,Model model){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		
		TeacherCourse course = teacherCourseService.findOneById(course_id);
		model.addAttribute("course", course);
		/*List<CourseResource> resourceList = courseResourceService.getAllCourseResourceById(course_id);
		model.addAttribute("resourceList",resourceList);
		model.addAttribute("resourceCount", resourceList.size());*/
		List<CourseResource> listResource = courseResourceService.getResourceByCourseId(course_id);
		List<CourseResource> courseList;
		Map<String, List<CourseResource>> courseMap = new TreeMap<String, List<CourseResource>>();
		String resourceOrder = null;
		for (CourseResource courseResource : listResource) {
			resourceOrder = courseResource.getLessonNum();
			courseList = new ArrayList<CourseResource>();
			courseList = courseResourceService
					.getResourceByLessonNumAndCourseId(resourceOrder,course_id);
			courseMap.put(resourceOrder, courseList);
		}
		model.addAttribute("resourceCount", listResource.size());
		model.addAttribute("courseMap", courseMap);
		return "teacher.course.view";
	}
	
	
	/*   new add course   */
	
	
	@RequestMapping(value="/admin/teacher/course/addcourse")
	public String addCoursePage(@RequestParam("active") String active,@RequestParam("cid") Integer course_id,Model model){
		if (active == null || active.equals("")) {
			active = "first";
		}
		if (course_id == null || course_id.equals("")) {
			course_id = 0;
		}
		model.addAttribute("active", active);
		model.addAttribute("cid", course_id);
		return "admin.teacher.course.add";
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/course/new/firststep",method=RequestMethod.POST)
	public String TeacherCourseAddInfo(@Valid TeacherCourseInfoForm courseInfoForm,
			BindingResult validResult, HttpSession session,MultipartHttpServletRequest request,Model model) throws Exception{
		logger.info("#### Into TeacherCourseAdd Controller ####");
		if(validResult.hasErrors()){
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "redirect:/admin/teacher/course/addcourse?active=first";
		}else{
			List<MultipartFile> files = request.getFiles("coverFile");
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Teacher teacher = teacherService.findOne(userInfo.getId());
			TeacherCourse course = new TeacherCourse();
			String courseName = courseInfoForm.getCourseName();
			String courseDesc = courseInfoForm.getCourseDesc();
			course.setCourseName(courseName);
			course.setCourseDesc(courseDesc);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(new Date());
			course.setCourseDate(date);
			course.setStatus(GlobalDefs.STATUS_CCWEB);
			course.setPublish(GlobalDefs.PUBLISH_NUM_ADMIN);
			course.setTeacher(teacher);
			course.setCourseType(courseInfoForm.getCourseType());
			for(int i=0;i<files.size();i++){
				MultipartFile multipartFile = files.get(i);
				if(!multipartFile.isEmpty()){
					logger.info("Upload file name:"+multipartFile.getOriginalFilename()); 
					String fileName = multipartFile.getOriginalFilename();
					String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
					String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/course/"+courseName;
					logger.debug("Upload Path:"+path); 
					FileUtil.createRealPath(path, session);
					String previewFile = path+File.separator+"small"+"."+fileExtension;
					File saveDest = new File(path + File.separator + fileName);
					multipartFile.transferTo(saveDest);
					//String saveName = FileUtil.saveFile(multipartFile.getInputStream(), fileName, path);
					FileUtil.getPreviewImage(saveDest, new File(previewFile), fileExtension);
					String savePath = FileUtil.getSavePath("course", userInfo.getId(), courseName, request)+File.separator+"small"+"."+fileExtension;
					//String savePath = request.getContextPath()+"/course/"+userInfo.getId()+"/"+courseName+"/"+saveName;
					course.setCourseCover(savePath);
				}
			}
			
			TeacherCourse newCourse = teacherCourseService.createTeacherCourse(course);
			return "redirect:/admin/teacher/course/addcourse?active=second&cid="+newCourse.getId();
		}
	
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/course/new/secondstep",method=RequestMethod.POST)
	public String addCourseSecond(@RequestParam("cid") Long course_id,
			@RequestParam("pwd") String pwd, @RequestParam("status") Integer status, Model model){
		TeacherCourse course = teacherCourseService.findOneById(Long.valueOf(course_id));
		course.setPwd(pwd.trim());
		course.setStatus(status);
		teacherCourseService.updateTeacherCourse(course);
		return "redirect:/admin/teacher/course/addcourse?active=third&cid="+Long.valueOf(course_id);
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/course/new/thirdstep",method=RequestMethod.POST)
	public String addCourseThird(HttpSession session,Model model,
			MultipartHttpServletRequest request,@RequestParam("cid") Long course_id) throws  Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<MultipartFile> files = request.getFiles("resourceFile");
		String resourceDesc = request.getParameter("resourceDesc");
		String lessonNum = request.getParameter("resourceOrder");
		for(int i=0;i<files.size();i++){
			if(!files.get(i).isEmpty()){
				MultipartFile multipartFile = files.get(i);
				CourseResource resource = new CourseResource();
				logger.info("Upload file name:"+files.get(i).getOriginalFilename()); 	
				String fileName = multipartFile.getOriginalFilename();
				String name = fileName.substring(0, fileName.indexOf("."));
				resource.setFileName(name);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String date = format.format(new Date());
				resource.setDate(date);
				
				TeacherCourse teacherCourse = teacherCourseService.findOneById(Long.valueOf(course_id));
				//String realPath = FileUtil.getPath("courseResource", userInfo.getId(), teacherCourse.getCourseName(), session);
				String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/course/"+teacherCourse.getCourseName()+"/"+lessonNum;
				FileUtil.createRealPath(path, session);
				File saveDest = new File(path + File.separator + fileName);
				multipartFile.transferTo(saveDest);
			//	String saveName = FileUtil.saveFile(files.get(i).getInputStream(), fileName, path);
				String savePath = path+File.separator+fileName;
				resource.setSavePath(savePath);
				resource.setSaveName(fileName);
				resource.setResourceDesc(resourceDesc);
				resource.setLessonNum(lessonNum);
				resource.setCourse_id(Long.valueOf(course_id));
				courseResourceService.createCourseResource(resource);
			}
		}
		return "redirect:/admin/teacher/course/edit/"+Long.valueOf(course_id)+"/modifycourse";
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{course_id}/publish")
	public String publishCourse(@PathVariable Long course_id){
		TeacherCourse course= teacherCourseService.findOneById(Long.valueOf(course_id));
		course.setPublish(GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		teacherCourseService.updateTeacherCourse(course);
		return "redirect:/admin/teacher/course/list";
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/course/edit/{course_id}/cancelpublish")
	public String cancelPublish(@PathVariable Long course_id){
		TeacherCourse course= teacherCourseService.findOneById(Long.valueOf(course_id));
		course.setPublish(GlobalDefs.PUBLISH_NUM_ADMIN);
		teacherCourseService.updateTeacherCourse(course);
		return "redirect:/admin/teacher/course/list";
	}
	
	@RequestMapping(value="/admin/teacher/course/edit/{course_id}/preview")
	public String previewCourse(@PathVariable Long course_id,Model model){
		TeacherCourse course= teacherCourseService.findOneById(Long.valueOf(course_id));
		List<CourseResource> listResource = courseResourceService.getResourceByCourseId(Long.valueOf(course_id));
		List<CourseResource> courseList;
		Map<String, List<CourseResource>> courseMap = new TreeMap<String, List<CourseResource>>();
		String LessonNum = null;
		for (CourseResource courseResource : listResource) {
			LessonNum = courseResource.getLessonNum();
			courseList= new ArrayList<CourseResource>();
			courseList = courseResourceService
					.getResourceByLessonNumAndCourseId(LessonNum,Long.valueOf(course_id));
			courseMap.put(LessonNum, courseList);
		}
		model.addAttribute("resourceCount", listResource.size());
		model.addAttribute("courseMap", courseMap);
		model.addAttribute("course", course);
		return "admin.teacher.course.preview";
	}

	@RequestMapping(value="/checkCoursePwd")
	public String checkCoursePwd(@RequestParam("cid") Long course_id,@RequestParam("coursepwd") String pwd,HttpServletRequest request,HttpServletResponse response ) throws IOException{
		logger.info("==== into the ajax checkCoursePwd controller ===="+course_id+pwd);
		PrintWriter out = response.getWriter();
		TeacherCourse course = teacherCourseService.findOneById(course_id);
		boolean flag;
		if(!pwd.equals(course.getPwd())){
			flag = false;
		}else{
			flag = true;
		}
		out.print(flag);
		out.flush();
		out.close();
		return null;
	}
	/**
	 * show the course in courses
	 * @param course_id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/teacher/course/edit/{course_id}/pubcourses")
	public String publishToCourses(@PathVariable Long course_id,Model model){
		TeacherCourse course = teacherCourseService.findOneById(Long.valueOf(course_id));
		course.setStatus(GlobalDefs.STATUS_CCWEB_COURSES);
		teacherCourseService.updateTeacherCourse(course);
		return "redirect:/admin/teacher/course/view/"+Long.valueOf(course_id);
	}

	
	@RequestMapping(value="/admin/teacher/course/edit/addlessonnum",method=RequestMethod.POST)
	public String addNewLessonNum(@RequestParam("courseId") Long course_id,Model model){
		List<CourseLesson> lessonList = lessonService.getMaxLessonNumByCourseId(Long.valueOf(course_id));
		String lessonNum = "0";
		if(lessonList.size()>0){
			lessonNum = lessonList.get(0).getLessonNum();
			lessonList.get(0).setStatus(null);
			lessonService.createCourseLesson(lessonList.get(0));
		}
		String newLessonNum = "";
		newLessonNum = Integer.parseInt(lessonNum)+1+"";
		CourseLesson courselesson = new CourseLesson();
		courselesson.setLessonNum(newLessonNum);
		courselesson.setCourseId(Long.valueOf(course_id));
		courselesson.setStatus("max");
		lessonService.createCourseLesson(courselesson);
		return "redirect:/admin/teacher/course/edit/"+Long.valueOf(course_id)+"/modifycourse";
	}
	
	
	@RequestMapping(value="/admin/teacher/course/edit/courselesson/destory",method=RequestMethod.POST)
	public String modifyCourseResource(@RequestParam("lessonId") Long lesson_id,@RequestParam("courseId") Long course_id){
		CourseLesson bigLesson = lessonService.findOne(lesson_id);
		logger.info("===================="+bigLesson.getLessonNum());
		if(Integer.parseInt(bigLesson.getLessonNum())>=2){
			String smallLessonNum = Integer.parseInt(bigLesson.getLessonNum())-1+"";
			logger.info("--------------------"+smallLessonNum);
			List<CourseLesson> smallLessonList = lessonService.findCourseLessonByCourseIdAndLessonNum(course_id, smallLessonNum);
			logger.info("++++++++++++++++++++++++++"+smallLessonList.get(0).getLessonNum());
			if(smallLessonList.size()>0){
				smallLessonList.get(0).setLessonNum("max");
				lessonService.createCourseLesson(smallLessonList.get(0));
			}
		}
		lessonService.destory(Long.valueOf(lesson_id));
		return "redirect:/admin/teacher/course/edit/"+Long.valueOf(course_id)+"/modifycourse";
	}
	

	@RequestMapping(value = "/admin/teacher/course/courseInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse courseFormAjaxJson(@Valid TeacherCourseInfoForm teacherCourseInfoForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}

	
	/*	
	@RequestMapping(value="/admin/teacher/allCourse/list")
	public String teacherAllCourseInfo(HttpSession session,Model model ){
		logger.info("#####Into TeacherCourseInfoPageController#####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		//Teacher teacher = teacherService.findOne(userInfo.getId());
		List<CourseBeans> courseBean = teacherCourseService.getAllCourseBeans();
		model.addAttribute("cb", courseBean);
		return "admin.teacher.allCourse.list";
	}
	
	@RequestMapping(value="/admin/teacher/course/all/list")
	public String teacherAllCourseList(HttpSession session,Model model,@RequestParam("school") String schoolName) throws Exception{
		logger.info("#####Into TeacherCourseInfoPageController#####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<String> school = teacherCourseService.getAllSchool();
		schoolName = new String(schoolName.getBytes("iso-8859-1"),"utf-8"); 
		logger.info("+++++++++++++++++++++++"+schoolName+"++++++++++++++++++++");
		List<CourseBeans> courseBean = teacherCourseService.getAllCourseBeans();
		List<CourseBeans> newCourseBeans = new ArrayList<CourseBeans>();
		if(schoolName.trim() !=null && !schoolName.trim().equals("all")){
			List<Teacher> teacherList = teacherCourseService.getAllCourseTeacher(schoolName);
			for(CourseBeans c :courseBean){
				if(schoolName.equals(c.getTeacher().getCollege())){
					newCourseBeans.add(c);
				}
			}
			model.addAttribute("schoolName", schoolName);
			model.addAttribute("cb", newCourseBeans);
			model.addAttribute("school", school);
			model.addAttribute("teacherList", teacherList);
			return "admin.teacher.course.all.list";
		}else if(schoolName.trim().equals("all")){
			model.addAttribute("cb", courseBean);
			model.addAttribute("school", school);
			return "admin.teacher.course.all.list";
		}else{
			model.addAttribute("cb", courseBean);
			model.addAttribute("school", school);
			return "admin.teacher.course.all.list";
		}
		
	} */
}
