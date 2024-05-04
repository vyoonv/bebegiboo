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

}
