package com.knet51.diplomat.controllers.admin.consult;

import java.util.Date;

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
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.consult.ConsultComment;
import com.knet51.ccweb.jpa.entities.consult.ConsultOrder;
import com.knet51.diplomat.beans.UserInfo;
import com.knet51.diplomat.controllers.common.defs.GlobalDefs;
import com.knet51.diplomat.jpa.services.TeacherService;
import com.knet51.diplomat.jpa.services.UserService;
import com.knet51.diplomat.jpa.services.consult.ConsultCommentService;
import com.knet51.diplomat.jpa.services.consult.ConsultOrderService;


@Controller
public class ConsultController {

	private static final Logger logger = LoggerFactory.getLogger(ConsultController.class);
	@Autowired
	private ConsultOrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ConsultCommentService commentService;
	
	@RequestMapping(value="/admin/consult/order/list")
	public String showConsultOrder(HttpSession session,@RequestParam(value="pageNumber", defaultValue="0") int pageNumber, 
			@RequestParam(value="pageSize", defaultValue="20")int pageSize,Model model){
		User user = getSessionUser(session);
		Page<ConsultOrder> page = orderService.findAllByConsulter(pageNumber, pageSize, user);
		model.addAttribute("page", page);
		return "admin."+user.getRole()+".consult.order.list";
	}
	
	@RequestMapping(value="/admin/consult/order/view/{order_id}")
	public String showConsultOrderDetail(@PathVariable Long order_id,HttpSession session,Model model){
		User user = getSessionUser(session);
		ConsultOrder order = orderService.findOne(order_id);
		
		if(!order.getConsulter().getId().equals(user.getId())){
			return "redirect:/admin/consult/orser/list";
		}else{
			ConsultComment comment = commentService.findOneByOrderAndConsulter(user, order);
			Teacher teacher = teacherService.findOne(order.getTeacher().getId());
			model.addAttribute("comment", comment);
			model.addAttribute("order", order);
			model.addAttribute("teacherInfo", teacher);
			return "admin."+user.getRole()+".consult.order.view";
		}
		
	}
	
	@RequestMapping(value="/admin/consult/order/comment", method = RequestMethod.POST)
	public String addConsultComment(@Valid ConsultCommentForm commentForm, BindingResult result,HttpSession session,
			@RequestParam("order_id") Long order_id){
			ConsultComment comment = new ConsultComment();
			ConsultOrder order = orderService.findOne(order_id);
			User user = getSessionUser(session);
			User teacher = order.getTeacher();
			comment.setConsulter(user);
			comment.setOrder(order);
			comment.setTeacher(teacher);
			comment.setDate(new Date());
			comment.setScore(commentForm.getScore());
			String content = commentForm.getContent();
			if(content.trim()== null || content.trim().equals("")){
				content="咨询效果很好，很满意";
			}
			comment.setContent(content);
			commentService.create(comment);
			return "redirect:/admin/consult/order/view/"+order_id;		
	}
	
	public User getSessionUser(HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		return userInfo.getUser();
	}
}
