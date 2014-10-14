package com.knet51.diplomat.controllers.admin.kefu.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.UserOrder;
import com.knet51.ccweb.jpa.entities.projects.Projects;
import com.knet51.diplomat.jpa.services.UserService;
import com.knet51.diplomat.jpa.services.projects.ProjectsService;
import com.knet51.diplomat.jpa.services.trade.OrderService;

@Controller
public class KefuOrderPageController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectsService projectsService;
	
	@RequestMapping(value="/admin/kefu/order/list")
	public String showUserOrderPage(Model model,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber, 
			@RequestParam(value="pageSize", defaultValue="20") int pageSize  ){
		Page<UserOrder> page = orderService.findAll(pageNumber, pageSize);
		model.addAttribute("page", page);
		return "admin.kefu.order.list";
	}
	
	@RequestMapping(value="/admin/kefu/user/{u_id}/order/list")
	public String showSomebodyOrderPage(@PathVariable Long u_id,Model model,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber, 
			@RequestParam(value="pageSize", defaultValue="20") int pageSize){
		User user = userService.findOne(u_id);
		Page<UserOrder> page = orderService.findOrderByUser(pageNumber, pageSize, user);
		model.addAttribute("page", page);
		model.addAttribute("buyer", user); 
		return "admin.kefu.user.order.list";
	}
	
	@RequestMapping(value="/admin/kefu/order/view/{id}")
	public String showOrderDetail(Model model,@PathVariable Long id){
		UserOrder order = orderService.findOne(id);
		Projects project = projectsService.findOne(order.getProjects().getId());
		model.addAttribute("order", order);
		model.addAttribute("projects", project);
		return "admin.kefu.order.view";
	}
}
