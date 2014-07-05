package com.graphene.web.service.allies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.graphene.web.jpa.entity.allies.Allies;
import com.graphene.web.jpa.entity.applyright.AlliesApplyRight;
import com.graphene.web.jpa.repository.allies.AlliesRepository;
import com.graphene.web.jpa.repository.applyright.AlliesApplyRepository;
@Service("alliesService")
public class AlliesServiceImpl implements AlliesService {
	@Autowired
	private AlliesRepository alliesRepository;

	@Override
	public Allies create(Allies allies) {
		
		return alliesRepository.save(allies);
	}

	@Override
	public Allies update(Allies allies) {
		 return alliesRepository.save(allies);
	}

	@Override
	public void delete(Long id) {
		alliesRepository.delete(id);
		
	}

	@Override
	public Allies find(Long id) {
	
		return alliesRepository.findOne(id);
	}

	@Override
	public Page<Allies> findAll(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<Allies> page = alliesRepository.findAll(pageable);
		return page;
	}
	

}
