package com.knet51.patents.controllers.common.defs;

import java.util.HashMap;
import java.util.Map;

public final class GlobalDefs {
	public static final String SESSION_USER_INFO = "sessionUserInfo";
	public static final String COOKIE_IDENTITY = "identity";
	
	//super admin's pwd
	public static final String KEFU_ADMIN_PWD = "caicai6688";
		
	// the  show status in home page
	public static final Integer HOME_FOCUS = 1;
	public static final Integer HOME_FOCUS_NOT = 0;
	
	// the patent belong to china or foreign
	public static final Integer CHINA = 0;
	public static final Integer FOREIGN = 1;
	
	// activity photo's width and height
	public static final int ACTIVITY_PHOTO_WIDTH = 440;
	public static final int ACTIVITY_PHOTO_HEIGHT = 220;
	
	
	public static final Integer WAITE = 0;
	public static final Integer PASS = 1;
	
	public static final String RZJG = "融资机构";
	public static final String FHYQ ="孵化园区";
	
	
	public static Map<String,String> getUserEduExpMap(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("1", "幼儿园");
		map.put("2", "小学");
		map.put("3", "初中");
		map.put("4", "高中");
		map.put("5", "本科");
		map.put("6", "研究生");
		return map;
	}
	
	public static Map<String,String> getTechField(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("1", "电子信息");
		map.put("2", "先进能源");
		map.put("3", "先进制造");
		map.put("4", "生物医药");
		map.put("5", "新材料");
		map.put("6", "现代交通");
		map.put("7", "资源环境");
		map.put("8", "现代农业");
		map.put("9", "海洋技术");
		map.put("10", "经济管理");
		map.put("11", "其他领域");
		return map;
	}
	
	public static Map<String, String> getProjectsField(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("1", "互联网");
		map.put("2", "IT软件/硬件");
		map.put("3", "服务行业");
		map.put("4", "文化创意");
		map.put("5", "节能环保");
		map.put("6", "资源环境");
		map.put("7", "现代农业");
		map.put("8", "生物医药");
		map.put("9", "经济管理");
		map.put("10", "其他领域");
		return map;
	}
	
	public static Map<String, String> getApplyPermitMap(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("investor", "投资人");
		map.put("ledinvestor", "领投人");
		map.put("investcompany", "投资机构");
		map.put("incubator", "孵化园");

		return map;
	}
}
