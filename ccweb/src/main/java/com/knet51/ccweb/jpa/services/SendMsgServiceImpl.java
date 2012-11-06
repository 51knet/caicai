package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.dao.ReceiveMsgDao;
import com.knet51.ccweb.jpa.dao.SendMsgDao;
import com.knet51.ccweb.jpa.entities.ReceiveMsg;
import com.knet51.ccweb.jpa.entities.SendMsg;
import com.knet51.ccweb.jpa.entities.User;

@Transactional
@Service("sendMsgService")
public class SendMsgServiceImpl implements SendMsgService {
	
	@Autowired
	private SendMsgDao sendMsgDao;
	
	@Autowired
	private ReceiveMsgDao receiveMsgDao;
	
	@Autowired
	private UserService userService;

	@Override
	public void add(SendMsg sendMsg, Long userId) {
		sendMsgDao.add(sendMsg);
		ReceiveMsg receiveMsg = new ReceiveMsg();
		receiveMsg.setDeled(1);
		receiveMsg.setReaded(1);
		//UserInfo userInfo = new UserInfo();
		User user = userService.findOne(userId);
		//user.setId(userId);
		receiveMsg.setUser(user);
		receiveMsg.setSendMsg(sendMsg);
		receiveMsgDao.add(receiveMsg);
		
	}

	@Override
	public void del(Long id) {
		sendMsgDao.del(id);
	}

	@Override
	public SendMsg detail(Long id) {
		return sendMsgDao.detail(id);
	}

	@Override
	public List<SendMsg> list(Long userId) {
		return sendMsgDao.list(userId);
	}

}
