package com.bebegiboo.project.review.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardImg {

	private int imgNo;
	private String imgPath;
	private String imgOriginalName;
	private String imgRename;
	private int boardNo;
	
	//게시글 이미지 삽입, 수정
	private MultipartFile uploadFile;
	
}
