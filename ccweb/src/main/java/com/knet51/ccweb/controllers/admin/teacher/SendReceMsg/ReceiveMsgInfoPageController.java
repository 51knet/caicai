package com.knet51.ccweb.controllers.admin.teacher.sendReceMsg;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.ReceiveMsg;
import com.knet51.ccweb.jpa.entities.SendMsg;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.ReceiveMsgService;
import com.knet51.ccweb.jpa.services.SendMsgService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;

@Controller
public class ReceiveMsgInfoPageController {
	private Logger logger = LoggerFactory.getLogger(ReceiveMsgInfoPageController.class);
	
	@Autowired
	private ReceiveMsgService receiveMsgService;
	
	@Autowired
	private SendMsgService sendMsgService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/admin/message/list")
	public String receiveMsgList(Model model,HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		logger.info("####  Into ReceiveMsgList page  ####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Long user_id = userInfo.getId();
		User user = userService.findOne(user_id);
		Page<ReceiveMsg> page = receiveMsgService.findIsReadMsgByUser(pageNumber, pageSize, user, 1 );
		List<ReceiveMsg> unReadMsgList =  receiveMsgService.unReadList(user_id);
		List<ReceiveMsg> isReadMsgList = receiveMsgService.isReadList(user_id);
		List<ReceiveMsg> isDele = receiveMsgService.isDele(user_id);
		List<ReceiveMsg> msgList = receiveMsgService.list(user_id);
		Integer unReadCount = unReadMsgList.size();
		Integer isReadCount = isReadMsgList.size();
		Integer msgCount = msgList.size();
		Integer isDeleCount = isDele.size();
		model.addAttribute("unReadCount", unReadCount);
		model.addAttribute("isReadCount", isReadCount);
		model.addAttribute("msgCount", msgCount);
		model.addAttribute("isDeleCount",isDeleCount);
		//model.addAttribute("unReadMsgList", unReadMsgList);
		model.addAttribute("page", page);
		if (userInfo.getUser().getRole().equals("teacher")) {
			return "admin.teacher.message.detail";
		} else if (userInfo.getUser().getRole().equals("enterprise")) {
			return "admin.enterprise.message.detail";
		} else if (userInfo.getUser().getRole().equals("user")) {
			return "admin.user.message.detail";
		}else {
			return "404";
		}
	}
	
	@Transactional
	@RequestMapping(value="/admin/message/detailOne")
	public String receiveMsgDetailOne(@RequestParam("mid") Long mid, @RequestParam("urmid") Long urmId,Model model,HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		SendMsg sendMsg = sendMsgService.detail(mid);
		receiveMsgService.isRead(urmId);
		Long senderId = sendMsg.getUser().getId();
		//System.out.println(senderId);
		model.addAttribute("sendMsg", sendMsg);
		model.addAttribute("senderId", senderId);
		model.addAttribute("urmId", urmId);
		if (userInfo.getUser().getRole().equals("teacher")) {
			return "admin.teacher.message.send";
		} else if (userInfo.getUser().getRole().equals("enterprise")) {
			return "admin.enterprise.message.send";
		} else if (userInfo.getUser().getRole().equals("user")) {
			return "admin.user.message.send";
		}else {
			return "404";
		}
	}
	
	@Transactional
	@RequestMapping(value="/admin/message/isRead")
	public String isReadMsg(Model model,HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Long user_id = userInfo.getId();
		User user = userService.findOne(user_id);
		Page<ReceiveMsg> page = receiveMsgService.findIsReadMsgByUser(pageNumber, pageSize, user, 2);
		List<ReceiveMsg> unReadMsgList =  receiveMsgService.unReadList(user_id);
		List<ReceiveMsg> isReadMsgList = receiveMsgService.isReadList(user_id);
		List<ReceiveMsg> isDele = receiveMsgService.isDele(user_id);
		List<ReceiveMsg> msgList = receiveMsgService.list(user_id);
		Integer unReadCount = unReadMsgList.size();
		Integer isReadCount = isReadMsgList.size();
		Integer msgCount = msgList.size();
		Integer isDeleCount = isDele.size();
		model.addAttribute("unReadCount", unReadCount);
		model.addAttribute("isReadCount", isReadCount);
		model.addAttribute("msgCount", msgCount);
		model.addAttribute("isDeleCount",isDeleCount);
		//model.addAttribute("isReadMsgList", isReadMsgList);
		model.addAttribute("page", page);
		if (userInfo.getUser().getRole().equals("teacher")) {
			return "admin.teacher.message.isReadDetail";
		} else if (userInfo.getUser().getRole().equals("enterprise")) {
			return "admin.enterprise.message.isReadDetail";
		} else if (userInfo.getUser().getRole().equals("user")) {
			return "admin.user.message.isReadDetail";
		}else {
			return "404";
		}
	}
	
	@Transactional
	@RequestMapping(value="/admin/message/isDele")
	public String isDeleMsg(Model model,HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Long user_id = userInfo.getId();
		User user = userService.findOne(user_id);
		Page<ReceiveMsg> page = receiveMsgService.findIsReadMsgByUser(pageNumber, pageSize, user, 3);
		List<ReceiveMsg> unReadMsgList =  receiveMsgService.unReadList(user_id);
		List<ReceiveMsg> isReadMsgList = receiveMsgService.isReadList(user_id);
		List<ReceiveMsg> isDele = receiveMsgService.isDele(user_id);
		List<ReceiveMsg> msgList = receiveMsgService.list(user_id);
		Integer unReadCount = unReadMsgList.size();
		Integer isReadCount = isReadMsgList.size();
		Integer msgCount = msgList.size();
		Integer isDeleCount = isDele.size();
		model.addAttribute("unReadCount", unReadCount);
		model.addAttribute("isReadCount", isReadCount);
		model.addAttribute("msgCount", msgCount);
		model.addAttribute("isDeleCount",isDeleCount);
		//model.addAttribute("isReadMsgList", isReadMsgList);
		model.addAttribute("page", page);
		if (userInfo.getUser().getRole().equals("teacher")) {
			return "admin.teacher.message.isDeleDetail";
		} else if (userInfo.getUser().getRole().equals("enterprise")) {
			return "admin.enterprise.message.isDeleDetail";
		} else if (userInfo.getUser().getRole().equals("user")) {
			return "admin.user.message.isDeleDetaill";
		}else {
			return "404";
		}
	}
	
	@Transactional
	@RequestMapping(value="/admin/message/deleOneReaded",method = RequestMethod.POST)
	public String deleMsgFromReaded(@RequestParam("mId") Long mid,Model model){
		logger.info("===================deleReaded"+mid);
		receiveMsgService.del(mid);
		return "redirect:/admin/message/isRead";
	}
	
	@Transactional
	@RequestMapping(value="/admin/message/deleOne",method = RequestMethod.POST)
	public String deleMsgFromUnReaded(@RequestParam("mId") Long mid,Model model){
		logger.info("===================deleOne"+mid);
		receiveMsgService.del(mid);
		return "redirect:/admin/message/list";
	}
	
	@Transactional
	@RequestMapping(value="/admin/message/destory",method = RequestMethod.POST)
	public String destory(@RequestParam("mId") Long mid,Model model){
		receiveMsgService.destory(Long.valueOf(mid));
		return "redirect:/admin/message/isDele";
	}
	
	@RequestMapping(value = "/admin/message/receiveMsgInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse receiveMsgInfoFormAjaxJson(@Valid SendMsgInfoForm sendMsgInfoForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
}
