package com.graphene.web.service.applyright;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graphene.web.jpa.entity.applyright.AlliesApplyRight;
import com.graphene.web.jpa.entity.applyright.ParkApplyRight;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.jpa.repository.UserRepository;
import com.graphene.web.jpa.repository.applyright.AlliesApplyRepository;
import com.graphene.web.jpa.repository.applyright.ParkApplyRepository;

@Service("alliesRightService")
public class AlliesRightServiceImpl implements AlliesRightService {
	@Autowired
	private AlliesApplyRepository alliesApplyRepository;
	@Autowired
	private ParkApplyRepository parkApplyRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public AlliesApplyRight create(AlliesApplyRight allieApply) {
		
		return alliesApplyRepository.save(allieApply);
	}

	@Override
	public AlliesApplyRight update(AlliesApplyRight allieApply) {
		 return alliesApplyRepository.save(allieApply);
	}

	@Override
	public void delete(Long id) {
		alliesApplyRepository.delete(id);
		
	}

	@Override
	public AlliesApplyRight find(Long id) {
	
		return alliesApplyRepository.findOne(id);
	}

	@Override
	public Page<AlliesApplyRight> findAllByStatus(int pageNumber, int pageSize,
			Integer status) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<AlliesApplyRight> page = alliesApplyRepository.findAllieApplyByStatus(status, pageable);
		return page;
	}

	@Override
	public Page<AlliesApplyRight> findAll(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<AlliesApplyRight> page = alliesApplyRepository.findAll(pageable);
		return page;
	}
	@Transactional
	@Override
	public boolean empower4User(Long apply_id, User user, String types) {
		boolean flag = false;
		if(types.equals("allies")){
			AlliesApplyRight applyRight = alliesApplyRepository.findOne(apply_id);
			if(applyRight != null && applyRight.getUser().getId().equals(user.getId())){
				user.setMyright("allies");
				user = userRepository.save(user);
				if(user.getMyright().equals("allies")){
					flag = true;
				}
			}
		}else if(types.equals("park")){
			ParkApplyRight coApplyRight = parkApplyRepository.findOne(apply_id);
			if(coApplyRight != null && coApplyRight.getUser().getId().equals(user.getId())){
				user.setMyright("allies");
				user = userRepository.save(user);
				if(user.getMyright().equals("allies")){
					flag = true;
				}
			}
		}
		
	
		return flag;
	}

	

}
