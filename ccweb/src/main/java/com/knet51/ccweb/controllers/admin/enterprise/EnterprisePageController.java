package com.knet51.ccweb.controllers.admin.enterprise;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knet51.ccweb.controllers.admin.enterprise.EnterprisePersonalInfoForm;


import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.jpa.services.achievement.EnterpriseIntroService;
import com.knet51.ccweb.jpa.services.enterprise.EnterpriseService;
import com.knet51.ccweb.jpa.services.trade.RechargeHistoryService;
import com.knet51.ccweb.jpa.services.trade.RechargeService;
import com.knet51.ccweb.jpa.services.trade.WithdrawsApplyService;
import com.knet51.ccweb.util.ajax.AjaxValidationEngine;
import com.knet51.ccweb.util.ajax.ValidationResponse;

/**
 * Handles requests for the application home page.
 */
@Controller
public class EnterprisePageController {

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
	
}
