package com.bebegiboo.project.review.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bebegiboo.project.review.model.dto.Comment;

@Mapper
public interface CommentMapper {

	/**댓글 목록 불러오기
	 * @param boardNo
	 * @return
	 */
	List<Comment> select(int boardNo);

	
	/** 답글 등록하기
	 * @param comment
	 * @return
	 */
	int insert(Comment comment);

	/**댓글 삭제
	 * @param commentNo
	 * @return
	 */
	int delete(int commentNo);

	/**댓글 업데이트
	 * @param comment
	 * @return
	 */
	int update(Comment comment);


}
