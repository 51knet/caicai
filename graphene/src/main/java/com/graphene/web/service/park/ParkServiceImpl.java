package com.graphene.web.service.park;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.graphene.web.jpa.entity.park.Park;
import com.graphene.web.jpa.repository.park.ParkRepository;
@Service("parkService")
public class ParkServiceImpl implements ParkService {
	@Autowired
	private ParkRepository parkRepository;
	@Override
	public Park create(Park park) {
		
		return parkRepository.save(park);
	}

	@Override
	public Park update(Park park) {
		// TODO Auto-generated method stub
		return parkRepository.save(park);
	}

	@Override
	public void delete(Long id) {
		parkRepository.delete(id);
		
	}

	@Override
	public Park find(Long id) {
		
		return parkRepository.findOne(id);
	}

	@Override
	public Page<Park> findAll(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<Park> page = parkRepository.findAll(pageable);
		return page;
	}

}
