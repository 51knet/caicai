package com.knet51.ccweb.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.knet51.ccweb.jpa.entities.SendMsg;

@Repository("sendMsgDao")
public class SendMsgDaoImpl implements SendMsgDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void add(SendMsg sendMsg) {
		em.persist(sendMsg);
	}

	@Override
	public void del(Long id) {
		SendMsg sendMsg = em.find(SendMsg.class, id);
		sendMsg.setIsDelete(2);
		em.merge(sendMsg);
	}

	@Override
	public SendMsg detail(Long id) {
		return em.find(SendMsg.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SendMsg> list(Long id) {
		List<SendMsg> sendMsgList = em.createQuery("from SendMsg where isDelete=1 and user_id="+id).getResultList();
		return sendMsgList;
	}
}
