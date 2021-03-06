package com.knet51.ccweb.controllers.admin.teacher.sendReceMsg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.beans.CommentBeans;
import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.admin.user.MyTrendsForm;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Comment;
import com.knet51.ccweb.jpa.entities.ReceiveMsg;
import com.knet51.ccweb.jpa.entities.SendMsg;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.timeline.Trends;
import com.knet51.ccweb.jpa.services.CommentService;
import com.knet51.ccweb.jpa.services.TrendsService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.msg.ReceiveMsgService;
import com.knet51.ccweb.jpa.services.msg.SendMsgService;
import com.knet51.ccweb.util.MyUtil;
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
	@Autowired
	private CommentService commentService;
	@Autowired
	private TrendsService trendsService;
	
	@RequestMapping(value="/admin/message/list")
	public String receiveMsgList(Model model,HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		logger.info("####  Into ReceiveMsgList page  ####");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			try {
				Long user_id = userInfo.getId();
				// count the total number of the unReadMsgList size
				List<ReceiveMsg> unReadMsgList =  receiveMsgService.unReadList(user_id);
				Integer unReadCount = unReadMsgList.size();
				
				//List<ReceiveMsg> isReadMsgList = receiveMsgService.isReadList(user_id);
				//List<ReceiveMsg> isDele = receiveMsgService.isDele(user_id);
				List<ReceiveMsg> msgList = receiveMsgService.list(user_id);
				
				//Integer isReadCount = isReadMsgList.size();
				Integer msgCount = msgList.size();
				//Integer isDeleCount = isDele.size();
				
				
				List<ReceiveMsg> unReadCommentList = receiveMsgService.unReadMsgList(user_id, GlobalDefs.MSG_TYPES_COMMENT, 2);
				Comment newComment = null;
				if(unReadCommentList.size()>0){
					newComment = commentService.findOneByCommentId(unReadCommentList.get(0).getCommentid());
				}
				
				List<ReceiveMsg> unReadFocusList = receiveMsgService.unReadMsgList(user_id, GlobalDefs.MSG_TYPES_FOCUS, 2);
				List<User> unReadFollower = new ArrayList<User>();
				for (int i = 0; i < unReadFocusList.size(); i++) {
					User user = userService.findOne(unReadFocusList.get(i).getCommenter());
					unReadFollower.add(user);
				}
				
				List<ReceiveMsg> unReadMessageListGroup = receiveMsgService.unReadMsgSenderListGroup(user_id, GlobalDefs.MSG_TYPES_MESSAGE);
				int pageNum = MyUtil.getPageNumber(pageNumber, unReadMessageListGroup.size() ,pageSize);
				//Page<ReceiveMsg> page = receiveMsgService.findReceiveMsgByUserAndTypes(pageNum, pageSize, userInfo.getUser(), 1, GlobalDefs.MSG_TYPES_MESSAGE);
				Page<ReceiveMsg> page = receiveMsgService.findReceiveMsgByUserAndReadedAndTypesGroup(pageNum, pageSize, GlobalDefs.MSG_TYPES_MESSAGE, 3, user_id);
				//List<ReceiveMsg> unReadListSenderList = receiveMsgService.unReadMsgSenderListGroup(user_id, GlobalDefs.MSG_TYPES_MESSAGE);
				
				//model.addAttribute("unReadListSender",unReadListSenderList);
				model.addAttribute("unReadFollower", unReadFollower );
				model.addAttribute("unReadFollowerCount", unReadFollower.size() );
				model.addAttribute("newComment", newComment );
				model.addAttribute("unReadCount", unReadCount);
				//model.addAttribute("isReadCount", isReadCount);
				model.addAttribute("msgCount", msgCount);
				//model.addAttribute("isDeleCount",isDeleCount);
				//model.addAttribute("unReadMsgList", unReadMsgList);
				model.addAttribute("page", page);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "admin."+userInfo.getRole()+".message.detail";
	}
	/*
	@RequestMapping(value="/admin/message/list")
	   public String receiveMsg(Model model,HttpSession session,@RequestParam(value="pageNumber",defaultValue="0")
       int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
           logger.info("####  Into ReceiveMsgList page  ####");
           UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
           Long user_id = userInfo.getId();
           try {
               List<ReceiveMsg> unReadMsgList =  receiveMsgService.unReadList(user_id);
               List<ReceiveMsg> isReadMsgList = receiveMsgService.isReadList(user_id);
               List<ReceiveMsg> isDele = receiveMsgService.isDele(user_id);
               List<ReceiveMsg> msgList = receiveMsgService.list(user_id);
               Integer unReadCount = unReadMsgList.size();
               Integer isReadCount = isReadMsgList.size();
               Integer msgCount = msgList.size();
               Integer isDeleCount = isDele.size();
               int pageNum = MyUtil.getPageNumber(pageNumber, unReadCount ,pageSize);
               Page<ReceiveMsg> page = receiveMsgService.findAllByUserAndReadedAndTypes(pageNum, pageSize, userInfo.getUser(),1,GlobalDefs.MSG_TYPES_MESSAGE);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
           return "";
       }
	*/
	
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
		return "admin."+userInfo.getRole()+".message.send";
	}
	
	@Transactional
	@RequestMapping(value="/admin/message/isRead")
	public String isReadMsg(Model model,HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Long user_id = userInfo.getId();
		List<ReceiveMsg> unReadMsgList =  receiveMsgService.unReadList(user_id);
		List<ReceiveMsg> isReadMsgList = receiveMsgService.isReadList(user_id);
		List<ReceiveMsg> isDele = receiveMsgService.isDele(user_id);
		List<ReceiveMsg> msgList = receiveMsgService.list(user_id);
		Integer unReadCount = unReadMsgList.size();
		Integer isReadCount = isReadMsgList.size();
		Integer msgCount = msgList.size();
		Integer isDeleCount = isDele.size();
		int pageNum = MyUtil.getPageNumber(pageNumber, isReadCount ,pageSize);
		Page<ReceiveMsg> page = receiveMsgService.findAllByUserAndReadedAndTypes(pageNum, pageSize,userInfo.getUser(), 2, GlobalDefs.MSG_TYPES_MESSAGE);
		
		model.addAttribute("unReadCount", unReadCount);
		model.addAttribute("isReadCount", isReadCount);
		model.addAttribute("msgCount", msgCount);
		model.addAttribute("isDeleCount",isDeleCount);
		//model.addAttribute("isReadMsgList", isReadMsgList);
		model.addAttribute("page", page);
		return "admin."+userInfo.getRole()+".message.isReadDetail";
	}
	
	@Transactional
	@RequestMapping(value="/admin/message/isDele")
	public String isDeleMsg(Model model,HttpSession session,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Long user_id = userInfo.getId();
		try {
			List<ReceiveMsg> unReadMsgList =  receiveMsgService.unReadList(user_id);
			List<ReceiveMsg> isReadMsgList = receiveMsgService.isReadList(user_id);
			List<ReceiveMsg> isDele = receiveMsgService.isDele(user_id);
			List<ReceiveMsg> msgList = receiveMsgService.list(user_id);
			Integer unReadCount = unReadMsgList.size();
			Integer isReadCount = isReadMsgList.size();
			Integer msgCount = msgList.size();
			Integer isDeleCount = isDele.size();
			int pageNum = MyUtil.getPageNumber(pageNumber, isDeleCount ,pageSize);
			Page<ReceiveMsg> page = receiveMsgService.findAllByUserAndReadedAndTypes(pageNum, pageSize, userInfo.getUser(), 3, GlobalDefs.MSG_TYPES_MESSAGE);

			model.addAttribute("unReadCount", unReadCount);
			model.addAttribute("isReadCount", isReadCount);
			model.addAttribute("msgCount", msgCount);
			model.addAttribute("isDeleCount",isDeleCount);
			//model.addAttribute("isReadMsgList", isReadMsgList);
			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin."+userInfo.getRole()+".message.isDeleDetaill";
	}
	/**
	 * show All msg by sender and receiver
	 * @param sender_id
	 * @param session
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/admin/message/detail/{sender_id}")
	public String showMsgBySender(@PathVariable Long sender_id,HttpSession session,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<ReceiveMsg> unReadMsgList =  receiveMsgService.findCommenterMsgList(GlobalDefs.MSG_TYPES_MESSAGE, 2, userInfo.getId(), sender_id);
		
		for (int i = 0; i < unReadMsgList.size(); i++) {
			receiveMsgService.isRead(unReadMsgList.get(i).getId());
		}
		
		List<ReceiveMsg> unReadAllMsgList =  receiveMsgService.unReadList(userInfo.getId());
		model.addAttribute("unReadCount", unReadAllMsgList.size());

		List<ReceiveMsg> userAndCommenterMsgList = receiveMsgService.findMsgListByUserAndCommenter(userInfo.getId(), GlobalDefs.MSG_TYPES_MESSAGE, sender_id);
		int pageNum = MyUtil.getPageNumber(pageNumber, userAndCommenterMsgList.size() ,pageSize);
		Page<ReceiveMsg> page = receiveMsgService.findMsgByUserAndCommenter(pageNum, pageSize, userInfo.getId(), GlobalDefs.MSG_TYPES_MESSAGE, sender_id);
		model.addAttribute("page", page);
		model.addAttribute("sender_id", sender_id);
		return "admin."+userInfo.getRole()+".message.detail.list";
	}
	
	@RequestMapping(value="/admin/message/detail/reply",method = RequestMethod.POST)
	public @ResponseBody ReceiveMsg ajaxCreateComment(@RequestParam("senderId") Long sender_id, HttpSession session,HttpServletResponse response,
			@Valid MyTrendsForm msgForm,BindingResult validResult){
		logger.info("#### Into SendMsgInfoPageControllerPage ####");
		if(validResult.hasErrors()){
			return null;
		}else{
			SendMsg sendMsg = new SendMsg();
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			User user = userService.findOne(userInfo.getId());
			String content = msgForm.getContents();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			//sendMsg.setTitle(title);
			sendMsg.setIsDelete(1);
			sendMsg.setContent(content);
			sendMsg.setDate(date);
			sendMsg.setUser(user);
			ReceiveMsg receiveMsg = sendMsgService.add(sendMsg, sender_id, userInfo.getId());
			return receiveMsg;
		}	
	}
	
	/**
	 * show unRead comment list in msg 
	 * @param session
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/admin/message/comment/detail")
	public String showCommentdetail(HttpSession session,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		try {
			
			List<ReceiveMsg> unReadCommentList = receiveMsgService.unReadMsgList(userInfo.getId(), GlobalDefs.MSG_TYPES_COMMENT, 2);
			int pageNum = MyUtil.getPageNumber(pageNumber, unReadCommentList.size() ,pageSize);
			Page<ReceiveMsg> page = receiveMsgService.findReceiveMsgByUserAndTypes(pageNum, pageSize, userInfo.getId(),3,GlobalDefs.MSG_TYPES_COMMENT);
			logger.info("---- into message comment detail list----");
			List<CommentBeans> commentBeansList = new ArrayList<CommentBeans>();
			for (int i = 0; i < page.getContent().size(); i++) {
				CommentBeans commentBeans = new CommentBeans();
				Comment comment = commentService.findOneByCommentId(page.getContent().get(i).getCommentid());
				Trends  trends = trendsService.findOneById(comment.getTrendId());
				System.out.println("----trendid="+trends.getId());
				commentBeans.setComment(comment);
				commentBeans.setTrends(trends);
				commentBeansList.add(commentBeans);
			}
			
			// change the unReadComment to readed 
			for (int i = 0; i < unReadCommentList.size(); i++) {
				receiveMsgService.isRead(unReadCommentList.get(i).getId());
			}
			// count the total number of the unReadMsgList size
			List<ReceiveMsg> unReadMsgList =  receiveMsgService.unReadList(userInfo.getId());
			model.addAttribute("unReadCount", unReadMsgList.size());
			
			model.addAttribute("page", page);
			model.addAttribute("commentBeansList", commentBeansList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin."+userInfo.getRole()+".message.comment.detail.list";
	}
	
	/**
	 * show  newfans list in msg 
	 * @param session
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/admin/message/focus/detail")
	public String showNewFocusdetail(HttpSession session,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<ReceiveMsg> unReadFocusList = receiveMsgService.unReadMsgList(userInfo.getId(), GlobalDefs.MSG_TYPES_FOCUS, 2);
		List<User> unReadFollower = new ArrayList<User>();
		for (int i = 0; i < unReadFocusList.size(); i++) {
			User user = userService.findOne(unReadFocusList.get(i).getCommenter());
			unReadFollower.add(user);
			receiveMsgService.isRead(unReadFocusList.get(i).getId());
		}
		model.addAttribute("unReadFollower", unReadFollower);
		model.addAttribute("unReadFollowerCount", unReadFollower.size());
		
		List<ReceiveMsg> unReadMsgList =  receiveMsgService.unReadList(userInfo.getId());
		model.addAttribute("unReadCount", unReadMsgList.size());
		return "admin."+userInfo.getRole()+".message.focus.detail.list";
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
