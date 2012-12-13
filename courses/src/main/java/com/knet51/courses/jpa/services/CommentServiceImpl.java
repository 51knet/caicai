package com.knet51.courses.jpa.services;


import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.knet51.ccweb.jpa.entities.teacher.Comment;
import com.knet51.ccweb.jpa.repository.CommentRepository;
@Transactional
@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commitRepository;
	@Override
	public Comment createComment(Comment comment) {
		return commitRepository.save(comment);
	}
	@Override
	public Page<Comment> findAllCommit(int pageNumber, int pageSize,
			Long teacherCourse_id) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		
		Page<Comment> onePage = commitRepository.findCommentByCourseid(teacherCourse_id, dateDesc);
		List<Comment> list = onePage.getContent();
		for (int i = 0; i < list.size(); i++) {
			System.out.println("+++++++++++++++++"+list.get(i).getCourseid());
		}
		return onePage;
	}
	@Override
	public Long getMark(Long teacherCourseId) {
		Long mark=commitRepository.getMark(teacherCourseId);
		return mark;
	}
	@Override
	public Long getPerson(Long teacherCourseId) {
		Long personNum=commitRepository.getPerson(teacherCourseId);
		return personNum;
	}
	@Override
	public Long getMark(Long teacherCourseId, Long userId) {
		Long markNum=commitRepository.getMark(teacherCourseId, userId);
		return markNum;
	}
}
