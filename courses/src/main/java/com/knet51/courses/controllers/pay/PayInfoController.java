package com.knet51.courses.controllers.pay;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.UserOrder;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.courses.beans.UserInfo;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.UserService;
import com.knet51.courses.jpa.services.projects.ProjectsService;
import com.knet51.courses.jpa.services.trade.OrderService;

@Controller
public class PayInfoController {
	
	@Autowired
	private ProjectsService projectsService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/projects/cart/view/{projects_id}")
	public String cartPage(@PathVariable Long projects_id, Model model,
			HttpSession session, HttpServletRequest request) {
		
		model.addAttribute("projects_id", projects_id);
		Projects projects = projectsService.findOne(projects_id);
		User seller = projects.getUser();
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		try {
			if (userInfo != null) {
				UserOrder userOrder = new UserOrder();
				userOrder.setUser(userInfo.getUser());
				userOrder.setStatus("未支付");
				userOrder.setStartTime(new Date());
				userOrder.setProjects_id(projects_id);
				userOrder = orderService.createOrder(userOrder);
				model.addAttribute("projects", projects);
				model.addAttribute("seller", seller);
				model.addAttribute("orderId", userOrder.getId().toString());
				return "projects.cart.view";
			}else{
				return "redirect:/";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/projects/pay/view/{order_id}", method = RequestMethod.POST)
	public String payPage(@PathVariable Long order_id, Model model,
			HttpSession session, HttpServletRequest request) {
		boolean paySuccessful = false;
		UserOrder userOrder = orderService.findOne(order_id);
		Long projects_id = userOrder.getProjects_id();
		Projects projects = projectsService.findOne(projects_id);
		User seller = projects.getUser();
		model.addAttribute("projects", projects);
		model.addAttribute("seller", seller);
		String password = "";
		String enterPassword = request.getParameter("password");
		model.addAttribute("projects_id", projects_id);
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (userInfo != null) {
			password = userInfo.getUser().getPassword();
			if (password.equals(enterPassword)) {
				userOrder.setStatus("完成");
				userOrder.setEndTime(new Date());
				userOrder = orderService.updateOrder(userOrder);
				paySuccessful = true;
			}
		}
		model.addAttribute("paySuccessful", paySuccessful);
		return "projects.pay.view";
	}

}
