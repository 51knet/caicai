package com.knet51.ccweb.jpa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.Comment;
import com.knet51.ccweb.jpa.repository.CommentRepository;
@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Override
	public Page<Comment> findAllByTrendId(Long trend_id, int pageNum,
			int pageSize) {
		Pageable pageable = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		return commentRepository.findAllByTrendId(trend_id, pageable);
	}

	@Override
	public List<Comment> findAllByTrendId(Long trend_id) {
		return commentRepository.findAllByTrendId(trend_id);
	}

	@Override
	public Comment createComment(Comment comment) {
		return commentRepository.saveAndFlush(comment);
	}

	@Override
	public void deleteComment(Long comment_id) {
		commentRepository.delete(comment_id);
	}

	@Override
	public Comment findOneByCommentId(Long comment_id) {
		return commentRepository.findOne(comment_id);
	}

	@Override
	public List<Comment> findTopSixByTrendId(Long trend_id) {
		Pageable pageable = new PageRequest(0, 6, Direction.DESC, "id");
		Page<Comment> page = commentRepository.findAllByTrendId(trend_id, pageable);
		List<Comment> list = new ArrayList<Comment>();
		for(int i=0;i<page.getSize();i++){
			list.add(page.getContent().get(i));
		}
		return list;
	}

}
