package com.knet51.ccweb.jpa.services.announcement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.AnnoPhoto;
import com.knet51.ccweb.jpa.repository.AnnoPhotoRepository;
@Transactional
@Service("annoPhotoService")
public class AnnoPhotoServiceImpl implements AnnoPhotoService {
	@Autowired
	private AnnoPhotoRepository annoPhotoRepository;

	@Override
	public List<AnnoPhoto> findAnnoPhotoByUserid(Long user_id) {
		return annoPhotoRepository.findAnnoPhotoByUserid(user_id);
	}

	@Override
	public AnnoPhoto createAnnoPhoto(AnnoPhoto annoPhoto) {
		return annoPhotoRepository.saveAndFlush(annoPhoto);
	}

	@Override
	public AnnoPhoto updateAnnoPhoto(AnnoPhoto annoPhoto) {
		return annoPhotoRepository.saveAndFlush(annoPhoto);
	}

	@Override
	public void deleteAnnoPhotoById(Long id) {
		annoPhotoRepository.delete(id);
	}

	@Override
	public AnnoPhoto findOneByAnnoPhotoId(Long anno_id) {
		return annoPhotoRepository.findOne(anno_id);
	}

}
