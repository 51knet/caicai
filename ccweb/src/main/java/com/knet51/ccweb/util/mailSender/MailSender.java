package com.knet51.ccweb.util.mailSender;

import java.util.Random;

import com.knet51.ccweb.jpa.entities.User;

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
		
		String context = "<div>亲爱的知识网注册用户，您好：</div><div>欢迎您注册知识网，" +
				"请您点击下面链接来激活您的知识网的保密邮箱:</div>";
		context += "<div><a href='" + url + "'>点击验证邮箱</a></div>";
		context += "<div>本邮件为自动发送，请勿回复。</div>";
		mailInfo.setMailServerHost("smtp.ym.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("service@51knet.com");
		mailInfo.setPassword("123456");
		mailInfo.setFromAddress("service@51knet.com");
		mailInfo.setToAddress(sendToAddress);
		mailInfo.setSubject("感谢您注册知识网。");
		mailInfo.setContent(context);
		mailInfo.setTimeout("10000");
		SimpleMailSender sms = new SimpleMailSender();
		return sms.sendHtmlMail(mailInfo);
	}
	
	public boolean SendPswMail(String sendToAddress, String url) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		
		String context = "<div>亲爱的知识网注册用户，您好：</div><div>欢迎您注册知识网，" +
				"请您点击下面链接来激活您的知识网的保密邮箱:</div>";
		context += "<div><a href='" + url + "'>点击验证邮箱</a></div>";
		context += "<div>本邮件为自动发送，请勿回复。</div>";
		mailInfo.setMailServerHost("smtp.ym.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("service@51knet.com");
		mailInfo.setPassword("123456");
		mailInfo.setFromAddress("service@51knet.com");
		mailInfo.setToAddress(sendToAddress);
		mailInfo.setSubject("感谢您注册知识网。");
		mailInfo.setContent(context);
		mailInfo.setTimeout("10000");
		SimpleMailSender sms = new SimpleMailSender();
		return sms.sendHtmlMail(mailInfo);
	}
	
	public boolean SendFocusMail(String sendToAddress, String url,Long fansid){
		MailSenderInfo mailInfo = new MailSenderInfo();
		String context = "<div>亲爱的知识网注册用户，您好：</div><div>您有新的粉丝关注您了，" +
				"请您点击下面链接来查看:</div>";
		context += "<div><a href='" + url + "/id/'"+fansid+"' '>点击查看他/她的主页</a></div>";
		context += "<div>本邮件为自动发送，请勿回复。</div>";
		mailInfo.setMailServerHost("smtp.ym.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("service@51knet.com");
		mailInfo.setPassword("123456");
		mailInfo.setFromAddress("service@51knet.com");
		mailInfo.setToAddress(sendToAddress);
		mailInfo.setSubject("感谢您使用知识网。");
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
	
	public boolean SendNotiyMail(User user, String title, String context) {
		if(user == null){
			return false;
		}
		String sendToAddress = user.getEmail();
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.ym.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("service@51knet.com");
		mailInfo.setPassword("123456");
		mailInfo.setFromAddress("service@51knet.com");
		mailInfo.setToAddress(sendToAddress);
		mailInfo.setSubject(title);
		mailInfo.setContent(context);
		mailInfo.setTimeout("10000");
		SimpleMailSender sms = new SimpleMailSender();
		return sms.sendHtmlMail(mailInfo);
	}

}
