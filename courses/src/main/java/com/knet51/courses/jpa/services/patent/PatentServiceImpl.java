package com.knet51.courses.jpa.services.patent;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specifications;
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
	public Patent findOne(String patentNum) {
		return patentRespository.findOne(patentNum);
	}

	@Override
	public Page<Patent> searchPatent(int pageNum, int pageSize, Long type, String search,
			String params) {
		List<Patent> listAll = patentRespository.findPatentList(type,search, params);
		List<Patent> list = patentRespository.findPatentPage(type,search, params, pageNum, pageSize);
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "patentNum");
		Page<Patent> page = new PageImpl<Patent>(list, pageable, listAll.size());
		return page;
	}

	@Override
	public Page<Patent> findPatentPage(int pageNum, int pageSize,
			String patentNum, String patentName, String inventer) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "patentNum");
		Page<Patent> page = patentRespository.findPatentPage(patentNum, patentName, inventer, pageable);
		return page;
	}

	@Override
	public Page<Patent> findPatentByPatentNumAndPatentNameAndInventer(
			int pageNum, int pageSize, String patentNum, String patentName,
			String inventer) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "patentNum");
		Page<Patent> page =patentRespository.findPatentByPatentNumLikeAndPatentNameLikeAndInventerLike(patentNum, patentName, inventer, pageable);
		return page;
	}

	@Override
	public List<Patent> findPatentList() {
		return patentRespository.findAll();
	}

	@Override
	public Page<Patent> findAll(int pageNum, int pageSize) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "patentNum");
		Page<Patent> page = patentRespository.findAll(pageable);
		return page;
	}

	@Override
	public Page<Patent> findPatentByPatentField(int pageNum, int pageSize,
			String patentField) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "patentNum");
		Page<Patent> page = patentRespository.findPatentByPatentField(patentField, pageable);
		return page;
	}



}
