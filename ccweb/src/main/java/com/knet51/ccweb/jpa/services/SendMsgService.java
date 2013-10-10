package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.ReceiveMsg;
import com.knet51.ccweb.jpa.entities.SendMsg;

public interface SendMsgService {
	ReceiveMsg add(SendMsg  sendMsg ,Long receiveId , Long senderid);
	void del(Long id);
	SendMsg  detail(Long id);
	List<SendMsg> list(Long userId);
}
