package com.knet51.ccweb.controllers.teacher.SendReceMsg;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.ReceiveMsg;
import com.knet51.ccweb.jpa.entities.SendMsg;
import com.knet51.ccweb.jpa.services.ReceiveMsgService;
import com.knet51.ccweb.jpa.services.SendMsgService;

@Controller
public class ReceiveMsgInfoPageController {
	private Logger logger = LoggerFactory.getLogger(ReceiveMsgInfoPageController.class);
	
	@Autowired
	private ReceiveMsgService receiveMsgService;
	
	@Autowired
	private SendMsgService sendMsgService;
	
	@RequestMapping(value="/admin/teacher/message/list")
	public String receiveMsgList(Model model,HttpSession session){
		logger.info("####  Into ReceiveMsgList page  ####");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Long userId = userInfo.getId();
		List<ReceiveMsg> unReadMsgList =  receiveMsgService.unReadList(userId);
		List<ReceiveMsg> isReadMsgList = receiveMsgService.isReadList(userId);
		List<ReceiveMsg> isDele = receiveMsgService.isDele(userId);
		List<ReceiveMsg> msgList = receiveMsgService.list(userId);
		Integer unReadCount = unReadMsgList.size();
		Integer isReadCount = isReadMsgList.size();
		Integer msgCount = msgList.size();
		Integer isDeleCount = isDele.size();
		model.addAttribute("unReadCount", unReadCount);
		model.addAttribute("isReadCount", isReadCount);
		model.addAttribute("msgCount", msgCount);
		model.addAttribute("isDeleCount",isDeleCount);
		model.addAttribute("unReadMsgList", unReadMsgList);
		return "admin.teacher.message.detail";
	}
	
	@Transactional
	@RequestMapping(value="/admin/teacher/message/detailOne")
	public String receiveMsgDetailOne(@RequestParam("mid") Long mid, @RequestParam("urmid") Long urmId,Model model){
		SendMsg sendMsg = sendMsgService.detail(mid);
		receiveMsgService.isRead(urmId);
		Long senderId = sendMsg.getUser().getId();
		//System.out.println(senderId);
		model.addAttribute("sendMsg", sendMsg);
		model.addAttribute("senderId", senderId);
		model.addAttribute("urmId", urmId);
		return "admin.teacher.message.send";
	}
	
	@RequestMapping(value="/admin/teacher/message/isRead")
	public String isReadMsg(Model model,HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Long userId = userInfo.getId();
		List<ReceiveMsg> unReadMsgList =  receiveMsgService.unReadList(userId);
		List<ReceiveMsg> isReadMsgList = receiveMsgService.isReadList(userId);
		List<ReceiveMsg> isDele = receiveMsgService.isDele(userId);
		List<ReceiveMsg> msgList = receiveMsgService.list(userId);
		Integer unReadCount = unReadMsgList.size();
		Integer isReadCount = isReadMsgList.size();
		Integer msgCount = msgList.size();
		Integer isDeleCount = isDele.size();
		model.addAttribute("unReadCount", unReadCount);
		model.addAttribute("isReadCount", isReadCount);
		model.addAttribute("msgCount", msgCount);
		model.addAttribute("isDeleCount",isDeleCount);
		model.addAttribute("isReadMsgList", isReadMsgList);
		return "admin.teacher.message.isReadDetail";
	}
}
