package com.bebegiboo.project.review.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bebegiboo.project.review.model.dto.Comment;
import com.bebegiboo.project.review.model.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

	private final CommentMapper mapper;
	
	//댓글 목록 불러오기
	@Override
	public List<Comment> select(int boardNo) {
		return mapper.select(boardNo);
	}
	
	
	//댓글 삭제
	@Override
	public int delete(int commentNo) {
		
		return mapper.delete(commentNo);
	}

	//댓글 업데이트
	@Override
	public int udpate(Comment comment) {
		
		return mapper.update(comment);
	}

	@Override
	public int insert(Comment comment) {

		return mapper.insert(comment);
	}





}



