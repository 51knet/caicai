package com.knet51.ccweb.controllers.teacher.course;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.services.CourseResourceService;
import com.knet51.ccweb.jpa.services.TeacherCourseService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
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
	
	@RequestMapping(value="/admin/teacher/course/list")
	public String teacherCourseInfo(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize){
		logger.info("#####Into TeacherCourseInfoPageController#####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Teacher teacher = teacherService.findOne(userInfo.getId());
		Page<TeacherCourse> onePage =teacherCourseService.findAllCourseByTeacher(pageNumber, pageSize, teacher);
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
		List<CourseResource> resourceList = courseResourceService.getAllCourseResourceById(course_id);
		model.addAttribute("course", course);
		model.addAttribute("resourceList",resourceList);
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
	int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize){
		User user = userService.findOne(teacher_id);
		Teacher teacher = teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		logger.debug(userInfo.toString());
		model.addAttribute("teacherInfo", userInfo);
		model.addAttribute("teacher_id", teacher_id);
		Page<TeacherCourse> onePage = teacherCourseService.findAllCourseByTeacher(pageNumber, pageSize, teacher);
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
		List<CourseResource> resourceList = courseResourceService.getAllCourseResourceById(course_id);
		model.addAttribute("course", course);
		model.addAttribute("resourceList",resourceList);
		model.addAttribute("resourceCount", resourceList.size());
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
	
	@RequestMapping(value="/admin/teacher/course/new/firststep",method=RequestMethod.POST)
	public String TeacherCourseAddInfo(@Valid TeacherCourseInfoForm courseInfoForm,
			BindingResult validResult, HttpSession session,MultipartHttpServletRequest request,Model model) throws Exception{
		logger.info("#### Into TeacherCourseAdd Controller ####");
		if(validResult.hasErrors()){
			logger.info("detailInfoForm Validation Failed " + validResult);
			return "redirect:/admin/teacher/course/addcourse?active=second";
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
			course.setStatus(1);
			course.setPublish(1);
			course.setTeacher(teacher);
			course.setCourseType(courseInfoForm.getCourseType());
			for(int i=0;i<files.size();i++){
				if(!files.get(i).isEmpty()){
					logger.info("Upload file name:"+files.get(i).getOriginalFilename()); 
					
					String fileName = files.get(i).getOriginalFilename();
					String fileType = fileName.substring(fileName.lastIndexOf("."));
					String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/course/"+courseName;
					FileUtil.createRealPath(path, session);
					String previewFile = path+"/small"+fileType;
					String saveName = FileUtil.saveFile(files.get(i).getInputStream(), fileName, path);
					FileUtil.getPreviewImage(new File(path+"/"+saveName), new File(previewFile), fileType.substring(1));
					String savePath = FileUtil.getSavePath("course", userInfo.getId(), courseName, request)+"/"+"small"+fileType;
					//String savePath = request.getContextPath()+"/course/"+userInfo.getId()+"/"+courseName+"/"+saveName;
					course.setCourseCover(savePath);
				}
			}
			
			TeacherCourse newCourse = teacherCourseService.createTeacherCourse(course);
			return "redirect:/admin/teacher/course/addcourse?active=second&cid="+newCourse.getId();
		}
	
	}
	
	@RequestMapping(value="/admin/teacher/course/new/secondstep",method=RequestMethod.POST)
	public String addCourseSecond(@RequestParam("cid") Long course_id,
			@RequestParam("pwd") String pwd, @RequestParam("status") Integer status, Model model){
		TeacherCourse course = teacherCourseService.findOneById(course_id);
		course.setPwd(pwd);
		course.setStatus(status);
		teacherCourseService.updateTeacherCourse(course);
		return "redirect:/admin/teacher/course/addcourse?active=third&cid="+course_id;
	}
	
	@RequestMapping(value="/admin/teacher/course/new/thirdstep",method=RequestMethod.POST)
	public String addCourseThird(HttpSession session,Model model,
			MultipartHttpServletRequest request,@RequestParam("cid") Long course_id) throws  Exception{
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<MultipartFile> files = request.getFiles("resourceFile");
		String resourceDesc = request.getParameter("resourceDesc");
		String resourceOrder = request.getParameter("resourceOrder");
		for(int i=0;i<files.size();i++){
			if(!files.get(i).isEmpty()){
				CourseResource resource = new CourseResource();
				logger.info("Upload file name:"+files.get(i).getOriginalFilename()); 
				
				String fileName = files.get(i).getOriginalFilename();
				String name = fileName.substring(0, fileName.indexOf("."));
				resource.setFileName(name);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String date = format.format(new Date());
				resource.setDate(date);
				
				TeacherCourse teacherCourse = teacherCourseService.findOneById(course_id);
				//String realPath = FileUtil.getPath("courseResource", userInfo.getId(), teacherCourse.getCourseName(), session);
				String path = session.getServletContext().getRealPath("/")+"/resources/attached/"+userInfo.getId()+"/course/"+teacherCourse.getCourseName()+"/"+resourceOrder;
				FileUtil.createRealPath(path, session);
				String saveName = FileUtil.saveFile(files.get(i).getInputStream(), fileName, path);
				String savePath = path+"\\"+saveName;
				resource.setSavePath(savePath);
				resource.setSaveName(saveName);
				resource.setResourceDesc(resourceDesc);
				resource.setResourceOrder(resourceOrder);
				resource.setCourse_id(course_id);
				courseResourceService.createCourseResource(resource);
			}
		}
		return "redirect:/admin/teacher/course/list";
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
		
		
	}  */
}
