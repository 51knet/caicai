package com.knet51.patents.jpa.services.patent.impl;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.patent.Patent;
import com.knet51.ccweb.jpa.repository.patent.UserPatentRepository;
import com.knet51.patents.jpa.services.patent.PatentService;
@Service("patentService")
public class PatentServiceImpl implements PatentService {
	@Autowired
	private UserPatentRepository patentRespository;
	
	@Override
	public Page<Patent> findPatentByUser(int pageNum, int pageSize, User user) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "publishDate","patentNum");
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
	public Patent update(Patent patent) {
		return patentRespository.saveAndFlush(patent);
	
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
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "publishDate","patentNum","focus");
		Page<Patent> page = patentRespository.findAll(pageable);
		return page;
	}

	@Override
	public Page<Patent> findPatentByCountry(int pageNum, int pageSize,
			Integer country) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "publishDate","patentNum","focus");
		Page<Patent> page = patentRespository.findPatentByCountry(country, pageable);
		return page;
	}

	@Override
	public Page<Patent> findPatentByFocus(int pageNum, int pageSize,
			Integer focus) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "publishDate","patentNum","focus");
		Page<Patent> page = patentRespository.findPatentByFocus(focus, pageable);
		return page;
	}

	@Override
	public Page<Patent> findPatentByPatentNumLike(int pageNum, int pageSize,
			String patentNum) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "publishDate","patentNum","focus");
		Page<Patent> page = patentRespository.findPatentByPatentNumLike(patentNum, pageable);
		return page;
	}


	@Override
	public Page<Patent> findPatentByStatus(int pageNum, int pageSize,
			Integer status) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "publishDate","patentNum");
		Page<Patent> page = patentRespository.findPatentByStatus(status, pageable);
		return page;
	}

	@Override
	public Page<Patent> findPatentByPatentNameLike(int pageNum, int pageSize,
			String patentName) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "publishDate","patentNum");
		Page<Patent> page = patentRespository.findPatentByPatentNameLike("%"+patentName+"%",pageable);
		return page;
	}

	@Override
	public Page<Patent> findPatentByUserAndStatus(int pageNum, int pageSize,
			User user, Integer status) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "publishDate","patentNum");
		Page<Patent> page = patentRespository.findPatentByUserAndStatus(user, status, pageable);
		return page;
	}




}
