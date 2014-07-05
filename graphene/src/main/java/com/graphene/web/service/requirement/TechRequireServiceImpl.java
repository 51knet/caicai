package com.graphene.web.service.requirement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graphene.web.jpa.entity.require.TechRequirement;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.jpa.repository.TechRequireRepository;

@Service("techRequireService")
public class TechRequireServiceImpl implements TechRequireService {
	@Autowired
	private TechRequireRepository repository;
	
	@Override
	public TechRequirement create(TechRequirement requirement) {
		return repository.save(requirement);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public TechRequirement update(TechRequirement requirement) {
		return repository.saveAndFlush(requirement);
	}

	@Override
	public Page<TechRequirement> findRequireByUser(int pageNum, int pageSize,
			User user) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		return repository.findRequireByUser(user, pageable);
	}



	@Override
	public List<TechRequirement> findRequireListByUser(User user) {
		Sort sort = new Sort(Direction.DESC, "id");
		return repository.findRequirByUser(user, sort);
	}



	@Override
	public TechRequirement findOne(Long require_id) {
		return repository.findOne(require_id);
	}

	@Override
	public Page<TechRequirement> findRequireAll(int pageNum, int pageSize) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		return repository.findAll(pageable);
	}

	@Override
	public Page<TechRequirement> findRequireByStatus(int pageNum, int pageSize,
			Integer status) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		return repository.findRequireByStatus(status, pageable);
	}

	@Override
	public Page<TechRequirement> findRequireByTitleLike(int pageNum, int pageSize,
			String title) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		return repository.findReqireByTitleLike(title, pageable);
	}

}
