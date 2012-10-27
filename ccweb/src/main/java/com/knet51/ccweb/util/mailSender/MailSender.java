package com.knet51.ccweb.util.mailSender;

import java.util.Random;

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

	public boolean SendMail(String sendToAddress, String url) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("caicaiadmin@163.com");
		mailInfo.setPassword("123qweasd");
		mailInfo.setFromAddress("caicaiadmin@163.com");
		mailInfo.setToAddress(sendToAddress);
		mailInfo.setSubject("Thanks for register to caicai web!");
		// mailInfo.setContent("Thanks for your registering, please click the url for complete your information:\n"
		// + url);
		mailInfo.setContent("<font style='BACKGROUND-COLOR: #666699' color='#ff0000' size='5'>邮箱验证<a href='"
				+ url
				+ "'>点击验证邮箱</a></font><img src='http://www.google.cn/intl/zh-CN/images/logo_cn.gif'></img>");
		mailInfo.setTimeout("10000");
		SimpleMailSender sms = new SimpleMailSender();
		return sms.sendHtmlMail(mailInfo);
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

}
