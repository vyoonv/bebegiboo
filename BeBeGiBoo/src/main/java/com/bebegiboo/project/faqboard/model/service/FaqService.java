package com.bebegiboo.project.faqboard.model.service;

import java.util.List;
import java.util.Map;

import com.bebegiboo.project.faqboard.model.dto.FaqBoard;

public interface FaqService {

	/** faq게시판 목록 
	 * @param cp
	 * @param qNo 
	 * @return
	 */
	Map<String, Object> FaqBoardList(int cp);

}
