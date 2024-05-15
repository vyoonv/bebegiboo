package com.bebegiboo.project.review.model.exception;

public class ImageUpdateException extends RuntimeException {

	public ImageUpdateException() {
		super("게시글 수정/삽입 중 오류 발생");
	}
	
	public ImageUpdateException(String message) {
		 super(message);
	 }
}
