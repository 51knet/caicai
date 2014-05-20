package com.knet51.patents.jpa.services.applyright;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.applyright.CoApplyRight;
import com.knet51.ccweb.jpa.repository.applyright.CoApplyRightRepository;
@Service("coApplyRightService")
public class CoApplyRightServiceImpl implements CoApplyRightService {
	@Autowired
	private CoApplyRightRepository coApplyRightRepository;

	@Override
	public CoApplyRight create(CoApplyRight coApplyRight) {
		return coApplyRightRepository.save(coApplyRight);
	}

	@Override
	public CoApplyRight update(CoApplyRight coApplyRight) {
		return coApplyRightRepository.save(coApplyRight);
	}

	@Override
	public void delete(Long id) {
		coApplyRightRepository.delete(id);
	}

	@Override
	public CoApplyRight find(Long id) {
		return coApplyRightRepository.findOne(id);
	}

	@Override
	public Page<CoApplyRight> findCoApplyRightPage(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		
		return coApplyRightRepository.findAll(pageable);
	}

	@Override
	public Page<CoApplyRight> findCoApplyRightByStatusAndComApplypermit(
			Integer status, String applypermit, int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return coApplyRightRepository.findCoApplyRightByStatusAndComApplypermit(status, applypermit, pageable);
	}

	@Override
	public Page<CoApplyRight> findCoApplyRightByStatus(Integer status,
			int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		return coApplyRightRepository.findCoApplyRightByStatus(status, pageable);
	}

}
