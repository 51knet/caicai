package com.knet51.courses.controllers.teacher;

import java.io.UnsupportedEncodingException;
import java.util.List;

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
import com.knet51.ccweb.jpa.entities.EduBackground;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.entities.technology.Technology;
import com.knet51.courses.beans.UserInfo;
import com.knet51.courses.controllers.defs.GlobalDefs;
import com.knet51.courses.jpa.services.TeacherService;
import com.knet51.courses.jpa.services.UserService;
import com.knet51.courses.jpa.services.achievement.EduBackgroundService;
import com.knet51.courses.jpa.services.patent.PatentService;
import com.knet51.courses.jpa.services.technology.TechnologyService;
import com.knet51.courses.util.ajax.AjaxValidationEngine;
import com.knet51.courses.util.ajax.ValidationResponse;

@Controller
public class TeacherConsultController {
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
		
		return "teacher.consult.pay";
	}
	
	@ResponseBody
	@RequestMapping(value="/teacher/{teacher_id}/consult/{type}/list", method = RequestMethod.POST,produces = "text/html;charset=UTF-8" )
	public String showConsultComment(@PathVariable Long teacher_id, @RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="3") int pageSize,@PathVariable String type) throws Exception{
		if(pageNumber<0){
			pageNumber = 0;
		}

		int maxPage = 0;
		StringBuilder sb = new StringBuilder();
		if(type.equals("comment")){
			Page<Patent> page = patentService.findAll(pageNumber, pageSize);
			maxPage = page.getTotalPages();
			sb.append("<table class='table table-hover' ><thead><tr><th width='15%'>姓名</th><th>咨询内容</th><th width='20%'>时间</th></tr></thead><tbody>");
			for (int i = 0; i < page.getContent().size(); i++) {
				sb.append("<tr><td>"+page.getContent().get(i).getInventer()+"</td><td>"+page.getContent().get(i).getPatentNum()+
						"</td><td>"+page.getContent().get(i).getPublishDate()+"</td></tr>");
			}
		}else if(type.equals("order")){
			Page<Technology> page = technologyService.findAllByStatus(pageNumber, pageSize, GlobalDefs.PASS);
			maxPage = page.getTotalPages();
			sb.append("<table class='table table-hover' ><thead><tr><th width='15%'>姓名</th><th>咨询内容</th><th width='20%'>时间</th></tr></thead><tbody>");
			for (int i = 0; i < page.getContent().size(); i++) {
				sb.append("<tr><td>"+page.getContent().get(i).getInventer()+"</td><td>"+page.getContent().get(i).getTechName()+
						"</td><td>"+page.getContent().get(i).getDate()+"</td></tr>");
			}
		}
		
		if(pageNumber > maxPage){
			pageNumber = maxPage;
		}
		
		sb.append(getAjaxPage(pageNumber, maxPage, type));
		return sb.toString();
	}
	
	
	@RequestMapping(value = "/teacher/consult/cartInfoAjax", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse consultCartFormAjaxJson(@Valid ConsultCartForm cartForm, BindingResult result) {
		return AjaxValidationEngine.process(result);
	}
	
	private String getAjaxPage(int pageNumber,int maxPage, String type){
		StringBuilder s = new StringBuilder();
		if(pageNumber == 0){
			s.append("</tbody></table>" +
					"<div class='pagination'><ul><li><a href='javascript:void(0)' onclick='ajaxPage("+(pageNumber)+")'>上一页 </a></li><li> <a href='javascript:void(0)' onclick='ajaxPage("+(pageNumber+1)+")'>下一页 </a></li></ul></div>");
		}else if(pageNumber == maxPage){
			s.append("</tbody></table>" +
					"<div class='pagination'><ul><li><a href='javascript:void(0)' onclick='ajaxPage("+(pageNumber-1)+")'>上一页 </a></li><li> <a href='javascript:void(0)' onclick='ajaxPage("+(pageNumber)+")'>下一页 </a></li></ul></div>");
		}else{
			s.append("</tbody></table>" +
					"<div class='pagination'><ul><li><a href='javascript:void(0)' onclick='ajaxPage("+(pageNumber-1)+")'>上一页 </a></li><li> <a href='javascript:void(0)' onclick='ajaxPage("+(pageNumber+1)+")'>下一页 </a></li></ul></div>");
		}
		return s.toString();
	}
}
