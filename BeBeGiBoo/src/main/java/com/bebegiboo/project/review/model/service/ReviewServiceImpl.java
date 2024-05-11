package com.bebegiboo.project.review.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bebegiboo.project.certification.model.dto.Certification;
import com.bebegiboo.project.faqboard.pagination.Pagination;
import com.bebegiboo.project.review.model.dto.CertificationBoard;
import com.bebegiboo.project.review.model.dto.ReviewPagination;
import com.bebegiboo.project.review.model.mapper.ReviewMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

	private final ReviewMapper mapper;
	//검색 X 후기 글 목록 불러오기
	@Override
	public Map<String, Object> selectReviewList(int cp) {
		
		
		log.debug("cp : " + cp);
		//1. 전체 게시글 수 조회
		int listCount = mapper.getListCount();
		
		//2. pagination 객체 생성하기
		ReviewPagination pagination = new ReviewPagination(cp, listCount);
		//3. 페이지 목록 조회
		int limit = pagination.getLimit(); //제한된 크기
		int offset = (cp-1) * limit; //건너뛰기 :  데이터를 가져오는 시작점에서 얼마나 떨어진 데이터인지를 의미
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<CertificationBoard> reviewList = mapper.SelectReivewList(rowBounds);
		//4. 목록조회 결과 + pagination객체 map으로 묶어서 반환
		
		Map<String, Object> map = new HashMap<>();
		map.put("pagination", pagination);
		map.put("reviewList", reviewList);
		
		return map;
	}
	


	//게시글 검색 -> 후기 글 목록 불러오기
	@Override
	public Map<String, Object> searchList(Map<String, Object> paramMap, int cp) {
		
		//1.검색조건 맞고, 삭제 안된 게시글 수 조회
		int listCount = mapper.getSearchCount(paramMap);
		
		//2. + cp 사용해서 Pagination 생성하기
		ReviewPagination pagination = new ReviewPagination(cp, listCount);
		
		//3. 페이지 목록 조회하기
		int limit = pagination.getLimit();
		int offset =(cp-1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<CertificationBoard> reviewList = mapper.selectSearchList(paramMap, rowBounds);
		
		log.debug("reviewList : " + reviewList);
		
		//4. 목록 조회 결과 + pagination 객체를 map으로 묶어서 결과로 반환
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("reviewList", reviewList);
		
		
		return map;
		
	}



	//후기글 상세 조회
	@Override
	public CertificationBoard selectOne(Map<String, Integer> map) {
		
		return mapper.selectOne(map);
	}



	//조회수 증가
	@Override
	public int updateReadCount(int boardNo) {
		
		return mapper.upadateReadCount(boardNo);
	}

}
