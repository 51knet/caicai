package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.User;
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
	public List<Trends> showTrendsByUser(User user) {
		Sort sort = new Sort(Direction.DESC, "id"); 
		return trendsRepository.findByUser(user , sort);
	}


	@Override
	public Trends findOneById(Long trend_id) {
		return trendsRepository.findOne(trend_id);
	}

	@Override
	public Page<Trends> showTrendsByUserId(int pageNum, int pageSize, Long u_id) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		
		return trendsRepository.findAllByUserId(u_id, pageable);
	}

	@Override
	public List<Trends> showAllTrendsByUserId(Long u_id) {
		return trendsRepository.findAllByUser(u_id);
	}

	@Override
	public List<Trends> showAllTeacherTrendsByUserId(Long u_id) {
		return trendsRepository.findTeacherAllByUser(u_id);
	}

	@Override
	public List<Trends> showAllTrendsByUserIdAndRole(Long u_id, String role) {
		return trendsRepository.findAllByUserAndRole(u_id, role);
	}

	@Override
	public List<Trends> showAllTrendsByUserIdAndRoleAndVariety(Long u_id,
			String role, String variety) {
		return trendsRepository.findAllByUserAndRoleAndVariety(u_id, role, variety);
	}


	@Override
	public List<Trends> showAllTrendsByUserIdAndVariety(Long u_id,
			String variety) {
		return trendsRepository.findAllByUserAndVariety(u_id, variety);
	}

}
