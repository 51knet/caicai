package com.knet51.ccweb.controllers.admin.user.acenter;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Recharge;
import com.knet51.ccweb.jpa.entities.RechargeHistory;
import com.knet51.ccweb.jpa.entities.WithdrawsApply;
import com.knet51.ccweb.jpa.services.RechargeHistoryService;
import com.knet51.ccweb.jpa.services.RechargeService;
import com.knet51.ccweb.jpa.services.WithdrawsApplyService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;
@Controller
public class RechargeWithdrawsController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(RechargeWithdrawsController.class);
	
	@Autowired
	private RechargeHistoryService rechargeHistoryService;
	@Autowired
	private RechargeService rechargeService;
	@Autowired
	private WithdrawsApplyService withdrawsApplyService;
	
	/**
	 * show  account info
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
		return "admin."+userInfo.getUser().getRole()+".account.list";
	}
	
	@RequestMapping(value="/acenter")
	public String showAccountCenter(HttpSession session,Model model ,@RequestParam(value="pageNumber",defaultValue="0") 
	int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		logger.info("======= into enterprise account controller");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		Page<RechargeHistory> page = rechargeHistoryService.findAllByUser(pageNumber, pageSize, userInfo.getUser());
		model.addAttribute("page", page);
		return "admin."+userInfo.getUser().getRole()+".account.list";
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
		return "admin."+userInfo.getUser().getRole()+".withdraws.list";
	}
	
	/**
	 * into create recharge page
	 * @return
	 */
	@RequestMapping(value="/admin/recharge/create")
	public String rechargeAccount(HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		return "admin."+userInfo.getUser().getRole()+".account.recharge.new";
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
	public @ResponseBody ValidationResponse rechargeFormAjaxJson(@Valid RechargeCardForm cardForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
	
	
	/**
	 * withdrawsApply form ajax check
	 * @param cardForm
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/admin/withdraws/createWithdrawsAjax", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse withdrawsApplyFormAjaxJson(@Valid WithdrawsApplyForm applyForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
	

	
	/**
	 * create recharge card
	 * @param cardForm
	 * @param validResult
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/admin/recharge/new", method = RequestMethod.POST)
	public String createRechargeHistory(@Valid RechargeCardForm cardForm,BindingResult validResult,HttpSession session){
		logger.info("=== into create rechargeHistory controller ===");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(validResult.hasErrors()){
			logger.info("RechargeCardForm Validation Failed " + validResult);
			return "redirect:/admin/recharge/create";
		}else{
			Recharge recharge = rechargeService.findOneByCardid(cardForm.getCardid());
			if(recharge!=null){
				RechargeHistory rechargeHistory = new RechargeHistory();
				rechargeHistory.setCardid(recharge.getCardid());
				rechargeHistory.setPrice(recharge.getPrice());
				rechargeHistory.setUser(userInfo.getUser());
				rechargeHistory.setDate(new Date());
				rechargeHistoryService.createRechargeHistory(rechargeHistory);
				rechargeService.deleteRechargeById(recharge.getId());
				return "redirect:/admin/account/list";
			}else{
				return "redirect:/admin/recharge/create";
			}
		}
	}
	
	/**
	 * show withdrawsapply lst and create withdrawsApply form
	 * @param withdrawsApplyForm
	 * @param validResult
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/admin/withdraws/create" ,method = RequestMethod.POST)
	public String createWithdrawsApply(@Valid WithdrawsApplyForm withdrawsApplyForm, BindingResult validResult, HttpSession session){
		logger.info("=== into create rechargeHistory controller ===");
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(validResult.hasErrors()){
			logger.info("withdrawsApplyForm Validation Failed " + validResult);
		}else{
			WithdrawsApply withdrawsApply = new WithdrawsApply();
			withdrawsApply.setContent(withdrawsApplyForm.getContent());
			withdrawsApply.setSum(Double.parseDouble(withdrawsApplyForm.getSum()));
			withdrawsApply.setDate(new Date());
			withdrawsApply.setUser(userInfo.getUser());
			withdrawsApplyService.createWithdrawsApply(withdrawsApply);
		}
		return "redirect:/admin/withdraws/list";
	}
}
