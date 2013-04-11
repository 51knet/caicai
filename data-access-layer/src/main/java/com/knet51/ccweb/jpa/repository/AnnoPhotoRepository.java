package com.knet51.ccweb.jpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.AnnoPhoto;

@Transactional
public interface AnnoPhotoRepository  extends JpaRepository<AnnoPhoto, Long>, JpaSpecificationExecutor<AnnoPhoto>{
	
	List<AnnoPhoto> findAnnoPhotoByUserid(Long userid);
}
