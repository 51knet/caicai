package com.knet51.ccweb.controllers.admin.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.admin.teacher.announcement.TeacherAnnoDetailInfoController;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Comment;
import com.knet51.ccweb.jpa.entities.timeline.Trends;
import com.knet51.ccweb.jpa.services.CommentService;
import com.knet51.ccweb.jpa.services.FriendsRelateService;
import com.knet51.ccweb.jpa.services.TrendsService;
import com.knet51.ccweb.jpa.services.UserService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	
	private static Logger logger = 
			LoggerFactory.getLogger(UserController .class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private FriendsRelateService relateService;
	@Autowired
	private TrendsService trendsService;
	@Autowired
	private CommentService commentService;
	
	/**
	 * big time line
	 * @param user_id
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/home/{user_id}")
	public String showAllTrends(@PathVariable Long user_id,HttpSession session, Model model){
		UserInfo userInfo =  (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(!userInfo.getId().equals(user_id)){
			return "redirect:/admin";
		}
		List<Trends> myTrends = trendsService.showAllTrendsByUserId(user_id);
		model.addAttribute("myTrend", myTrends);
		return "admin.user.home";
	}
	/**
	 * create a new trends by user
	 * @param session
	 * @param trendsForm
	 * @param validResult
	 * @return
	 */
	@RequestMapping(value="/admin/mytrends", method = RequestMethod.POST)
	public String createMyTrends(HttpSession session,@Valid MyTrendsForm trendsForm,BindingResult validResult){
		UserInfo userInfo =  (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(validResult.hasErrors()){
			return "redirect:/admin/home/"+userInfo.getId();
		}else{
			Trends myTrends = new Trends();
			myTrends.setUserId(userInfo.getId());
			myTrends.setName(userInfo.getName());
			myTrends.setGender(userInfo.getGender());
			myTrends.setContext(trendsForm.getContents());
			myTrends.setEmail(userInfo.getEmail());
			myTrends.setPhoto_url(userInfo.getAvatar());
			myTrends.setPublishDate(new Date());
			trendsService.createTrends(myTrends);
			return "redirect:/admin/home/"+userInfo.getId();
		}
	}
	
	@RequestMapping(value="/showTrendsComment",method=RequestMethod.POST)
	public void showTrendsComment(HttpServletResponse response, @RequestParam("trendId") Long trend_id) throws Exception{
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		List<Comment> cList = commentService.findTopSixByTrendId(trend_id);
		logger.info("======= into show Trend comment controller =====");
		out.print(g.toJson(cList));
		logger.info("---"+g.toJson(cList));
		out.flush();
		out.close();
		
	}
	
}
