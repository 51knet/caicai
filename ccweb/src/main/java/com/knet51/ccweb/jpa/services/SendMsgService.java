package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.SendMsg;

public interface SendMsgService {
	void add(SendMsg  sendMsg ,Long userId);//接受信件的用户id
	void del(Long id);
	SendMsg  detail(Long id);
	List<SendMsg> list(Long userId);
}
