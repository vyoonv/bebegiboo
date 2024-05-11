package com.bebegiboo.project.manager.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


import com.bebegiboo.project.donateInfo.dto.DonationProduct;
import com.bebegiboo.project.donateInfo.dto.DonationRecord;
import com.bebegiboo.project.manager.dto.DetailProduct;
import com.bebegiboo.project.manager.mapper.ManagerMapper;
import com.bebegiboo.project.member.model.dto.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService{
	
	private final ManagerMapper mapper;


	// 회원 목록 조회
	@Override
	public List<Member> selectMemberList() {

		return mapper.selectMemberList();
	}


	// 회원 정보 수정
	@Override
	public int update(Member member) {

		return mapper.update(member);
	}


	// 기부물품 목록 조회
	@Override
	public List<DonationRecord> selectDonationThingsList(int memberNo) {


		return mapper.selectDonationThingsList(memberNo);
	}





	// 기부물품 상세내역 조회
	@Override
	public DetailProduct selectDonationDetailThingsList(int recordNo) {
		DetailProduct detailProduct = mapper.selectDonationDetailThingsList(recordNo);
		
		detailProduct.setProductName(mapper.selectProductNames(recordNo));
		
		

		return detailProduct;
	}


	// 피기부자 목록 조회
	@Override
	public List<Member> selectAcceptorList() {

		return mapper.selectAcceptorList();
	}


	// 피기부자 등록
	@Override
	public int connectDonate(Map<String, Object> connectObj) {

		return mapper.connectDonate(connectObj);
	}

}
