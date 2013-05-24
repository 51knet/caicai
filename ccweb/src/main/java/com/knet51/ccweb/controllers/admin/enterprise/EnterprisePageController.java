package com.knet51.ccweb.controllers.admin.enterprise;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.admin.enterprise.EnterprisePersonalInfoForm;
import com.knet51.ccweb.controllers.admin.user.WithdrawsApplyForm;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;


import com.knet51.ccweb.jpa.entities.Recharge;
import com.knet51.ccweb.jpa.entities.Enterprise;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.RechargeHistoryService;
import com.knet51.ccweb.jpa.services.RechargeService;
import com.knet51.ccweb.jpa.services.EnterpriseService;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.WithdrawsApplyService;
import com.knet51.ccweb.jpa.services.achievement.EnterpriseIntroService;
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
	private EnterpriseService enterpriseService;
	@Autowired
	private UserService userService;
	@Autowired
	private EnterpriseIntroService introService;
	@Autowired
	private RechargeHistoryService rechargeHistoryService;
	@Autowired
	private RechargeService rechargeService;
	@Autowired
	private WithdrawsApplyService withdrawsApplyService;

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
	
}
