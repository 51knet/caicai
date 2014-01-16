package com.knet51.ccweb.jpa.repository.blog;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.blog.BlogComment;

@Transactional
public interface BlogCommentRepository extends JpaRepository<BlogComment, Long>, JpaSpecificationExecutor<BlogComment> {
}
