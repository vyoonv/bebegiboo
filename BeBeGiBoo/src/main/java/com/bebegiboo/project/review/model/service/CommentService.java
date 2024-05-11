package com.bebegiboo.project.review.model.service;

import java.util.List;
import java.util.Map;

import com.bebegiboo.project.review.model.dto.Comment;

public interface CommentService {

	/** 댓글 달기
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
	int udpate(Comment comment);

	/**댓글 목록 불러오기
	 * @return
	 */
	List<Comment> select(int boardNo);


}
