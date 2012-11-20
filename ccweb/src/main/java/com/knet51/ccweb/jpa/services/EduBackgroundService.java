package com.knet51.ccweb.jpa.services;

import com.knet51.ccweb.jpa.entities.EduBackground;

public interface EduBackgroundService {
	EduBackground createEduBackground(EduBackground eduBackground);
	EduBackground findEduInfoByteacherId(Long teacher_id);
	EduBackground updateEduBackground(EduBackground eduBackground);
}
