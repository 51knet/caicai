package com.knet51.ccweb.controllers.front.enterprise;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserCourseBeans;
import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.AnnoPhoto;
import com.knet51.ccweb.jpa.entities.Announcement;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseType;
import com.knet51.ccweb.jpa.entities.courses.Course;
import com.knet51.ccweb.jpa.entities.courses.UserCourse;
import com.knet51.ccweb.jpa.services.AnnoPhotoService;
import com.knet51.ccweb.jpa.services.AnnouncementService;
import com.knet51.ccweb.jpa.services.CourseTypeService;
import com.knet51.ccweb.jpa.services.CourseService;
import com.knet51.ccweb.jpa.services.UserCourseService;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class EnterpriseCourseFrontPageController {
	private static Logger logger = LoggerFactory
			.getLogger(EnterpriseCourseFrontPageController.class);
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	@Autowired
	private CourseTypeService courseTypeService;
	@Autowired
	private UserCourseService userCourseService;
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private AnnoPhotoService annoPhotoService;
	
//	/**
//	 * show course detail info in enterprise front page
//	 * @param user_id
//	 * @param course_id
//	 * @param pwd
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/enterprise/course/view", method = RequestMethod.POST)
//	public String detailCourse(@RequestParam("enterpriseId") Long user_id,
//			@RequestParam("courseId") Long course_id,
//			@RequestParam("coursepwd") String pwd, Model model) {
//			TeacherCourse course = courseService.findOneById(course_id);
//			User user = userService.findOne(user_id);
//			UserInfo userInfo = new UserInfo(user);
//			logger.debug(userInfo.toString());
//			model.addAttribute("userInfo", userInfo);
//			model.addAttribute("user_id", user_id);
//			model.addAttribute("course", course);
//			List<CourseType> cTypeList = courseTypeService.findAll();
//			model.addAttribute("cTypeList", cTypeList);
//			
//			List<TeacherCourse> courseList = courseService
//					.getAllTeacherCourseByUseridAndPublish(user_id,
//							GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
//			model.addAttribute("courseList", courseList);
//			model.addAttribute("courseCount", courseList.size());
//			return "enterprise.course.view";
//	}
	
	@RequestMapping(value="/enterprise/{user_id}/course/view/{course_id}")
	public String showCourseDetainInfo(	@PathVariable Long user_id, @PathVariable Long course_id, Model model){
		Course course = courseService.findOneById(course_id);
		User user = userService.findOne(user_id);
		UserInfo userInfo = new UserInfo(user);
		logger.debug(userInfo.toString());
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("user_id", user_id);
		model.addAttribute("course", course);
		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);
		
		List<Course> courseList = courseService
				.getAllTeacherCourseByUseridAndPublish(user_id,
						GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());
		
		List<UserCourse> userCourseList = userCourseService.findByTeachercourseid(course_id);
		model.addAttribute("userCourseList", userCourseList);
		model.addAttribute("userCourseCount", userCourseList.size());
		
		List<UserCourseBeans> CourseComment = new ArrayList<UserCourseBeans>();
		Page<UserCourse> onePage = userCourseService
				.findUserCourseByTeachercourseid(0, 20,
						course_id);
		UserCourseBeans UserCourseBeans;
		for (int i = 0; i < onePage.getContent().size(); i++) {
			UserCourseBeans = new UserCourseBeans();
			long userid = onePage.getContent().get(i).getUserid();
			User buyer = userCourseService.findByUserId(userid);
			UserCourse comm = onePage.getContent().get(i);
			String userName = buyer.getName();
			String photoUrl = buyer.getPhoto_url();
			UserCourseBeans.setUserCourse(comm);
			UserCourseBeans.setPhotoUrl(photoUrl);
			UserCourseBeans.setUserName(userName);
			CourseComment.add(UserCourseBeans);
		}
		model.addAttribute("CourseComment", CourseComment);
		
		return "enterprise.course.view";
	}
	
	/**
	 * show enterprise course list
	 * 
	 * @param id
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/enterprise/{user_id}/course/list")
	public String getAllEnterpriseCourse(
			@PathVariable Long user_id,
			Model model,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
		User user = userService.findOne(user_id);
		UserInfo userInfo = new UserInfo(user);
		logger.debug(userInfo.toString());
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("user_id", user_id);
		Page<Announcement> annoPage = announcementService
				.findAllAnnoByUser(0, 4, user);
		model.addAttribute("annolist", annoPage.getContent());
		List<Announcement> annoList = announcementService
				.findAllByUid(user_id);
		model.addAttribute("annoCount", annoList.size());
		List<AnnoPhoto> annoPhoto = annoPhotoService
				.findAnnoPhotoByUserid(user.getId());
		model.addAttribute("annoPhoto", annoPhoto);
		
		
		Page<Course> onePage = courseService
				.findTeacherCourseByUserAndPublish(pageNumber, pageSize, user,
						GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("page", onePage);

		List<Course> courseList = courseService
				.getAllTeacherCourseByUseridAndPublish(user_id,
						GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());

		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);

		return "enterprise.course.list";
	}

	/**
	 * filter the course by course type
	 * 
	 * @param teacher_id
	 * @param type_id
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/enterprise/{user_id}/course/type/{type_id}")
	public String filterCourseByType(
			@PathVariable Long user_id,
			@PathVariable Long type_id,
			Model model,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
		User user = userService.findOne(user_id);
		// Teacher teacher = teacherService.findOne(enterprise_id);
		CourseType cType = courseTypeService.findOneById(type_id);
		UserInfo userInfo = new UserInfo(user);
		logger.debug(userInfo.toString());
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("user_id", user_id);
		Page<Course> onePage = courseService
				.findTeacherCourseByUserAndPublishAndCType(pageNumber,
						pageSize, user, GlobalDefs.PUBLISH_NUM_ADMIN_FRONT,
						cType);
		model.addAttribute("page", onePage);

		List<Course> courseList = courseService
				.getAllTeacherCourseByUseridAndPublish(user_id,
						GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseCount", courseList.size());

		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);
		
		Page<Announcement> annoPage = announcementService
				.findAllAnnoByUser(0, 4, user);
		model.addAttribute("annolist", annoPage.getContent());
		List<Announcement> annoList = announcementService
				.findAllByUid(user_id);
		model.addAttribute("annoCount", annoList.size());
		List<AnnoPhoto> annoPhoto = annoPhotoService
				.findAnnoPhotoByUserid(user.getId());
		model.addAttribute("annoPhoto", annoPhoto);
		return "enterprise.course.list";
	}
	/**
	 * search course by courseName or min price or max price
	 * @param courseName
	 * @param min_price
	 * @param max_price
	 * @param user_id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/enterprise/searchCourse" ,method = RequestMethod.POST)
	public String findCourse(@RequestParam("courseName") String courseName,@RequestParam("minPrice") Long min_price,
			@RequestParam("maxPrice")Long max_price,@RequestParam("userid")Long user_id,Model model){
		logger.info("=== into enterprise search course conroller ===");
		String cName = courseName.trim();
		User user = userService.findOne(user_id);
		UserInfo userInfo = new UserInfo(user);
		int min  =  0;
		int max =  99999;
		if(min_price != null){
			min = min_price.intValue();
		}
		if(max_price !=null){
			max = max_price.intValue();
		}
		List<Course> courseList = courseService
				.getAllTeacherCourseByUseridAndPublish(user_id,GlobalDefs.PUBLISH_NUM_ADMIN_FRONT);
		List<Course> newCourseList = new ArrayList<Course>();
		for (int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getPrice().intValue()>=min && courseList.get(i).getPrice().intValue()<= max){
				if(cName !=null && !cName.equals("") && courseList.get(i).getCourseName().contains(cName)){
					newCourseList.add(courseList.get(i));
				}else{
					newCourseList.add(courseList.get(i));
				}
			}
		}
		model.addAttribute("newCourselist", newCourseList);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("user_id", user_id);
		model.addAttribute("courseCount", courseList.size());
		model.addAttribute("courseList", courseList);
		
		List<CourseType> cTypeList = courseTypeService.findAll();
		model.addAttribute("cTypeList", cTypeList);
		return "enterprise.search.course.list";
	}
	
}
