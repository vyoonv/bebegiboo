package com.bebegiboo.project.review.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CertificationBoard {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriteDate;
	private String boardUpdateDate;
	private int readCount;
	private String boardDelFl;
	private int memberNo;
	
	//목록 조회 시 상관 서브 쿼리 결과
	private int commentCount;

	//게시글 목록 썸네일 이미지
	private String thumbnail;
	
	//특정 게시글 이미지 목록
	private List<BoardImg> imageList;
	
	//특정 게시글 댓글 목록
	private List<Comment> commentList;
	
	//작성자 이름
	private String memberId;
	
	
}
