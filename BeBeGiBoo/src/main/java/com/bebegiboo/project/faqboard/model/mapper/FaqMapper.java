package com.bebegiboo.project.faqboard.model.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.bebegiboo.project.faqboard.model.dto.FaqBoard;

@Mapper
public interface FaqMapper {

	/** 글 수 조회 
	 * @param qNo
	 * @return
	 */
	int getListCount();

	/** faq 목록 조회 
	 * @param qNo
	 * @param rowBounds
	 * @return
	 */
	List<FaqBoard> selectFaqBoardList(RowBounds rowBounds);

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
	 * @param map
	 * @return
	 */
	int faqDelete(int qNo);

	List<FaqBoard> getFaqByCategory(int categoryNo);

}
