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

	/** faq 작성 
	 * @param inputFaq
	 * @return
	 */
	int insertFaq(FaqBoard inputFaq);

	/** faq 조회 
	 * @param qNo
	 * @return
	 */
	FaqBoard selectOne(int qNo);

	/** faq 수정 
	 * @param inputFaq
	 * @return
	 */
	int updateFaq(FaqBoard inputFaq);

	/** faq 삭제 
	 * @param qNo
	 * @return
	 */
	int faqDelete(int qNo);

	List<FaqBoard> getFaqByCategory(int categoryNo);

	

	

}
