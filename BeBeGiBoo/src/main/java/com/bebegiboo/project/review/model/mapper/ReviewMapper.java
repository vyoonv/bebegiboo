package com.bebegiboo.project.review.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.bebegiboo.project.certification.model.dto.Certification;
import com.bebegiboo.project.review.model.dto.CertificationBoard;
@Mapper
public interface ReviewMapper {

	/** 리뷰 개수 세기
	 * @return
	 */
	int getListCount();

	/** 리뷰 목록 반환하기
	 * @param rowBounds
	 * @return
	 */
	List<CertificationBoard> SelectReivewList(RowBounds rowBounds);

	/** 검색된 게시물 개수 
	 * @param paramMap
	 * @return
	 */
	int getSearchCount(Map<String, Object> paramMap);

	/** 검색된 게시물 목록 반환하기
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<CertificationBoard> selectSearchList(Map<String, Object> paramMap, RowBounds rowBounds);

	/** 게시물 상세 조회
	 * @param map
	 * @return
	 */
	CertificationBoard selectOne(Map<String, Integer> map);

	/** 조회수 증가
	 * @param boardNo
	 * @return
	 */
	int upadateReadCount(int boardNo);

}
