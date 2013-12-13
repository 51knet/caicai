package com.knet51.ccweb.jpa.services.patent;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.repository.patent.UserPatentRepository;
@Service("patentService")
public class PatentServiceImpl implements PatentService {
	@Autowired
	private UserPatentRepository patentRespository;
	
	@Override
	public Page<Patent> findPatentByUser(int pageNum, int pageSize, User user) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "patentNum");
		Page<Patent> onePage = patentRespository.findPatentByUser(user, pageable);
		return onePage;
	}

	@Override
	public List<Patent> findPatentListByUser(User user) {
		return patentRespository.findPatentByUser(user);
	}

	@Override
	public void create(Patent patent) {
		patentRespository.saveAndFlush(patent);
	}

	@Override
	public void update(Patent patent) {
		patentRespository.saveAndFlush(patent);
	}

	@Override
	public void delete(String patentNum) {
		patentRespository.delete(patentNum);
		
	}

	@Override
	public Patent findOne(String patentNum) {
		return patentRespository.findOne(patentNum);
	}

	@Override
	public Page<Patent> findPatent(int pageNum, int pageSize) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "publishDate");
		Page<Patent> page = patentRespository.findAll(pageable);
		return page;
	}



}
