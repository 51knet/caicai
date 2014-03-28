package com.knet51.courses.util.mailSender;

import java.util.Random;

import com.knet51.courses.controllers.defs.GlobalDefs;

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
		
		String context = "<div>亲爱的注册用户，您好：</div><div>欢迎您注册本网站，请您点击下面链接来激活您的知识网的保密邮箱:</div>";
		context += "<div><a href='" + url + "'>点击验证邮箱</a></div>";
		context += "<div>本邮件为自动发送，请勿回复。</div>";
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("caicaiadmin@163.com");
		mailInfo.setPassword("123qweasd");
		mailInfo.setFromAddress("caicaiadmin@163.com");
		mailInfo.setToAddress(sendToAddress);
		mailInfo.setSubject("感谢您注册本网站。");
		mailInfo.setContent(context);
		mailInfo.setTimeout("10000");
		SimpleMailSender sms = new SimpleMailSender();
		return sms.sendHtmlMail(mailInfo);
	}
	
	public boolean SendFastUploadMail(String sendToAddress) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		
		String context = "<div>亲爱的注册用户，您好：</div><div>感谢您的上传，您的初始登录密码为："+GlobalDefs.DEFAULT_PWD+"，登录账号为您填写表格时的邮箱。</div>";
		context += "<div>本邮件为自动发送，请勿回复。</div>";
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("caicaiadmin@163.com");
		mailInfo.setPassword("123qweasd");
		mailInfo.setFromAddress("caicaiadmin@163.com");
		mailInfo.setToAddress(sendToAddress);
		mailInfo.setSubject("感谢您的关注。");
		mailInfo.setContent(context);
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
