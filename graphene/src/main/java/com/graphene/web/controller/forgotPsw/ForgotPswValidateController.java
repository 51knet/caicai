package com.graphene.web.controller.forgotPsw;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.service.UserService;
import com.graphene.web.util.ajax.AjaxValidationEngine;
import com.graphene.web.util.ajax.ValidationResponse;


@Controller
public class ForgotPswValidateController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/forgotPsw/{randomUrl}/{idString}", method = RequestMethod.GET)
	public String forgotPsw(@PathVariable String randomUrl,
			@PathVariable String idString, HttpSession session) {
		Integer id = Integer.parseInt(idString);
		User result = userService.findOne(id.longValue());
		boolean userConfirmed = (result != null)
				&& randomUrl.equals(result.getForgotPsw());
		if (userConfirmed) {
			session.setAttribute("resetPswId", idString);
			return "fogotPsw.update";
		} else {
			return "home";
		}
	}
	
	@RequestMapping(value="/forgetPwdAjax", method = RequestMethod.POST)
	public void checkEmail(HttpServletResponse response,ForgotPswForm forgotPswForm) throws Exception{
		String email=forgotPswForm.getForgotemail();
		PrintWriter out=response.getWriter();
		Integer count=userService.getCountByEmail(email);
		String countString  = count.toString();
		out.write(countString);
		out.flush();
		out.close();
	}
	@RequestMapping(value = "/forgetPwdCheck", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse processFormAjaxJson(@Valid ForgotPswForm forgotPswForm, BindingResult result,HttpSession session) {
		return AjaxValidationEngine.process(result);
	}
	

}
