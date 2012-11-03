package com.knet51.ccweb.controllers.teacher.SendReceMsg;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.SendMsg;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.SendMsgService;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class SendMsgDetailInfoController {
	private static final Logger logger = LoggerFactory.getLogger(SendMsgDetailInfoController.class);
	
	@Autowired
	private SendMsgService sendMsgService;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	@RequestMapping(value="/admin/message/sendMsgInfo",method = RequestMethod.POST)
	public String messageList(@RequestParam("uid") Long userId,@Valid SendMsgInfoForm sendMsgInfoForm,
			BindingResult validResult, HttpSession session,Model model){
		
		logger.info("#### Into SendMsgInfoPageControllerPage ####");
		if(validResult.hasErrors()){
			return "";
		}else{
			//System.out.println(userId);
			SendMsg sendMsg = new SendMsg();
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			User user = userInfo.getUser();
			String title = sendMsgInfoForm.getTitle();
			String content = sendMsgInfoForm.getContent();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			sendMsg.setTitle(title);
			sendMsg.setIsDelete(1);
			sendMsg.setContent(content);
			sendMsg.setDate(date);
			sendMsg.setUser(user);
			sendMsgService.add(sendMsg, userId);
			return "redirect:/teacher/" + userId;
		}	
	}
	
	@Transactional	
	@RequestMapping(value="/admin/teacher/message/sendMsg",method = RequestMethod.POST)
	public String sendMsg(@RequestParam("senderId") Long senderId, @RequestParam("sendMsgId") Long sendMsgId , @RequestParam("urmId") Long urmId ,
			@Valid SendMsgInfoForm sendMsgInfoForm,
			BindingResult validResult, HttpSession session,Model model){
		
		logger.info("#### Into SendMsgInfoPageControllerPage ####");
		if(validResult.hasErrors()){
			return "";
		}else{
			//System.out.println(userId);
			SendMsg sendMsg = new SendMsg();
			//User sender = userService.findOne(senderId);
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			User sender = userInfo.getUser();
			String title = sendMsgInfoForm.getTitle();
			String content = sendMsgInfoForm.getContent();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			sendMsg.setTitle(title);
			sendMsg.setIsDelete(1);
			sendMsg.setContent(content);
			sendMsg.setDate(date);
			sendMsg.setUser(sender);
			sendMsgService.add(sendMsg, senderId);
			return "redirect:/admin/teacher/message/detailOne?mid=" + sendMsgId+"&urmid="+urmId;
		}	
	}
}