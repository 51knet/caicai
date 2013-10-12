package com.knet51.ccweb.controllers.admin.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knet51.ccweb.beans.TrendsBeans;
import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Comment;
import com.knet51.ccweb.jpa.entities.EduBackground;
import com.knet51.ccweb.jpa.entities.Knowledge;
import com.knet51.ccweb.jpa.entities.ReceiveMsg;
import com.knet51.ccweb.jpa.entities.Student;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.WorkExp;
import com.knet51.ccweb.jpa.entities.courses.Course;
import com.knet51.ccweb.jpa.entities.timeline.Trends;
import com.knet51.ccweb.jpa.services.CommentService;
import com.knet51.ccweb.jpa.services.CourseService;
import com.knet51.ccweb.jpa.services.EduBackgroundService;
import com.knet51.ccweb.jpa.services.FriendsRelateService;
import com.knet51.ccweb.jpa.services.KnowledgeService;
import com.knet51.ccweb.jpa.services.ReceiveMsgService;
import com.knet51.ccweb.jpa.services.StudentService;
import com.knet51.ccweb.jpa.services.TrendsService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.WorkExpService;
import com.knet51.ccweb.jpa.services.promotion.UserRecommendService;


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
	@Autowired
	private UserRecommendService recommendService;
	@Autowired
	private KnowledgeService knowledgeService;
	@Autowired
	private CourseService courseService;
	@Autowired 
	private ReceiveMsgService receiveMsgService; 
	@Autowired
	private EduBackgroundService eduBackgroundService;
	@Autowired
	private WorkExpService workExpService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private FriendsRelateService relateService;
	
	/**
	 * big time line
	 * @param user_id
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/trend/{trendRole}/{variety}")
	public String showAllTrends(HttpSession session, Model model,@PathVariable String trendRole, @PathVariable String variety){
		UserInfo userInfo =  (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		String role = userInfo.getRole();
		
		// show the recommend info at page of right
		List<User> recommendTeacher = recommendService.getRecommendTeacher(userInfo.getId(), 3);
		List<User> recommendUser = recommendService.getRecommendUser(userInfo.getId(), 3);
		List<Course> recommendCourse = recommendService.getRecommendCourses(userInfo.getId(), 3);
		
		model.addAttribute("recommendTeacher", recommendTeacher);
		model.addAttribute("recommendUser", recommendUser);
		model.addAttribute("recommendCourse", recommendCourse);
		
		// show the trends and its comments
		List<TrendsBeans> trendsBeansList = new ArrayList<TrendsBeans>();
		List<Trends> myTrends = null;
		if(variety.equals("all")){
			if(trendRole.equals("teacher") || trendRole .equals("user")){
				myTrends = trendsService.showAllTrendsByUserIdAndRole(userInfo.getId(), trendRole);
			}else{
				myTrends = trendsService.showAllTrendsByUserId(userInfo.getId());
			}
			
		}else{
			if(trendRole.equals("teacher") || trendRole .equals("user")){
				myTrends = trendsService.showAllTrendsByUserIdAndRoleAndVariety(userInfo.getId(), trendRole, variety);
			}else{
				myTrends = trendsService.showAllTrendsByUserIdAndVariety(userInfo.getId(), variety);
			}
		}
		
		if(myTrends.size()>0){
			List<Comment> comment = new ArrayList<Comment>();
			for (Trends trends : myTrends) {
				TrendsBeans trendsBeans = new TrendsBeans();
				comment = commentService.findAllByTrendId(trends.getId());
				trendsBeans.setCommentList(comment);
				trendsBeans.setTrend(trends);
				trendsBeans.setCommentCount((long)comment.size());
				trendsBeansList.add(trendsBeans);
			}
		}
		
		// judge unReaded msg  count 
		List<ReceiveMsg> unReadMsgList =  receiveMsgService.unReadList(userInfo.getId());
		
		model.addAttribute("unReadCount", unReadMsgList.size());
		model.addAttribute("trendRole", trendRole);
		model.addAttribute("trendVariety", variety);
		model.addAttribute("trend", trendsBeansList);
		model.addAttribute("trendCount", trendsBeansList.size());
		return "admin."+role+".trend";
	}
	
	/**
	 * create a new trends by user
	 * @param session
	 * @param trendsForm
	 * @param validResult
	 * @return
	 */
	@RequestMapping(value="/admin/trend/publish", method = RequestMethod.POST)
	public String createMyTrends(HttpSession session,@Valid MyTrendsForm trendsForm,BindingResult validResult,@RequestParam("trendRole") String trendRole,
			@RequestParam("trendVariety") String trendVariety){
		UserInfo userInfo =  (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(validResult.hasErrors()){
			return "redirect:/admin/trend/"+trendRole+"/"+trendVariety;
		}else{
			Trends newTrends = new Trends();
			newTrends.setUser(userInfo.getUser());
			
			newTrends.setContext(trendsForm.getContents());
			newTrends.setPublishDate(new Date());
			newTrends.setVariety("shuoshuo");
			trendsService.createTrends(newTrends);
			return "redirect:/admin/trend/"+trendRole+"/"+trendVariety;
		}
	}
	
	/**
	 * return trend comment ajax
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
	public String createComment(@RequestParam("trendId") Long trend_id, HttpSession session,@Valid MyTrendsForm trendsForm,BindingResult validResult,
			RedirectAttributes redirectAttributes,@RequestParam("trendRole") String trendRole){
		UserInfo userInfo =  (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(validResult.hasErrors()){
			return "redirect:/admin/trend/view/"+trend_id;
		}else{
		Comment comment = new Comment();
		
		comment.setUser(userInfo.getUser());
		
		comment.setContext(trendsForm.getContents());
		comment.setTrendId(trend_id);
		
		comment.setPublishDate(new Date());
		Comment newComment = commentService.createComment(comment);
		if(newComment != null){
			redirectAttributes.addFlashAttribute("show",trend_id);
		}
		return "redirect:/admin/trend/view/"+trend_id;
		}
	}
	/**
	 * ajax Comment
	 * @param trend_id
	 * @param session
	 * @param response
	 * @param trendsForm
	 * @param validResult
	 * @return
	 */
	@RequestMapping(value="/ajaxcomment" , method = RequestMethod.POST)
	public @ResponseBody Comment ajaxCreateComment(@RequestParam("trendId") Long trend_id, HttpSession session,HttpServletResponse response,
			@Valid MyTrendsForm trendsForm,BindingResult validResult){
		UserInfo userInfo =  (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Trends t = new Trends(); 
		t = trendsService.findOneById(trend_id);
		Comment comment = new Comment();
		
		if(validResult.hasErrors()){
			return null;
		}else{
		comment.setUser(userInfo.getUser());
		
		comment.setContext(trendsForm.getContents());
		comment.setTrendId(trend_id);
		
		comment.setPublishDate(new Date());
		Comment newComment = commentService.createComment(comment);
		
		ReceiveMsg receiveMsg = new ReceiveMsg();
		receiveMsg.setCommenter(userInfo.getId());
		receiveMsg.setCommentid(newComment.getId());
		receiveMsg.setTypes(GlobalDefs.MSG_TYPES_COMMENT);
		receiveMsg.setReaded(1);
		receiveMsg.setUser(t.getUser());
		receiveMsgService.add(receiveMsg);
		
		return newComment; 
		}
	}
	/**
	 * ajax Reply
	 * @param host_id
	 * @param trend_id
	 * @param session
	 * @param response
	 * @param trendsForm
	 * @param validResult
	 * @return
	 */
	@RequestMapping(value="/ajaxreply" , method = RequestMethod.POST)
	public @ResponseBody Comment ajaxCreateReply(@RequestParam("hostId") Long host_id,@RequestParam("trendId") Long trend_id, HttpSession session,HttpServletResponse response,
			@Valid MyTrendsForm trendsForm,BindingResult validResult){
		UserInfo userInfo =  (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Trends t = new Trends(); 
		t = trendsService.findOneById(trend_id);
		if(validResult.hasErrors()){
			return null;
		}else{
			User host = userService.findOne(host_id);
			Comment comment = new Comment();
			comment.setUser(userInfo.getUser());
			comment.setTrendId(trend_id);
			comment.setHost(host);
			comment.setContext(trendsForm.getContents());
			comment.setPublishDate(new Date());
			Comment newComment =commentService.createComment(comment);
			
			ReceiveMsg receiveMsg = new ReceiveMsg();
			receiveMsg.setCommenter(userInfo.getId());
			receiveMsg.setCommentid(newComment.getId());
			receiveMsg.setTypes(GlobalDefs.MSG_TYPES_COMMENT);
			receiveMsg.setReaded(1);
			receiveMsg.setUser(host);
			receiveMsgService.add(receiveMsg);
			return newComment; 
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
			return "redirect:/admin/all/all";
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
	
	/**
	 * create reply
	 * @param host_id
	 * @param trend_id
	 * @param trendRole
	 * @param session
	 * @param trendsForm
	 * @param validResult
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/reply" ,method = RequestMethod.POST)
	public String createReply(@RequestParam("hostId") Long host_id,@RequestParam("trendId") Long trend_id,@RequestParam("trendRole") String trendRole,
			HttpSession session,@Valid MyTrendsForm trendsForm,BindingResult validResult,RedirectAttributes redirectAttributes){
		UserInfo userInfo =  (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(validResult.hasErrors()){
			return "redirect:/admin/trend/view/"+trend_id;
		}else{
		User host = userService.findOne(host_id);
		Comment comment = new Comment();
		comment.setUser(userInfo.getUser());
		comment.setTrendId(trend_id);
		comment.setHost(host);
		comment.setContext(trendsForm.getContents());
		comment.setPublishDate(new Date());
		Comment newComment =commentService.createComment(comment);
		if(newComment != null){
			redirectAttributes.addFlashAttribute("show",trend_id);
		}
		return "redirect:/admin/trend/view/"+trend_id;
		}
	}
	
	/**
	 * show detail variety in trend jsp
	 * @param variety
	 * @param uid
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/trend/{variety}/{uid}")
	public String trendDispatcher(@PathVariable String variety,
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
				
				Integer fansCount = friendsRelateService.getAllFans(uid)
						.size();
				Integer hostCount = friendsRelateService.getAllHost(uid)
						.size();
				session.setAttribute("fansCount", fansCount);
				session.setAttribute("hostCount", hostCount);
			}
			return "redirect:/" + user.getRole() + "/" + uid + "/" + variety
					+ "/list";
		}
	}
	/**
	 * show detail trends' comments
	 * @param trend_id
	 * @param session
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/admin/trend/view/{trend_id}")
	public String showTrendDetail(@PathVariable Long trend_id,HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize){
		UserInfo userInfo =  (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Trends trends = trendsService.findOneById(trend_id);
		TrendsBeans trendsBeans = new TrendsBeans();
		Page<Comment> comment = commentService.findAllByTrendId(trend_id, pageNumber, pageSize);
		int commentCount = commentService.findAllByTrendId(trend_id).size();
		trendsBeans.setCommentList(comment.getContent());
		trendsBeans.setTrend(trends);
		trendsBeans.setCommentCount((long)commentCount);
		model.addAttribute("trendBeans", trendsBeans);
		model.addAttribute("page", comment);
		
		List<User> recommendTeacher = recommendService.getRecommendTeacher(userInfo.getId(), 3);
		List<User> recommendUser = recommendService.getRecommendUser(userInfo.getId(), 3);
		List<Course> recommendCourse = recommendService.getRecommendCourses(userInfo.getId(), 3);
		
		model.addAttribute("recommendTeacher", recommendTeacher);
		model.addAttribute("recommendUser", recommendUser);
		model.addAttribute("recommendCourse", recommendCourse);
		model.addAttribute("view", "view");
		String role = userInfo.getRole();
		
		if(role != null && role.equals("user")){
			return "admin.user.trend.detail";
		}else if(role != null && role.equals("teacher")){
			return "admin.teacher.trend.detail";
		}else{
			return "redirect:/admin";
		}
	}
	
	/**
	 * show my knowledge store
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/myknowledge/list")
	public String showKnowledgeByUser(HttpSession session,Model model,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="9") int pageSize){
		
		try {
			UserInfo userInfo =  (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			Page<Knowledge> myKnowledgePage = knowledgeService.findAllByUserid(pageNumber, pageSize, userInfo.getId());
			Course course = null;
			List<Course> courseList = new ArrayList<Course>();
			for(int i=0;i< myKnowledgePage.getContent().size();i++){
				course = courseService.findOneById(myKnowledgePage.getContent().get(i).getCourseid());
				if(course != null){
					courseList.add(course);
				}
			}
			
			// judge unReaded msg  count 
			List<ReceiveMsg> unReadMsgList =  receiveMsgService.unReadList(userInfo.getId());
			
			model.addAttribute("unReadCount", unReadMsgList.size());
			model.addAttribute("courseList", courseList);
			model.addAttribute("page",myKnowledgePage);
			model.addAttribute("courseCount", courseList.size());
			return "admin."+userInfo.getRole()+".knowledge.list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin";
	}
	
	@RequestMapping(value="/admin/message/comment/destory" ,method = RequestMethod.POST)
	public String destoryComment(HttpSession session,@RequestParam("trendRole") String trendRole,
			@RequestParam("trendVariety") String trendVariety,@RequestParam("commentId") Long comment_id){
		
		try {
			if(comment_id != null){
				commentService.deleteComment(comment_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/trend/"+trendRole+"/"+trendVariety;
	}
	
	/**
	 * show detail trend info in user front page
	 * @param user_id
	 * @param trend_id
	 * @param pageNumber
	 * @param pageSize
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/user/{user_id}/trend/view/{trend_id}")
	public String showDetailTrendInFrontPage(@PathVariable Long user_id,@PathVariable Long trend_id,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="10") int pageSize,HttpSession session,Model model){
		try {
			// common model -start
			User user = userService.findOne(user_id);
			if (user.getRole().equals("user")) {
				UserInfo sessionUserInfo = (UserInfo) session
						.getAttribute(GlobalDefs.SESSION_USER_INFO);
				// page
				List<User> recommendTeacher = null;
				List<User> recommendUser = null;
				List<Course> recommendCourse = null;
				if (sessionUserInfo != null) {
					recommendTeacher = recommendService.getRecommendTeacher(sessionUserInfo.getId(), 3);
					recommendUser = recommendService.getRecommendUser(sessionUserInfo.getId(), 3);
					recommendCourse = recommendService.getRecommendCourses(sessionUserInfo.getId(), 3);

				}else{
					recommendTeacher = recommendService.getRandomUsers("teacher", 3);
					recommendUser = recommendService.getRandomUsers("user", 3);
					recommendCourse = recommendService.getRandomCourses(3);
				}
				
				model.addAttribute("recommendTeacher", recommendTeacher);
				model.addAttribute("recommendUser", recommendUser);
				model.addAttribute("recommendCourse", recommendCourse);
						
				UserInfo userInfo = new UserInfo(user);

				model.addAttribute("userInfo", userInfo);
				model.addAttribute("user_id", user_id);

				model.addAttribute("role", userInfo.getRole());
				// common model -end
				
				
				Trends trends = trendsService.findOneById(trend_id);
				TrendsBeans trendsBeans = new TrendsBeans();
				Page<Comment> comment = commentService.findAllByTrendId(trend_id, pageNumber, pageSize);
				int commentCount = commentService.findAllByTrendId(trend_id).size();
				trendsBeans.setCommentList(comment.getContent());
				trendsBeans.setTrend(trends);
				trendsBeans.setCommentCount((long)commentCount);
				model.addAttribute("trendBeans", trendsBeans);
				model.addAttribute("page", comment);
				model.addAttribute("view", "view");
				return userInfo.getRole()+".trend.view";
			} else {
				return "redirect:/id/" + user_id;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "404";
		}
	}
	
	@RequestMapping(value="/user/{user_id}/resume")
	public String showUserResume(@PathVariable Long user_id,HttpSession session,Model model){
		User user = userService.findOne(user_id);
		if (user.getRole().equals("user")) {
			UserInfo sessionUserInfo = (UserInfo) session
					.getAttribute(GlobalDefs.SESSION_USER_INFO);
			// page
			List<User> recommendTeacher = null;
			List<User> recommendUser = null;
			List<Course> recommendCourse = null;
			if (sessionUserInfo != null) {
				recommendTeacher = recommendService.getRecommendTeacher(sessionUserInfo.getId(), 3);
				recommendUser = recommendService.getRecommendUser(sessionUserInfo.getId(), 3);
				recommendCourse = recommendService.getRecommendCourses(sessionUserInfo.getId(), 3);
	
			}else{
				recommendTeacher = recommendService.getRandomUsers("teacher", 3);
				recommendUser = recommendService.getRandomUsers("user", 3);
				recommendCourse = recommendService.getRandomCourses(3);
			}
			
			model.addAttribute("recommendTeacher", recommendTeacher);
			model.addAttribute("recommendUser", recommendUser);
			model.addAttribute("recommendCourse", recommendCourse);
					
			UserInfo userInfo = new UserInfo(user);
			Student student = studentService.findOne(user_id);
			userInfo.setStudent(student);
	
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("user_id", user_id);
			model.addAttribute("role", userInfo.getRole());
			
			Map<String,String> map = GlobalDefs.getUserEduExpMap();
			model.addAttribute("levelmap", map);
			List<EduBackground> eduInfo = eduBackgroundService
					.findEduListByTeacherId(user_id);
			model.addAttribute("eduInfo", eduInfo);
			model.addAttribute("eduCount", eduInfo.size());

			List<WorkExp> workInfo = workExpService.findWorkList(user_id);
			model.addAttribute("workInfo", workInfo);
			model.addAttribute("workCount", workInfo.size());

			
			
			return userInfo.getRole()+".resume.view";
		} else {
			return "redirect:/id/" + user_id;
		}
	}
	
	@RequestMapping(value="/user/{user_id}/friends")
	public String showUserFriends(@PathVariable Long user_id,Model model,HttpSession session){
		User user = userService.findOne(user_id);
		if (user.getRole().equals("user")) {
			UserInfo sessionUserInfo = (UserInfo) session
					.getAttribute(GlobalDefs.SESSION_USER_INFO);
			// page
			List<User> recommendTeacher = null;
			List<User> recommendUser = null;
			List<Course> recommendCourse = null;
			if (sessionUserInfo != null) {
				recommendTeacher = recommendService.getRecommendTeacher(sessionUserInfo.getId(), 3);
				recommendUser = recommendService.getRecommendUser(sessionUserInfo.getId(), 3);
				recommendCourse = recommendService.getRecommendCourses(sessionUserInfo.getId(), 3);

			}else{
				recommendTeacher = recommendService.getRandomUsers("teacher", 3);
				recommendUser = recommendService.getRandomUsers("user", 3);
				recommendCourse = recommendService.getRandomCourses(3);
			}
			
			model.addAttribute("recommendTeacher", recommendTeacher);
			model.addAttribute("recommendUser", recommendUser);
			model.addAttribute("recommendCourse", recommendCourse);
					
			UserInfo userInfo = new UserInfo(user);

			model.addAttribute("userInfo", userInfo);
			model.addAttribute("user_id", user_id);

			model.addAttribute("role", userInfo.getRole());
			// common model -end
			
			List<User> fansInfoList = relateService.getAllFansInfo(user_id);
			model.addAttribute("fansList", fansInfoList);
			List<User> hostsInfoList = relateService.getAllHostInfo(user_id);
			model.addAttribute("hostList", hostsInfoList);
			
			
			
			return userInfo.getRole()+".friends.list";
		} else {
			return "redirect:/id/" + user_id;
		}
	}

	
}
