package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.Comment;

public interface CommentService {
	
	Page<Comment> findAllByTrendId(Long trend_id, int pageNum, int pageSize );
	List<Comment> findAllByTrendId(Long trend_id);
	
	List<Comment> findTopSixByTrendId(Long trend_id);
	
	Comment createComment(Comment comment);
	void deleteComment(Long comment_id);
	Comment findOneByCommentId(Long  comment_id);
}
