package com.knet51.ccweb.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;

@Transactional
public interface BlogPostRepository extends JpaRepository<BlogPost, Long>, JpaSpecificationExecutor<BlogPost>, BlogPostRepositoryCustom {
	Page<BlogPost> findByAuthor(Teacher author, Pageable pageable);
	Page<BlogPost> findByAuthorAndGarbage(Teacher author, boolean garbage, Pageable pageable);
	Page<BlogPost> findByAuthorAndDraft(Teacher teacher, boolean b, Pageable dateDesc);
	Page<BlogPost> findByAuthorAndGarbageAndDraft(Teacher teacher,boolean garbage, boolean draft, Pageable pageable);
	
}
