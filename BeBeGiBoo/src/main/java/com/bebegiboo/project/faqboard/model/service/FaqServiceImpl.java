package com.bebegiboo.project.faqboard.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bebegiboo.project.faqboard.model.dto.FaqBoard;
import com.bebegiboo.project.faqboard.model.mapper.FaqMapper;
import com.bebegiboo.project.faqboard.pagination.Pagination;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class FaqServiceImpl implements FaqService{
	
	
	private final FaqMapper mapper; 
	
	

	/**
	 * faq 목록
	 */
	@Override
	public Map<String, Object> FaqBoardList(int cp) {
		
		int listCount = mapper.getListCount(); 
		
		Pagination pagination = new Pagination(cp, listCount); 
		
		int limit = pagination.getLimit(); 
		int offset = (cp-1)*limit; 
		
		RowBounds rowBounds= new RowBounds(offset, limit); 
		
		List<FaqBoard> faqList = mapper.selectFaqBoardList(rowBounds); 
		
		Map<String, Object> map = new HashMap<>(); 
		
		map.put("pagination", pagination); 
		map.put("faqList", faqList); 
		
		return map;
	}



	/**
	 * faq 작성 
	 */
	@Override
	public int insertFaq(FaqBoard inputFaq) {
		
		int result = mapper.insertFaq(inputFaq); 
		
		return result;
	}



	/**
	 * faq 조회 
	 */
	@Override
	public FaqBoard selectOne(int qNo) {
		
		return mapper.selectOne(qNo);
	}



	/**
	 * faq 수정 
	 */
	@Override
	public int updateFaq(FaqBoard inputFaq) {
		
		int result = mapper.updateFaq(inputFaq); 
		
		return result; 
		
	}



	/**
	 * faq 삭제 
	 */
	@Override
	public int faqDelete(int qNo) {
		
		return mapper.faqDelete(qNo);
	}



	/**
	 * 카테고리 목록 
	 */
	@Override
	public List<FaqBoard> getFaqByCategory(int categoryNo) {
		
		return mapper.getFaqByCategory(categoryNo);
	}






	

}
