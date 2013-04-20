package com.knet51.ccweb.controllers.admin.user.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Order;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.OrderService;


@Controller
public class UserOrderController {
	
	public static final long MAX_FILE_SIZE_2M = 2*1024*1024;
	
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping(value="/admin/orderList")
	public String orderList(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		User user = userInfo.getUser();
		Order userOrder = new Order();
		userOrder.setUser(user);
		userOrder.setDescription("for test");
		orderService.createOrder(userOrder);
		List<Order> orderList = new ArrayList<Order>();
		Page<Order> myOrder = orderService.findAllbyUser(pageNumber, pageSize, user);
		for (Order order : myOrder) {
			orderList.add(order);
		}
		model.addAttribute("orderList", orderList);
		model.addAttribute("page", myOrder);
		return "admin.order.list";
	}
}
