package com.graphene.web.service.patent;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.graphene.web.jpa.entity.patent.Patent;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.jpa.repository.patent.PatentRepository;


@Service("patentService")
public class PatentServiceImpl implements PatentService {
	@Autowired
	private PatentRepository patentRespository;
	
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
	public void delete(Long id) {
		patentRespository.delete(id);
		
	}

	@Override
	public Patent findOne(Long id) {
		return patentRespository.findOne(id);
	}

	@Override
	public Page<Patent> findPatent(int pageNum, int pageSize) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "publishDate","patentNum","focus");
		Page<Patent> page = patentRespository.findAll(pageable);
		return page;
	}

	@Override
	public Page<Patent> findPatentByCountryAndStatus(int pageNum, int pageSize,
			Integer country,Integer status) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "publishDate","patentNum","focus");
		Page<Patent> page = patentRespository.findPatentByCountryAndStatus(country,status,pageable);
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
