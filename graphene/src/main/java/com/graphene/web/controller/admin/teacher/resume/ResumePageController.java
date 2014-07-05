package com.graphene.web.controller.admin.teacher.resume;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.graphene.web.common.beans.UserInfo;
import com.graphene.web.common.defs.GlobalDefs;
import com.graphene.web.jpa.entity.resume.EduBackground;
import com.graphene.web.jpa.entity.resume.WorkExp;
import com.graphene.web.jpa.entity.teacher.TeacherHonor;
import com.graphene.web.jpa.entity.teacher.TeacherPatent;
import com.graphene.web.jpa.entity.teacher.TeacherProject;
import com.graphene.web.jpa.entity.teacher.TeacherThesis;
import com.graphene.web.service.TeacherService;
import com.graphene.web.service.UserService;
import com.graphene.web.service.achievement.TeacherHonorService;
import com.graphene.web.service.achievement.TeacherPatentService;
import com.graphene.web.service.achievement.TeacherProjectService;
import com.graphene.web.service.achievement.TeacherThesisService;
import com.graphene.web.service.resume.EduBackgroundService;
import com.graphene.web.service.resume.WorkExpService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class ResumePageController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;
	@Autowired
	private EduBackgroundService eduBackgroundService;
	@Autowired
	private WorkExpService workExpService;
	@Autowired
	private TeacherThesisService thesisService;
	@Autowired
	private TeacherProjectService projectService;
	@Autowired
	private TeacherPatentService patentService;
	@Autowired
	private TeacherHonorService honorService;
	

	@Transactional
	@RequestMapping(value = "/admin/resume")
	public String resumePage(@RequestParam("active") String active,
			Model model, HttpSession session) {
		String universityFilePath = "";
		List<String> universityList = new ArrayList<String>();
		BufferedReader br;
		universityFilePath = session.getServletContext().getRealPath("/");
		universityFilePath += "resources\\university\\universities.property";
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					universityFilePath), "utf-8"));
			String data = "";
			while ((data = br.readLine()) != null) {
				universityList.add(data);
			}
			br.close();
		} catch (FileNotFoundException e) {
			universityList.add(" ");
		} catch (IOException e) {
			universityList.add(" ");
		}
		UserInfo userInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		try {
			if (active == null || active.equals("")) {
				active = "personal";
			}
			model.addAttribute("active", active);
			model.addAttribute("universityList", universityList);
			Map<String,String> map = GlobalDefs.getUserEduExpMap();
			model.addAttribute("levelmap", map);
		
			List<EduBackground> eduInfo = eduBackgroundService
					.findEduListByTeacherId(userInfo.getId());
			if (eduInfo != null) {
				model.addAttribute("eduInfo", eduInfo);
				model.addAttribute("eduCount", eduInfo.size());
			}
			List<WorkExp> workInfo = workExpService.findWorkList(userInfo.getId());
			if (workInfo != null) {
				model.addAttribute("workInfo", workInfo);
				model.addAttribute("workCount", workInfo.size());
			}
			if(userInfo != null && !userInfo.getRole().equals("user")){
				
				List<TeacherThesis> thesisList = thesisService.getAllThesisById(userInfo.getId());
				model.addAttribute("thesisList", thesisList);
				model.addAttribute("thesisCount", thesisList.size());
				
				List<TeacherProject> projectList = projectService.getAllProjectById(userInfo.getId());
				model.addAttribute("projectList", projectList);
				model.addAttribute("projectCount", projectList.size());
				
				List<TeacherPatent> patentList = patentService.getAllPatentById(userInfo.getId());
				model.addAttribute("patentList", patentList);
				model.addAttribute("patentCount", patentList.size());
				
				List<TeacherHonor> honorList = honorService.getAllHonorById(userInfo.getId());
				model.addAttribute("honorList", honorList);
				model.addAttribute("honorCount", honorList.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "admin."+userInfo.getRole()+".resume";		
	}
	
	@RequestMapping(value="/admin/thesisInfo/edit/ajax",method = RequestMethod.POST)
	public void getThesisJson(@RequestParam ("thesisId") Long thesis_id,HttpServletResponse response,HttpSession session) throws Exception{
		TeacherThesis teacherThesisInfo = thesisService.findOneById(Long.valueOf(thesis_id));
		PrintWriter out = response.getWriter();
		Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		out.write(g.toJson(teacherThesisInfo));
		out.flush();
		out.close();
	}
	@RequestMapping(value="/admin/projectInfo/edit/ajax",method = RequestMethod.POST)
	public void getProjectJson(@RequestParam ("projectId") Long project_id,HttpServletResponse response,HttpSession session) throws Exception{
		TeacherProject teacherProjectInfo = projectService.findOneById(Long.valueOf(project_id));
		PrintWriter out = response.getWriter();
		Gson g =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		out.write(g.toJson(teacherProjectInfo));
		out.flush();
		out.close();
	}
	@RequestMapping(value="/admin/patentInfo/edit/ajax",method = RequestMethod.POST)
	public void getPatentJson(@RequestParam ("patentId") Long patent_id,HttpServletResponse response,HttpSession session) throws Exception{
		TeacherPatent teacherPatentInfo = patentService.findOneById(Long.valueOf(patent_id));
		PrintWriter out = response.getWriter();
		Gson g =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		out.write(g.toJson(teacherPatentInfo));
		out.flush();
		out.close();
	}
	@RequestMapping(value="/admin/honorInfo/edit/ajax",method = RequestMethod.POST)
	public void getTeacherHonorJson(@RequestParam ("honorId") Long honor_id,HttpServletResponse response,HttpSession session) throws Exception{
		TeacherHonor honorInfo = honorService.findOneById(Long.valueOf(honor_id));
		PrintWriter out = response.getWriter();
		Gson g =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		out.write(g.toJson(honorInfo));
		out.flush();
		out.close();
		}
	@RequestMapping(value="/admin/eduInfo/edit/ajax",method = RequestMethod.POST)
	public void getEduJson(@RequestParam ("eduId") Long edu_id,HttpServletResponse response,HttpSession session) throws Exception{
		EduBackground eduInfo = eduBackgroundService.findOneById(Long.valueOf(edu_id));
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		out.write(g.toJson(eduInfo));
		out.flush();
		out.close();
		
	}
	
	@RequestMapping(value="/admin/workInfo/edit/ajax",method = RequestMethod.POST)
	public void getWorkJson(@RequestParam ("workId") Long work_id,HttpServletResponse response,HttpSession session) throws Exception{
		WorkExp workInfo = workExpService.findOneById(Long.valueOf(work_id));
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		out.write(g.toJson(workInfo));
		out.flush();
		out.close();
	}
}
