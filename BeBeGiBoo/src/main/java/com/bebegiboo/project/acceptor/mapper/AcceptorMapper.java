package com.bebegiboo.project.acceptor.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bebegiboo.project.donateInfo.dto.DonationRecord;

@Mapper
public interface AcceptorMapper {

	/** 기부물품 목록 조회
	 * @param number 
	 * @return
	 */
	DonationRecord selectProduct(Integer number);

	/** 기부물품 이름 조회
	 * @param product 
	 * @return
	 */
	List<String> productName(DonationRecord product);

	List<Integer> selectRecordNo();

	/** 위시리스트 삽입
	 * @param map
	 * @return
	 */
	int insertWish(Map<String, Integer> map);

}
