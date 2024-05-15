package com.bebegiboo.project.review.model.service;

import java.util.List;
import java.util.Map;

import com.bebegiboo.project.review.model.dto.CertificationBoard;

public interface ReviewService {

	/** 검색X 후기 게시판 글 목록 불러오기
	 * @param cp
	 * @return
	 */
	Map<String, Object> selectReviewList(int cp);

	/** 검색 O 후기 게시판 글 목록 불러오기
	 * @param paramMap
	 * @param cp
	 * @return
	 */
	Map<String, Object> searchList(Map<String, Object> paramMap, int cp);

	/** 후기 상세 조회
	 * @param map
	 * @return
	 */
	CertificationBoard selectOne(Map<String, Integer> map);

	/** 조회수 증가
	 * @param boardNo
	 * @return
	 */
	int updateReadCount(int boardNo);

	/** 메인페이지용 보드조회
	 * @return
	 */
	List<CertificationBoard> selectBoardList();

}
