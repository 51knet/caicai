package com.knet51.ccweb.controllers.admin.user.order;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.UserOrder;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.Course;
import com.knet51.ccweb.jpa.services.CourseService;
import com.knet51.ccweb.jpa.services.OrderService;


@Controller
public class UserOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CourseService courseService;
	
	
	@RequestMapping(value="/admin/order/list")
	public String orderList(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		Page<UserOrder> myOrder = orderService.findOrderByUser(pageNumber, pageSize, user);
		model.addAttribute("page", myOrder);
		return "admin."+user.getRole()+".order.list";
	}
	
	@RequestMapping(value="/admin/order/view/{orderId}")
	public String orderDetail(HttpSession session,Model model ,@PathVariable Long orderId){
		UserOrder userOrder = orderService.findOne(orderId);
		Long course_id = Long.parseLong(userOrder.getCourseId());
		Course course = courseService.findOneById(course_id);
		User seller = course.getUser();
		model.addAttribute("course", course);
		model.addAttribute("seller", seller);
		model.addAttribute("order", userOrder);
		return "admin.order.detail";
	}
}
