package com.knet51.ccweb.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.knet51.ccweb.jpa.entities.blog.BlogPost;

public interface BlogPostRepository extends PagingAndSortingRepository<BlogPost, Long>, BlogPostRepositoryCustom {

}
