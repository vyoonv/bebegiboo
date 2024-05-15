package com.bebegiboo.project.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.bebegiboo.project.certification.model.dto.Certification;
import com.bebegiboo.project.donateInfo.dto.DonationRecord;
import com.bebegiboo.project.faqboard.model.dto.FaqBoard;

import com.bebegiboo.project.manager.dto.DetailProduct;
import com.bebegiboo.project.manager.mapper.ManagerMapper;
import com.bebegiboo.project.manager.pagination.ManagerPagination;
import com.bebegiboo.project.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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
	public List<Member> selectAcceptorList(int recordNo) {

		return mapper.selectAcceptorList(recordNo);
	}


	// 피기부자 등록
	@Override
	public int connectDonate(Map<String, Object> connectObj) {

		return mapper.connectDonate(connectObj);
	}


	/**
	 * 봉사인증신청서 목록 조회 
	 */
	@Override
	public Map<String, Object> certificationList(int cp) {
		
		int listCount = mapper.getListCount(); 
		
		ManagerPagination pagination = new ManagerPagination(cp, listCount); 
		
		int limit = pagination.getLimit(); 
		int offset = (cp-1)*limit; 
		
		RowBounds rowBounds= new RowBounds(offset, limit); 
		
		List<Certification> certificationList = mapper.certificationList(rowBounds); 
		
		Map<String, Object> map = new HashMap<>(); 
		
		map.put("pagination", pagination); 
		map.put("certificationList", certificationList); 
		
		return map;
	}


	/**
	 * 봉사인증신청서 수정 
	 */
	@Override
	public int infoUpdate(Certification inputInfo, int memberNo) {
		
		inputInfo.setMemberNo(memberNo);
		
		return mapper.infoUpdate(inputInfo);
	}




}
