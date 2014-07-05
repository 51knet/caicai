package com.graphene.web.service.applyright;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.graphene.web.jpa.entity.applyright.ParkApplyRight;
import com.graphene.web.jpa.repository.applyright.ParkApplyRepository;
@Service("parkRightService")
public class ParkRightServiceImpl implements ParkRightService {
	@Autowired
	private ParkApplyRepository parkApplyRepository;
	@Override
	public ParkApplyRight create(ParkApplyRight parkApply) {
		
		return parkApplyRepository.save(parkApply);
	}

	@Override
	public ParkApplyRight update(ParkApplyRight parkApply) {
		// TODO Auto-generated method stub
		return parkApplyRepository.save(parkApply);
	}

	@Override
	public void delete(Long id) {
		parkApplyRepository.delete(id);
		
	}

	@Override
	public ParkApplyRight find(Long id) {
		
		return parkApplyRepository.findOne(id);
	}

	@Override
	public Page<ParkApplyRight> findAllByStatus(Integer status, int pageNumber,
			int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<ParkApplyRight> page = parkApplyRepository.findParkApplyByStatus(status, pageable);
		return page;
	}

	@Override
	public Page<ParkApplyRight> findAll(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<ParkApplyRight> page = parkApplyRepository.findAll(pageable);
		return page;
	}

}
