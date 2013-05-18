package com.knet51.ccweb.controllers.admin.enterprise;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.admin.enterprise.EnterprisePersonalInfoForm;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;


import com.knet51.ccweb.jpa.entities.Recharge;
import com.knet51.ccweb.jpa.entities.RechargeHistory;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.WithdrawsApply;
import com.knet51.ccweb.jpa.services.EduBackgroundService;
import com.knet51.ccweb.jpa.services.RechargeHistoryService;
import com.knet51.ccweb.jpa.services.RechargeService;
import com.knet51.ccweb.jpa.services.TeacherService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.WithdrawsApplyService;
import com.knet51.ccweb.jpa.services.WorkExpService;
import com.knet51.ccweb.jpa.services.achievement.TeacherHonorService;
import com.knet51.ccweb.jpa.services.achievement.TeacherPatentService;
import com.knet51.ccweb.jpa.services.achievement.TeacherProjectService;
import com.knet51.ccweb.jpa.services.achievement.TeacherThesisService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;

/**
 * Handles requests for the application home page.
 */
@Controller
public class EnterprisePageController {

	private static final Logger logger = LoggerFactory
			.getLogger(EnterprisePageController.class);

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherHonorService honorService;
	@Autowired
	private RechargeHistoryService rechargeHistoryService;
	@Autowired
	private RechargeService rechargeService;
	@Autowired
	private WithdrawsApplyService withdrawsApplyService;
	
	@Transactional
	@RequestMapping(value = "/admin/enterprisepersonalInfo", method = RequestMethod.POST)
	public String enterprisepersonalInfo(@Valid EnterprisePersonalInfoForm personalInfoForm,
			BindingResult validResult, HttpSession session,RedirectAttributes redirectAttr, HttpServletRequest request, HttpServletResponse response) {
		logger.info("#### Personal InfoController ####");
		
		if (validResult.hasErrors()) {
			logger.info("detailInfoForm Validation Failed " + validResult);
			
		} else {
			logger.info("### detailInfoForm Validation passed. ###");
			
			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			User user = userService.findOne(userInfo.getId());
			user.setName(personalInfoForm.getName());
			user = userService.updateUser(user);
			Teacher teacher = new Teacher(user);
			teacher.setIsEnterprise("1");
			teacher = teacherService.updateTeacher(teacher);
			userInfo.setUser(user);
			userInfo.setTeacher(teacher);
			session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
			
			String message = "个人信息保存成功";
			redirectAttr.addFlashAttribute("message", message);
		}
		return "redirect:/admin/resume?active=personal";
	}
	
//	@Transactional
//	@RequestMapping(value = "/admin/enterprsie/selfurl" , method = RequestMethod.POST)
//	public String selfUrl(@Valid TeacherSelfUrlForm selfUrlForm,
//			BindingResult validResult, HttpSession session) {
//
//		logger.info("### in self url controller ###");
//
//		if (validResult.hasErrors()) {
//			logger.info("selfUrlForm Validation Failed " + validResult);
//			return "redirect:/admin/enterprise/details?active=url";
//		} else {
//			logger.info("### detailInfoForm Validation passed. ###");
//			String url = selfUrlForm.getUrl();
//			boolean usableUrl = false;
//			try {
//				usableUrl = userService.usableUrl(url);
//			} catch (Exception e) {
//				usableUrl = false;
//			}
//			if (usableUrl) {
//				UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
//				User user = userService.findOne(userInfo.getId());
//				user.setSelf_url(url);
//				user = userService.updateUser(user);
//				userInfo.setUser(user);
//				session.setAttribute(GlobalDefs.SESSION_USER_INFO, userInfo);
//			}
//			return "redirect:/admin/enterprise/details?active=url";
//		}
//	}
//	
//	
//	@RequestMapping(value="/admin/enterprise/honor/new" , method = RequestMethod.POST)
//	public String addHonor(@RequestParam("honorId") Long honorId, @Valid TeacherHonorDetailInfoForm honorDetailForm, HttpSession session,
//			Model model,BindingResult validResult){
//		logger.info("#### Into enterprsieHonnerAddController ####");
//		if(validResult.hasErrors()){
//			return "redirect:/admin/enterprise/resume?active=honor";
//		}else{
//			UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
//			
//			TeacherHonor honor = null;
//			if(honorId!=null){
//				honor=honorService.findOneById(honorId);
//				honor.setName(honorDetailForm.getHonorName());
//				honor.setReason(honorDetailForm.getReason());
//				honor.setDesc(honorDetailForm.getHonorDesc());
//				honorService.update(honor);
//			}else{
//				honor=new TeacherHonor();
//				honor.setName(honorDetailForm.getHonorName());
//				honor.setReason(honorDetailForm.getReason());
//				honor.setDesc(honorDetailForm.getHonorDesc());
//				honorService.save(honor, userInfo.getTeacher());
//			}
//			return "redirect:/admin/enterprise/resume?active=honor";
//		}
//	}
//	
//	@RequestMapping(value="/admin/enterprise/honor/destory",method=RequestMethod.POST)
//	public String deleHonor(@RequestParam("honorId")Long honor_id){
//		honorService.deleteById(Long.valueOf(honor_id));
//		return "redirect:/admin/enterprise/resume?active=honor";
//	}
//	
//	
//
//	@Transactional
//	@RequestMapping(value = "/admin/enterprise/resume")
//	public String resumePage(@RequestParam("active") String active,
//			Model model, HttpSession session) {
//	
//		if (active == null || active.equals("")) {
//			active = "personal";
//		}
//		UserInfo userInfo = (UserInfo) session
//				.getAttribute(GlobalDefs.SESSION_USER_INFO);
//		List<TeacherHonor> honorList = honorService.getAllHonorById(userInfo.getId());
//		model.addAttribute("honorList", honorList);
//		model.addAttribute("honorCount", honorList.size());
//		model.addAttribute("active", active);
//		return "admin.enterprise.resume";
//	}
	/**
	 * show enterprise account info
	 * @param session
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/admin/account/list")
	public String showAccountList(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		logger.info("======= into enterprise account controller");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Page<RechargeHistory> page = rechargeHistoryService.findAllByUser(pageNumber, pageSize, userInfo.getUser());
		model.addAttribute("page", page);
		return "admin.enterprise.account.list";
	}
	/**
	 * into create recharge page
	 * @return
	 */
	@RequestMapping(value="/admin/recharge/create")
	public String rechargeAccount(){
		return "admin.enterprise.account.recharge.new";
	}
	
	@RequestMapping(value = "/admin/enterpriseInfoAJAX", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse enterpriseFormAjaxJson(@Valid EnterprisePersonalInfoForm personalInfoForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
	
	/**
	 * check the cardid if it exist in DB
	 * @param cardid
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/recharge/checkCardid")
	public void checkCardid(@RequestParam("cardid") String cardid,HttpServletResponse response) throws Exception{
		logger.info("==== into checkCardid controller ==="+cardid);
		PrintWriter writer = response.getWriter();
		Recharge recharge = rechargeService.findOneByCardid(cardid);
		writer.print(recharge!=null?"yes":"no");
		writer.flush();
		writer.close();
	}
	/**
	 * cardForm ajax check
	 * @param cardForm
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/admin/recharge/createRechargeAjax", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse rechargeFormAjaxJson(@Valid EnterpriseRechargeCardForm cardForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
	
	/**
	 * show user withdraws list
	 * @param session
	 * @param model
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/admin/withdraws/list")
	public String showMyWithdrawsList(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="5") int pageSize){
		logger.info("=== into withdraws page controller ===");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Page<WithdrawsApply> page = withdrawsApplyService.findAllByUser(pageNumber, pageSize, userInfo.getUser());
		model.addAttribute("page", page);
		return "admin.enterprise.withdraws.list";
	}
	
	/**
	 * withdrawsApply form ajax check
	 * @param cardForm
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/admin/withdraws/createWithdrawsAjax")
	public @ResponseBody ValidationResponse withdrawsApplyFormAjaxJson(@Valid WithdrawsApplyForm applyForm, BindingResult result,HttpSession session) {
		logger.info("hehehehehehehhee");
		return AjaxValidationEngine.process(result);
	}
}