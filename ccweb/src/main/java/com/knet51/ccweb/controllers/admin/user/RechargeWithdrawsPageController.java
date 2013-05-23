package com.knet51.ccweb.controllers.admin.user;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.entities.RechargeHistory;
import com.knet51.ccweb.jpa.entities.WithdrawsApply;
import com.knet51.ccweb.jpa.services.RechargeHistoryService;
import com.knet51.ccweb.jpa.services.RechargeService;
import com.knet51.ccweb.jpa.services.WithdrawsApplyService;
@Controller
public class RechargeWithdrawsPageController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(RechargeWithdrawsPageController.class);
	
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
		return "admin."+userInfo.getRole()+".account.list";
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
		return "admin."+userInfo.getRole()+".withdraws.list";
	}
	
	/**
	 * into create recharge page
	 * @return
	 */
	@RequestMapping(value="/admin/recharge/create")
	public String rechargeAccount(HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		return "admin."+userInfo.getRole()+".account.recharge.new";
	}
}
