package com.knet51.ccweb.controllers.teacher.SendReceMsg;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.SendMsg;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.SendMsgService;

@Controller
public class SendMsgDetailInfoController {
	private static final Logger logger = LoggerFactory.getLogger(SendMsgDetailInfoController.class);
	
	@Autowired
	private SendMsgService sendMsgService;
	
	@RequestMapping(value="/admin/message/sendMsgInfo",method = RequestMethod.POST)
	public String messageList(@RequestParam("uid") Long userId,@Valid SendMsgInfoForm sendMsgInfoForm,
			BindingResult validResult, HttpSession session,Model model){
		
		logger.info("#### Into SendMsgInfoPageControllerPage ####");
		if(validResult.hasErrors()){
			return "";
		}else{
			System.out.println(userId);
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
}
