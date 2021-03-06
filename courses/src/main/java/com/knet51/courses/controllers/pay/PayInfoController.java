package com.knet51.courses.controllers.pay;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.UserOrder;
import com.knet51.ccweb.jpa.entities.UserRight;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.courses.beans.UserInfo;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.UserRightService;
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
	@Autowired
	private UserRightService userRightService;
	
	@RequestMapping(value = "/projects/cart/view/{p_id}")
	public String cartPage(@PathVariable Long p_id, Model model,
			HttpSession session, HttpServletRequest request) {
		try {
			boolean pass = false; 
			model.addAttribute("projects_id", p_id);
			Projects projects = projectsService.findOne(p_id);
			User seller = projects.getUser();
			UserInfo userInfo = (UserInfo) session
					.getAttribute(GlobalDefs.SESSION_USER_INFO);
			if(userInfo != null){
				List<UserRight> rightList = userRightService.findUserRightListByUser(userInfo.getUser());
				for (UserRight userRight : rightList) {
					if((userRight.getUserRight().equals("investor") || userRight.getUserRight().equals("ledinvestor"))
							&& (!projects.getMaxInvestNum().equals(projects.getCurrentInvestNum()) || !projects.getCurrentMoney().equals(projects.getTotalMoney())) 
							&& !userInfo.getId().equals(seller.getId())){
						pass = true;
					}
				}
				if(pass){
					model.addAttribute("projects", projects);
					model.addAttribute("seller", seller);
					return "projects.cart.view";
				}else{
					return "redirect:/" ;
				}
					
			}else{
				return "redirect:/" ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";

	}
	
	@RequestMapping(value = "/projects/pay/view", method = RequestMethod.POST)
	public String payPage( Model model,HttpSession session, 
			HttpServletRequest request,@RequestParam("projects_id") Long projects_id) {
		try {
			UserInfo userInfo = (UserInfo) session
					.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Projects projects = projectsService.findOne(projects_id);
			User seller = projects.getUser();
			model.addAttribute("projects", projects);
			model.addAttribute("seller", seller);
			
			boolean paySuccessful = false;
			UserOrder userOrder = new UserOrder();
			userOrder.setUser(userInfo.getUser());
			userOrder.setStatus("未支付");
			userOrder.setStartTime(new Date());
			userOrder.setProjects(projects);
			userOrder = orderService.createOrder(userOrder);
			String password = "";
			String enterPassword = request.getParameter("password");
			model.addAttribute("projects_id", projects_id);
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
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "projects.pay.view";
	}
	
	

}
