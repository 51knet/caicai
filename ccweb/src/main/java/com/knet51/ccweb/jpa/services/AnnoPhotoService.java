package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.AnnoPhoto;

public interface AnnoPhotoService {
	List<AnnoPhoto> findAnnoPhotoByUserid(Long user_id);
	AnnoPhoto createAnnoPhoto(AnnoPhoto annoPhoto);
	AnnoPhoto updateAnnoPhoto(AnnoPhoto annoPhoto);
	void deleteAnnoPhotoById(Long id);
	
	AnnoPhoto findOneByAnnoPhotoId(Long anno_id);
}
