package com.bebegiboo.project.review.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bebegiboo.project.review.model.dto.BoardImg;
import com.bebegiboo.project.review.model.dto.CertificationBoard;

@Mapper
public interface EditReviewMapper {

	/** 게시글 작성
	 * @param inputBoard
	 * @return result
	 */
	int boardInsert(CertificationBoard inputBoard);

	/** 게시글 이미지 모두 삽입
	 * @param uploadList
	 * @return result
	 */
	int insertUploadList(List<BoardImg> uploadList);

	/** 게시글 부분 수정(제목, 내용)
	 * @param inputBoard
	 * @return
	 */
	int boardUpdate(CertificationBoard inputBoard);

	/** 게시글 이미지 삭제
	 * @param map
	 * @return
	 */
	int deleteImage(Map<String, Object> map);

	/** 게시글 이미지 수정
	 * @param img
	 * @return
	 */
	int updateImage(BoardImg img);

	/** 게시글 이미지 삽입(행)
	 * @param img
	 * @return
	 */
	int insertImage(BoardImg img);

	/** 게시글 삭제
	 * @param map
	 * @return
	 */
	int boardDelete(Map<String, Integer> map);




}
