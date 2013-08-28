package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Knowledge;
import com.knet51.ccweb.jpa.repository.KnowledgeRepository;
@Transactional
@Service("knowledgeService")
public class KnowledgeServiceImpl implements KnowledgeService {
	
	@Autowired
	private KnowledgeRepository knowledgeRepository;
	@Override
	public Knowledge create(Knowledge knowledge) {
		return knowledgeRepository.saveAndFlush(knowledge);
	}

	@Override
	public Knowledge update(Knowledge knowledge) {
		return knowledgeRepository.saveAndFlush(knowledge);
	}

	@Override
	public void deleteById(Long k_id) {
		knowledgeRepository.delete(k_id);
	}

	@Override
	public Knowledge findOneById(Long k_id) {
		return knowledgeRepository.findOne(k_id);
	}

	@Override
	public List<Knowledge> findAllByUserid(Long user_id) {
		return knowledgeRepository.findKnowledgeByUserid(user_id);
	}

	@Override
	public Knowledge findOneByUseridAndCourseid(Long user_id, Long course_id) {
		return knowledgeRepository.findByUseridAndCourseid(user_id, course_id);
	}

	@Override
	public Page<Knowledge> findAllByUserid(int pageNumber, int pageSize,
			Long userid) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<Knowledge> onePage = knowledgeRepository.findByUserid(userid, pageable);
		return onePage;
	}

}
