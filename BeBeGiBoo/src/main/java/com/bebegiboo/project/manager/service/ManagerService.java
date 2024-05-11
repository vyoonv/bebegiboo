package com.bebegiboo.project.manager.service;

import java.util.List;
import java.util.Map;

import com.bebegiboo.project.donateInfo.dto.DonationProduct;
import com.bebegiboo.project.donateInfo.dto.DonationRecord;
import com.bebegiboo.project.manager.dto.DetailProduct;
import com.bebegiboo.project.member.model.dto.Member;

public interface ManagerService {


	/** 회원 목록 조회
	 * @return
	 */
	List<Member> selectMemberList();

	/** 회원 정보 수정
	 * @param member
	 * @return
	 */
	int update(Member member);

	/** 기부물품 목록 조회
	 * @return
	 */
	List<DonationRecord> selectDonationThingsList(int memberNo);


	/** 기부물품 상세내역 조회
	 * @param recordNo
	 * @return
	 */
	DetailProduct selectDonationDetailThingsList(int recordNo);

	/** 피기부자 목록 조회
	 * @return
	 */
	List<Member> selectAcceptorList();

	/** 피기부자 연결
	 * @param connectObj
	 * @return
	 */
	int connectDonate(Map<String, Object> connectObj);

}