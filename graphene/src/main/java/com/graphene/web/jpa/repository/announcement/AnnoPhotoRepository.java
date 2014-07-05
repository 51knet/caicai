package com.graphene.web.jpa.repository.announcement;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.graphene.web.jpa.entity.announcement.AnnoPhoto;


public interface AnnoPhotoRepository  extends JpaRepository<AnnoPhoto, Long>, JpaSpecificationExecutor<AnnoPhoto>{
	
	List<AnnoPhoto> findAnnoPhotoByUserid(Long userid);
}
