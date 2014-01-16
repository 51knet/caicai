package com.knet51.ccweb.jpa.repository.blog;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;

@Transactional
public interface BlogPostRepository extends JpaRepository<BlogPost, Long>, JpaSpecificationExecutor<BlogPost>, BlogPostRepositoryCustom {
	Page<BlogPost> findByAuthorAndForbiddenIsNull(Teacher author, Pageable pageable);
	Page<BlogPost> findByAuthorAndGarbageAndForbiddenIsNull(Teacher author, boolean garbage, Pageable pageable);
	Page<BlogPost> findByAuthorAndDraftAndForbiddenIsNull(Teacher teacher, boolean b, Pageable dateDesc);
	Page<BlogPost> findByAuthorAndGarbageAndDraftAndForbiddenIsNull(Teacher teacher,boolean garbage, boolean draft, Pageable pageable);
	
	// for the super admin
	List<BlogPost> findByAuthorAndGarbageAndDraft(Teacher teacher,boolean garbage, boolean draft);
	List<BlogPost> findAllByGarbageAndDraft(boolean garbage, boolean draft);
	Page<BlogPost> findAllByGarbageAndDraft(boolean garbage, boolean draft,Pageable pageable);
}
