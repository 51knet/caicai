package com.knet51.ccweb.controllers.common.friendsRelated;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.FriendsRelated;
import com.knet51.ccweb.jpa.entities.ReceiveMsg;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.FriendsRelateService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.msg.ReceiveMsgService;
import com.knet51.ccweb.util.mailSender.MailSender;

@Controller
public class FriendsRelatedDetailController {	
	private Logger logger = 
			LoggerFactory.getLogger(FriendsRelatedDetailController.class);  
	
	@Autowired
	private UserService userService;
	@Autowired
	private FriendsRelateService friendsRelateService; 
	@Autowired 
	private ReceiveMsgService receiveMsgService;
	
	@RequestMapping(value="/addrelation")
	public String askFriendsRelated(@RequestParam("uid") Long host_id,HttpSession session){
		logger.info("#### Into FriendsRelated addDetailPage ####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Long user_id = userInfo.getId();
		//User user = userService.findOne(user_id);
		User host = userService.findOne(host_id);
		FriendsRelated friendsReiated = new FriendsRelated();
		friendsReiated.setHost_id(host_id);
		friendsReiated.setFollow_id(user_id);
		friendsReiated.setGroups(host.getRole());
		friendsRelateService.save(friendsReiated);
		
		ReceiveMsg receiveMsg = new ReceiveMsg();
		receiveMsg.setCommenter(userInfo.getId());
		receiveMsg.setTypes(GlobalDefs.MSG_TYPES_FOCUS);
		receiveMsg.setReaded(1);
		receiveMsg.setUser(host);
		receiveMsgService.add(receiveMsg);
		
		MailSender.getInstance().SendFocusMail(host.getEmail(), 
				"http://www.51knet.com", host_id);
		
		return "redirect:/id/" + host_id;
	}
	
	@RequestMapping(value="/delerelation")
	public String deleFriendsRelated(@RequestParam("uid") Long host_id,HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		FriendsRelated related = friendsRelateService.findOneByHostIdAndFollowId(host_id, userInfo.getId());
		friendsRelateService.deleteById(related.getId());
		return "redirect:/id/" + host_id;
	}
	
	@RequestMapping(value="/addRelation")
	public @ResponseBody String addRelation(@RequestParam("teacherid")Long teachre_id,@RequestParam("flag") String flag){
		 
		return flag;
	}
	
}
