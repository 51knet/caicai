package com.knet51.ccweb.jpa.dao;

import java.util.List;

import com.knet51.ccweb.jpa.entities.SendMsg;

public interface SendMsgDao {
	void add(SendMsg  sendMsg);
	void del(Long id);
	SendMsg  detail(Long id);
	List<SendMsg> list(Long id);
}
