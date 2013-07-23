package com.knet51.ccweb.controllers.admin.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.beans.TrendsBeans;
import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Comment;
import com.knet51.ccweb.jpa.entities.User;
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
	
	@Autowired
	private UserService userService;
	@Autowired
	private TrendsService trendsService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private FriendsRelateService friendsRelateService;
	
	/**
	 * big time line
	 * @param user_id
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/trend")
	public String showAllTrends(HttpSession session, Model model){
		UserInfo userInfo =  (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		String role = userInfo.getRole();
		if(role != null && role.equals("user")){
			List<Trends> myTrends = trendsService.showAllTrendsByUserId(userInfo.getId());
			//model.addAttribute("myTrend", myTrends);
			
			List<TrendsBeans> trendsBeansList = new ArrayList<TrendsBeans>();
			List<Comment> comment = new ArrayList<Comment>();
			for (Trends trends : myTrends) {
				TrendsBeans trendsBeans = new TrendsBeans();
				comment = commentService.findAllByTrendId(trends.getId());
				trendsBeans.setCommentList(comment);
				trendsBeans.setTrend(trends);
				trendsBeans.setCommentCount((long)comment.size());
				trendsBeansList.add(trendsBeans);
			}
			
			model.addAttribute("myTrend", trendsBeansList);
			return "admin.user.trend";
		}
		else if(role != null && role.equals("teacher")){
			List<Trends> myTrends = trendsService.showAllTeacherTrendsByUserId(userInfo.getId());
			List<TrendsBeans> trendsBeansList = new ArrayList<TrendsBeans>();
			List<Comment> comment = new ArrayList<Comment>();
			for (Trends trends : myTrends) {
				TrendsBeans trendsBeans = new TrendsBeans();
				comment = commentService.findAllByTrendId(trends.getId());
				trendsBeans.setCommentList(comment);
				trendsBeans.setTrend(trends);
				trendsBeans.setCommentCount((long)comment.size());
				trendsBeansList.add(trendsBeans);
			}
			
			model.addAttribute("myTrend", trendsBeansList);
			return "admin.teacher.trend";
		}
		else{
			return "redirect:/admin";
		}
	}
	/**
	 * create a new trends by user
	 * @param session
	 * @param trendsForm
	 * @param validResult
	 * @return
	 */
	@RequestMapping(value="/admin/trend/publish", method = RequestMethod.POST)
	public String createMyTrends(HttpSession session,@Valid MyTrendsForm trendsForm,BindingResult validResult){
		UserInfo userInfo =  (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(validResult.hasErrors()){
			return "redirect:/admin/trend";
		}else{
			Trends newTrends = new Trends();
			newTrends.setUser(userInfo.getUser());
			
			newTrends.setContext(trendsForm.getContents());
			newTrends.setPublishDate(new Date());
			
			trendsService.createTrends(newTrends);
			return "redirect:/admin/trend";
		}
	}
	
	/**
	 * rturn trend comment ajax
	 * @param trend_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/showTrendsComment",method=RequestMethod.POST)
	public @ResponseBody List<Comment> showTrendsComment(@RequestParam("trendId") Long trend_id  ) throws Exception{
		List<Comment> cList = commentService.findAllByTrendId(trend_id);
		return cList;
		
	}
	/**
	 * create comment in admin page,just for testing
	 * @param trend_id
	 * @param session
	 * @param trendsForm
	 * @param validResult
	 * @return
	 */
	@RequestMapping(value="/comment" , method=RequestMethod.POST)
	public String createComment(@RequestParam("trendId") Long trend_id, HttpSession session,@Valid MyTrendsForm trendsForm,BindingResult validResult){
		UserInfo userInfo =  (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(validResult.hasErrors()){
			return "redirect:/admin/trend";
		}else{
		Comment comment = new Comment();
		
		comment.setUser(userInfo.getUser());
		
		comment.setContext(trendsForm.getContents());
		comment.setTrendId(trend_id);
		
		comment.setPublishDate(new Date());
		commentService.createComment(comment);
		return "redirect:/admin/trend"; 
		}
	}
	
	/**
	 * create comment in front page
	 * @param trend_id
	 * @param session
	 * @param trendsForm
	 * @param validResult
	 * @return
	 */
	@RequestMapping(value="/front/comment" , method=RequestMethod.POST)
	public String createFrontComment(@RequestParam("trendId") Long trend_id, HttpSession session,@Valid MyTrendsForm trendsForm,BindingResult validResult){
		UserInfo userInfo =  (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(validResult.hasErrors()){
			return "redirect:/admin/trend";
		}else{
		Trends trends = trendsService.findOneById(trend_id);
		Comment comment = new Comment();
		
		comment.setUser(userInfo.getUser());
		
		comment.setTrendId(trend_id);
		comment.setContext(trendsForm.getContents());
		comment.setPublishDate(new Date());
		commentService.createComment(comment);
		return "redirect:/id/"+trends.getUser().getId(); 
		}
	}
	
	@RequestMapping(value = "/trend/{varity}/{uid}")
	public String trendDispatcher(@PathVariable String varity,
			@PathVariable Long uid, HttpSession session,Model model) {
		User user = userService.findOne(uid);
		if (user == null) {
			return "404";
		} else {
			UserInfo sessionUserInfo = (UserInfo) session
					.getAttribute(GlobalDefs.SESSION_USER_INFO);
			boolean isFollower = false;
			if (sessionUserInfo != null) { 
				User sessionUser = sessionUserInfo.getUser();
				isFollower = friendsRelateService.isTheFollower(uid,sessionUser.getId());
				session.setAttribute("isFollower", isFollower);
			}
			return "redirect:/" + user.getRole() + "/" + uid + "/" + varity
					+ "/list";
		}
	}
}
