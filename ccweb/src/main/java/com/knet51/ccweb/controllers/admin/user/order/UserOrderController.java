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
import com.knet51.ccweb.jpa.services.OrderService;


@Controller
public class UserOrderController {
	
	public static final long MAX_FILE_SIZE_2M = 2*1024*1024;
	
	@Autowired
	private OrderService orderService;
	
	
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
		UserOrder userOder = orderService.findOne(orderId);
		model.addAttribute("userOrder", userOder);
		return "admin.order.detail";
	}
}
