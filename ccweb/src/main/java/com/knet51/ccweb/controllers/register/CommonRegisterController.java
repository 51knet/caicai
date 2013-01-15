package com.knet51.ccweb.controllers.register;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;
import com.knet51.ccweb.util.mailSender.MailSender;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CommonRegisterController {

	private static final Logger logger = LoggerFactory
			.getLogger(CommonRegisterController.class);

	@Autowired
	private UserService userService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "register/common", method = RequestMethod.POST)
	public String commonRegister(@Valid CommonRegisterForm commonRegisterForm,
			BindingResult validResult, Model model) {
		logger.info("#### commonRegisterController ####");

		if (validResult.hasErrors()) {
			logger.info("commonRegisterForm Validation Failed " + validResult);
			return "redirect:/";
		} else {
			String email = commonRegisterForm.getEmail();
			String psw = commonRegisterForm.getPsw();
			User findUser = userService.findByEmailAddress(email);
			if (findUser == null) {
				User user = new User(email, psw);
				boolean mailSuccess = false;
				String randomUrl = MailSender.getInstance()
						.produceRandomString();
				user.setRandomUrl(randomUrl);
				user.setPhoto_url("/resources/img/avatar/avatar90.png");
				findUser = userService.createUser(user);
				randomUrl += "/";
				randomUrl += findUser.getId();
				mailSuccess = MailSender.getInstance().SendMail(email,
						"http://www.51knet.com/ccweb/mail/" + randomUrl);
				if (mailSuccess) {
					String hrefString = email;
					hrefString = hrefString
							.substring(hrefString.indexOf("@") + 1);
					model.addAttribute("hrefString", hrefString);
					return "register.successful";
				} else {
					return "404";
				}
			} else {
				return "redirect:/";
			}
		}
	}
	@RequestMapping(value="/register/email", method = RequestMethod.POST)
	public void checkEmail(@RequestParam("email") String email,HttpServletResponse response) throws Exception{
		PrintWriter out=response.getWriter();
		Integer count=userService.getCountByEmail(email);
		String countString  = count.toString();
		System.out.println(countString);
		out.write(countString);
		out.flush();
		out.close();
	}
	
	
	
}
