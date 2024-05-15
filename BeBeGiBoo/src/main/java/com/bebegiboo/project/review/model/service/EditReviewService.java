package com.bebegiboo.project.review.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.bebegiboo.project.review.model.dto.CertificationBoard;

public interface EditReviewService {

	/** 게시글 작성
	 * @param inputBoard
	 * @param images
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	int boardInsert(CertificationBoard inputBoard, List<MultipartFile> images) throws IllegalStateException, IOException;

	/**게시글 수정
	 * @param inputBoard
	 * @param images
	 * @param queryString
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	int boardUpdate(CertificationBoard inputBoard, List<MultipartFile> images, String deleteOrder) throws IllegalStateException, IOException;

	/** 게시글 삭제
	 * @param map
	 * @return
	 */
	int boardDelete(Map<String, Integer> map);



}
