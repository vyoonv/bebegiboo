package com.bebegiboo.project.faqboard.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bebegiboo.project.faqboard.model.dto.FaqBoard;
import com.bebegiboo.project.faqboard.model.dto.Pagination;
import com.bebegiboo.project.faqboard.model.mapper.FaqMapper;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class FaqServiceImpl implements FaqService{
	
	
	private final FaqMapper mapper; 
	
	

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

}
