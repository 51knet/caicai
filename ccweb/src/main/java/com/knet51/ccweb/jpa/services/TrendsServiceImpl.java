package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.timeline.Trends;
import com.knet51.ccweb.jpa.repository.TrendsRepository;

@Service("trendsService")
public class TrendsServiceImpl implements TrendsService {
	
	@Autowired
	private TrendsRepository trendsRepository;

	@Override
	public Trends createTrends(Trends trends) {
		return trendsRepository.saveAndFlush(trends);
	}

	@Override
	public void deleteTrendsById(Long trend_id) {
		trendsRepository.delete(trend_id);
	}

	@Override
	public List<Trends> showAllTrendsByUserId(Long u_id) {
		Sort sort = new Sort(Direction.DESC, "id"); 
		return trendsRepository.findAllByUserId(u_id , sort);
	}


	@Override
	public Trends findOneById(Long trend_id) {
		return trendsRepository.findOne(trend_id);
	}

	@Override
	public Page<Trends> showAllTrendsByUserId(int pageNum, int pageSize,
			Long u_id) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		
		return trendsRepository.findAllByUserId(u_id, pageable);
	}


}
