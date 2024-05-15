package com.bebegiboo.project.acceptor.service;

import java.util.List;
import java.util.Map;

import com.bebegiboo.project.donateInfo.dto.DonationRecord;

public interface AcceptorService {

	/** 기부물품 목록 조회
	 * @return
	 */
	List<DonationRecord> selectProduct();

	/** 기부신청목록 삽입
	 * @param recordNo 
	 * @param memberNo 
	 * @return
	 */
	int insertWish(Map<String, Integer> map);

}
