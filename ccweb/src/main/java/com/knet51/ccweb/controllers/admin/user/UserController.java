package com.knet51.ccweb.controllers.admin.user;

import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import com.knet51.ccweb.beans.UserInfo;
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
			LoggerFactory.getLogger(UserController.class);
	
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
	@RequestMapping(value="/admin/home")
	public String showAllTrends(HttpSession session, Model model){
		UserInfo userInfo =  (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		List<Trends> myTrends = trendsService.showAllTrendsByUserId(userInfo.getId());
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
	
	/**
	 * rturn trend comment ajax
	 * @param trend_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/showTrendsComment",method=RequestMethod.POST)
	public @ResponseBody List<Comment> showTrendsComment(@RequestParam("trendId") Long trend_id  ) throws Exception{
		logger.info("======= into show Trend comment controller =====");
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
			return "redirect:/admin/home/"+userInfo.getId();
		}else{
		Comment comment = new Comment();
		comment.setContext(trendsForm.getContents());
		comment.setName(userInfo.getName());
		comment.setTrendId(trend_id);
		comment.setUserId(userInfo.getId());
		comment.setPhoto_url(userInfo.getAvatar());
		commentService.createComment(comment);
		return "redirect:/admin/home/"+userInfo.getId(); 
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
			return "redirect:/admin/home/"+userInfo.getId();
		}else{
		Trends trends = trendsService.findOneById(trend_id);
		Comment comment = new Comment();
		comment.setContext(trendsForm.getContents());
		comment.setName(userInfo.getName());
		comment.setTrendId(trend_id);
		comment.setUserId(userInfo.getId());
		comment.setPhoto_url(userInfo.getAvatar());
		commentService.createComment(comment);
		return "redirect:/id/"+trends.getUserId(); 
		}
	}
}
