package com.bebegiboo.project.review.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bebegiboo.project.review.model.dto.Comment;
import com.bebegiboo.project.review.model.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("comment")
@RequiredArgsConstructor
@RestController
@Slf4j
public class CommentController {

	private final CommentService service;
	
	@GetMapping("")
	public List<Comment> select(@RequestParam("boardNo") int boardNo) {
		return service.select(boardNo);
	}
	
	@PostMapping("")
	public int insert(@RequestBody Comment comment) {
		log.debug("comment : " + comment);
		return service.insert(comment);
	}
	
	@DeleteMapping("")
	public int delete(@RequestBody int commentNo) {
	
		return service.delete(commentNo);
	}
	
	@PutMapping("")
	public int update(@RequestBody Comment comment) {
		return service.udpate(comment);
	}
}
