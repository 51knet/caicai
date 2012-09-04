package com.knet51.ccweb.util.mailSender;

import java.util.Random;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;

public class MailSender {

	private static MailSender instance = null;

	private MailSender() {
		// TODO Auto-generated constructor stub
	}

	public static synchronized MailSender getInstance() {
		if (instance == null) {
			instance = new MailSender();
		}
		return instance;
	}

	public void SendMail(String sendToAddress, String url) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("caicaiadmin@163.com");
		mailInfo.setPassword("123qweasd");
		mailInfo.setFromAddress("caicaiadmin@163.com");
		mailInfo.setToAddress(sendToAddress);
		mailInfo.setSubject("Thanks for register to caicai web!");
		mailInfo.setContent("Thanks for your registering, please click the url for complete your information:\n"
				+ url);
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);
	}

	public String produceRandomString() {
		String radStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuffer generateRandStr = new StringBuffer();
		Random rand = new Random();
		int length = 32;
		for (int i = 0; i < length; i++) {
			int randNum = rand.nextInt(52);
			generateRandStr.append(radStr.substring(randNum, randNum + 1));
		}
		return generateRandStr.toString();
	}

	public boolean SendConfirmMail(String mail, UserService userService) {
		String randomUrl = produceRandomString();
		User user = userService.findByEmailAddress(mail);
		if (user != null) {
			user.setRandomUrl(randomUrl);
			userService.updateUser(user);
			randomUrl += "/";
			randomUrl += user.getId();
			SendMail(mail, "http://localhost:8080/ccweb/mail/" + randomUrl);
			return true;
		} else {
			return false;
		}
	}

}
