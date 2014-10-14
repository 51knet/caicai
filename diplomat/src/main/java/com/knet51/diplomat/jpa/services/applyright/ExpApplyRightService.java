package com.knet51.diplomat.jpa.services.applyright;

import com.knet51.ccweb.jpa.entities.applyright.ExpApplyRight;

public interface ExpApplyRightService {
	ExpApplyRight findOne(Long id);
	void delete(Long id);
	ExpApplyRight update(ExpApplyRight expRight);
	ExpApplyRight create(ExpApplyRight expRight);
}
