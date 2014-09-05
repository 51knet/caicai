package com.knet51.courses.controllers.teacher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.knet51.ccweb.jpa.entities.EduBackground;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.consult.ConsultComment;
import com.knet51.ccweb.jpa.entities.consult.ConsultOrder;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.courses.beans.UserInfo;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.TeacherService;
import com.knet51.courses.jpa.services.UserService;
import com.knet51.courses.jpa.services.achievement.EduBackgroundService;
import com.knet51.courses.jpa.services.patent.PatentService;
import com.knet51.courses.jpa.services.technology.TechnologyService;
import com.knet51.courses.jpa.services.trade.ConsultCommentService;
import com.knet51.courses.jpa.services.trade.ConsultOrderService;
import com.knet51.courses.util.MyUtil;
import com.knet51.courses.util.ajax.AjaxValidationEngine;
import com.knet51.courses.util.ajax.ValidationResponse;
import com.knet51.courses.util.mailSender.MailSender;

@Controller
public class TeacherConsultController {
	private static Logger logger = LoggerFactory.getLogger(TeacherConsultController.class);
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;
	@Autowired
	private EduBackgroundService eduBackgroundService;
	@Autowired
	private PatentService patentService;
	@Autowired
	private TechnologyService technologyService;
	@Autowired
	private ConsultOrderService orderService;
	@Autowired
	private ConsultCommentService commentService;
	
	@RequestMapping(value="/teacher/{teacher_id}/consult")
	public String consultPage(@PathVariable Long teacher_id,Model model){
		User user = userService.findOne(teacher_id);
		Teacher teacher=teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		List<EduBackground> eduInfo = eduBackgroundService.findEduListByTeacherId(teacher_id);
		model.addAttribute("eduInfo", eduInfo);
		model.addAttribute("eduCount", eduInfo.size());
		
		Page<Patent> patentPage = patentService.findAll(0, 1);
		model.addAttribute("patent", patentPage.getContent());

		model.addAttribute("teacher_id", teacher_id);
		model.addAttribute("teacherInfo", userInfo);
		return "teacher.consult"; 
	}
	
	@RequestMapping(value="/teacher/{teacher_id}/consult/cart")
	public String cartPage(@PathVariable Long teacher_id,Model model){
		User user = userService.findOne(teacher_id);
		Teacher teacher=teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);

		model.addAttribute("teacher_id", teacher_id);
		model.addAttribute("teacherInfo", userInfo);
		return "teacher.consult.cart";
	}
	
	@RequestMapping(value="/teacher/{teacher_id}/consult/pay")
	public String payController(@PathVariable Long teacher_id,Model model,@Valid ConsultCartForm cartForm, BindingResult result){
		User user = userService.findOne(teacher_id);
		Teacher teacher=teacherService.findOne(teacher_id);
		UserInfo userInfo = new UserInfo(user);
		userInfo.setTeacher(teacher);
		model.addAttribute("teacher_id", teacher_id);
		model.addAttribute("teacherInfo", userInfo);
		if(result.hasErrors()){
			return "redirect:/teacher/"+teacher_id+"/consult/cart";
		}else{
			String vaildEmail = cartForm.getEmail();
			String name = cartForm.getUsername();
			String sendInfo = "<div>亲爱的注册用户，您好：</div><div>感谢您登录本网站，您的初始登录密码为："+GlobalDefs.DEFAULT_PWD+"，登录账号为您填写表格时的邮箱。</div>";
			User consulter = validOrCreateUserByEmail(vaildEmail,name,sendInfo);
			ConsultOrder cart = new ConsultOrder();
			cart.setConsulter(consulter);
			cart.setTeacher(user);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			String date = format.format(new Date());
			cart.setDate(date);
			MyUtil.copyValidBeanToDestBean(cartForm, cart);
			orderService.create(cart);
		}
		
		return "teacher.consult.pay";
	}
	
	@ResponseBody
	@RequestMapping(value="/teacher/{teacher_id}/consult/comment/list", method = RequestMethod.POST,produces = "text/html;charset=UTF-8" )
	public String showConsultComment(@PathVariable Long teacher_id, @RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="3") int pageSize) throws Exception{
		if(pageNumber<0){
			pageNumber = 0;
		}
		int maxPage = 0;
		StringBuilder sb = new StringBuilder();
		User teacher = userService.findOne(teacher_id);
		Page<ConsultComment> page =  commentService.findAllByTeacher(teacher, pageNumber, pageSize);
		maxPage = page.getTotalPages();
		sb.append("<table class='table table-hover' ><thead><tr><th width='20%'>姓名</th><th>评论内容</th><th>评分</th><th width='20%'>时间</th></tr></thead><tbody>");
		for (int i = 0; i < page.getContent().size(); i++) {
			sb.append("<tr><td ><div style='max-width:120px;max-height:20px; overflow:hidden;'>"+page.getContent().get(i).getConsulter().getName()+"</div></td>" +
					"<td  ><div style='max-width:440px; max-height:20px;overflow:hidden;'>"+page.getContent().get(i).getContent()+"</td><td>"+page.getContent().get(i).getScore()
					+" 分</td><td>"+MyUtil.chengeDateToString(page.getContent().get(i).getDate())+"</td></tr>");
		}
		
		if(pageNumber > maxPage){
			pageNumber = maxPage;
		}
		
		sb.append(getAjaxPage(pageNumber, maxPage, "comment"));
		return sb.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/teacher/{teacher_id}/consult/order/list", method = RequestMethod.POST,produces = "text/html;charset=UTF-8" )
	public String showConsultOrder(@PathVariable Long teacher_id, @RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="3") int pageSize) throws Exception{
		if(pageNumber<0){
			pageNumber = 0;
		}
		int maxPage = 0;
		//Teacher teacher = teacherService.findOne(teacher_id);
		User teacher = userService.findOne(teacher_id);
		StringBuilder sb = new StringBuilder();
		//Page<Technology> page = technologyService.findAllByStatus(pageNumber, pageSize, GlobalDefs.PASS);
		Page<ConsultOrder> page = orderService.findAllByTeacher(pageNumber, pageSize, teacher);
		maxPage = page.getTotalPages();
		sb.append("<table class='table table-hover' ><thead><tr><th width='20%'>咨询人</th><th>咨询内容</th><th width='20%'>时间</th></tr></thead><tbody>");
		for (int i = 0; i < page.getContent().size(); i++) {
			sb.append("<tr><td ><div style='max-width:120px;max-height:20px; overflow:hidden;'>"+page.getContent().get(i).getConsulter().getName()+"</div></td>" +
					"<td ><div  style='max-width:440px;max-height:20px; overflow:hidden;'>"+page.getContent().get(i).getTitle()+
					"</div></td><td>"+page.getContent().get(i).getDate()+"</td></tr>");
		}
		
		if(pageNumber > maxPage){
			pageNumber = maxPage;
		}
		
		sb.append(getAjaxPage(pageNumber, maxPage, "order"));
		return sb.toString();
	}
	
	
	private String getAjaxPage(int pageNumber,int maxPage, String type){
		StringBuilder s = new StringBuilder();
		if(pageNumber <= 0){
			s.append("</tbody></table>" +
					"<div class='pagination'><ul><li><a href='javascript:void(0)' onclick='ajaxPage("+(pageNumber)+",\""+type+"\")'>上一页 </a></li><li><a href='javascript:void(0)'>第"+(pageNumber+1)+"页</a></li><li> <a href='javascript:void(0)' onclick='ajaxPage("+(pageNumber+1)+",\""+type+"\")'>下一页 </a></li></ul></div>");
		}else if(pageNumber >= maxPage){
			s.append("</tbody></table>" +
					"<div class='pagination'><ul><li><a href='javascript:void(0)' onclick='ajaxPage("+(pageNumber-1)+",\""+type+"\")'>上一页 </a></li><li><a href='javascript:void(0)'>第"+(pageNumber+1)+"页</a></li><li> <a href='javascript:void(0)' onclick='ajaxPage("+(pageNumber)+",\""+type+"\")'>下一页 </a></li></ul></div>");
		}else{
			s.append("</tbody></table>" +
					"<div class='pagination'><ul><li><a href='javascript:void(0)' onclick='ajaxPage("+(pageNumber-1)+",\""+type+"\")'>上一页 </a></li><li><a href='javascript:void(0)'>第"+(pageNumber+1)+"页</a></li><li> <a href='javascript:void(0)' onclick='ajaxPage("+(pageNumber+1)+",\""+type+"\")'>下一页 </a></li></ul></div>");
		}
		return s.toString();
	}
	
	@RequestMapping(value = "/teacher/consult/cartInfoAjax", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse consultCartFormAjaxJson(@Valid ConsultCartForm cartForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	private User validOrCreateUserByEmail(String fastEmail,String name, String sendInfo){
		User user = userService.getValidEmail(fastEmail);

		if(user == null){
			user = new User();
			user.setName(name);
			user.setEmail(fastEmail);
			user.setPassword(GlobalDefs.DEFAULT_PWD);
			user.setName(fastEmail);
			user.setPhoto_url(GlobalDefs.DEFAULT_PHOTO_URL);
			user.setRole(GlobalDefs.USER_ROLE);
			user.setRandomUrl("pass");
			user = userService.createUser(user);
			MailSender.getInstance().SendConsultUploadMail(fastEmail, sendInfo);
			logger.info("==== user not exist "+user.getName());
		}else{
			logger.info("==== user is exist "+user.getName());
		}

		return user;
	}
	
}
