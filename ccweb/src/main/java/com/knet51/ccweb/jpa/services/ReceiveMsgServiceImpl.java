package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.ReceiveMsgDao;
import com.knet51.ccweb.jpa.entities.ReceiveMsg;

@Transactional
@Service("receiveMsgService")
public class ReceiveMsgServiceImpl implements ReceiveMsgService {

	@Autowired
	private ReceiveMsgDao receiveMsgDao;
	@Override
	public void add(ReceiveMsg receiveMsg) {
		receiveMsgDao.add(receiveMsg);
	}

	@Override
	public void del(Long id) {
		receiveMsgDao.del(id);
	}

	@Override
	public void del2(Long id) {
		receiveMsgDao.del2(id);
	}

	@Override
	public void isRead(Long id) {
		receiveMsgDao.isRead(id);
	}

	@Override
	public ReceiveMsg detail(Long id) {
		return receiveMsgDao.detail(id);
	}

	@Override
	public List<ReceiveMsg> list(Long userId) {
		return receiveMsgDao.list(userId);
	}

	@Override
	public List<ReceiveMsg> unReadList(Long userId) {
		return receiveMsgDao.unReadList(userId);
	}

	@Override
	public List<ReceiveMsg> isReadList(Long userId) {
		return receiveMsgDao.isReadList(userId);
	}

	@Override
	public List<ReceiveMsg> isDele(Long userId) {
		return receiveMsgDao.isDele(userId);
	}

}