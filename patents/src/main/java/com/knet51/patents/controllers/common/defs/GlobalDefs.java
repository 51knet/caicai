package com.knet51.patents.controllers.common.defs;

import java.util.HashMap;
import java.util.Map;

public final class GlobalDefs {
	public static final String SESSION_USER_INFO = "sessionUserInfo";
	public static final String COOKIE_IDENTITY = "identity";
	
	//super admin's pwd
	public static final String KEFU_ADMIN_PWD = "caicai6688";
	
	// patent status
	public static final Integer PATENT_WAITE = 0;
	public static final Integer PATENT_PASS = 1;
	
	// the patent show status in home page
	public static final Integer PATENT_HOME_FOCUS = 1;
	public static final Integer PATENT_HOME_FOCUS_NOT = 0;
	
	// the patent belong to china or foreign
	public static final Integer PATENT_CHINA = 0;
	public static final Integer PATENT_FOREIGN = 1;
	
	// activity photo's width and height
	public static final int ACTIVITY_PHOTO_WIDTH = 440;
	public static final int ACTIVITY_PHOTO_HEIGHT = 220;
	
	// requirement status
	public static final Integer REQUIREMENT_WAITE = 0;
	public static final Integer REQUIREMENT_PASS = 1;
	
	// technology status
	public static final Integer TECH_WAITE = 0;
	public static final Integer TECH_PASS = 1;
	
	public static final Integer WAITE = 0;
	public static final Integer PASS = 0;
	
	// the technology show status in home page
	public static final Integer TECH_HOME_FOCUS = 1;
	public static final Integer TECH_HOME_FOCUS_NOT = 0;
	
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
}
