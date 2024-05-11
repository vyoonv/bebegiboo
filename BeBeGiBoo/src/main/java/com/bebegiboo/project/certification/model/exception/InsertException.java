package com.bebegiboo.project.certification.model.exception;

public class InsertException extends RuntimeException {

	
	/**
	 *  삽입 예외 처리 메서드 
	 */
	public InsertException() {
		super("삽입 중 예외발생"); 
	}
	
	/** 삽입 처리 예외 메세지용 
	 * @param message
	 */
	public InsertException(String message) {
		super(message); 
	}
}
