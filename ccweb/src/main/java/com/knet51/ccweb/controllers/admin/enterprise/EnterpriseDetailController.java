package com.knet51.ccweb.controllers.admin.enterprise;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.Recharge;
import com.knet51.ccweb.jpa.entities.RechargeHistory;
import com.knet51.ccweb.jpa.entities.WithdrawsApply;
import com.knet51.ccweb.jpa.services.RechargeHistoryService;
import com.knet51.ccweb.jpa.services.RechargeService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.WithdrawsApplyService;

@Controller
public class EnterpriseDetailController {
	private static final Logger logger = LoggerFactory
			.getLogger(EnterpriseDetailController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RechargeHistoryService rechargeHistoryService;
	@Autowired
	private RechargeService rechargeService;
	@Autowired
	private WithdrawsApplyService withdrawsApplyService;
	
	/**
	 * create recharge card
	 * @param cardForm
	 * @param validResult
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/admin/recharge/new", method = RequestMethod.POST)
	public String createRechargeHistory(@Valid EnterpriseRechargeCardForm cardForm,BindingResult validResult,HttpSession session){
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
