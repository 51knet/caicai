package com.knet51.ccweb.controllers.admin.user.courses;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.UserOrder;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.Course;
import com.knet51.ccweb.jpa.entities.courses.UserCourse;
import com.knet51.ccweb.jpa.services.UserCourseService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.course.CourseResourceService;
import com.knet51.ccweb.jpa.services.course.CourseService;
import com.knet51.ccweb.jpa.services.trade.OrderService;

@Controller
public class UserPayController {

	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserCourseService userCourseService;
	@Autowired
	private CourseResourceService courseResourceService;
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/course/pay/view/{order_id}", method = RequestMethod.POST)
	public String payPage(@PathVariable Long order_id, Model model,
			HttpSession session, HttpServletRequest request) {
		boolean paySuccessful = false;
		UserOrder userOrder = orderService.findOne(order_id);
		Long course_id = Long.valueOf(userOrder.getCourseId());
		Course course = courseService.findOneById(course_id);
		User seller = course.getUser();
		model.addAttribute("course", course);
		model.addAttribute("seller", seller);
		String password = "";
		String enterPassword = request.getParameter("password");
		model.addAttribute("courseId", course_id);
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user;
		if (userInfo != null) {
			user = userService.findByEmailAddress(userInfo.getEmail());
			password = user.getPassword();
			UserCourse userCourse = userCourseService
					.findByTeachercourseidAndUserid(course_id,
							userInfo.getId());
			if (userCourse == null) {
				if (password.equals(enterPassword)) {
					userCourse = new UserCourse();
					userCourse.setTeachercourseid(course_id);
					userCourse.setUserid(userInfo.getId());
					userCourseService.save(userCourse);
					userOrder.setStatus("完成");
					userOrder.setEndTime(new Date());
					userOrder = orderService.updateOrder(userOrder);
					paySuccessful = true;
				}
			}else{
				paySuccessful = true;
			}
		}
		model.addAttribute("paySuccessful", paySuccessful);
		return "course.pay.view";
	}

	@RequestMapping(value = "/course/cart/view/{course_id}")
	public String cartPage(@PathVariable Long course_id, Model model,
			HttpSession session, HttpServletRequest request) {
		model.addAttribute("courseId", course_id);
		Course course = courseService.findOneById(course_id);
		User seller = course.getUser();
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user;
		if (userInfo != null) {
			user = userService.findByEmailAddress(userInfo.getEmail());
			UserCourse userCourse = userCourseService
					.findByTeachercourseidAndUserid(course_id,
							userInfo.getId());
			if (userCourse == null) {
					UserOrder userOrder = new UserOrder(user, course_id.toString());
					userOrder.setStatus("未支付");
					userOrder.setStartTime(new Date());
					userOrder = orderService.createOrder(userOrder);
					model.addAttribute("course", course);
					model.addAttribute("seller", seller);
					model.addAttribute("orderId", userOrder.getId().toString());
					return "course.cart.view";
			}else{
				return "redirect:/";
			}
		}else{
			return "redirect:/";
		}
	}
}
