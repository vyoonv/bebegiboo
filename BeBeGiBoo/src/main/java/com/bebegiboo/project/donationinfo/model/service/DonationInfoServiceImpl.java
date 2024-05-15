package com.bebegiboo.project.donationinfo.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bebegiboo.project.donateInfo.dto.DeliveryInfoList;
import com.bebegiboo.project.donateInfo.dto.DonationProduct;
import com.bebegiboo.project.donateInfo.dto.DonationRecord;
import com.bebegiboo.project.donateInfo.dto.PaymentInfo;
import com.bebegiboo.project.donationinfo.model.mapper.DonationInfoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Transactional
@Service
public class DonationInfoServiceImpl implements DonationInfoService{
	
	private final DonationInfoMapper mapper;

	// 기부 레코드 리스트 가져오기
	@Override
	public List<DonationRecord> getDonationRecord(int memberNo) {
		
		return mapper.getDonationRecord(memberNo);
	}

	// 기부 배송 정보 가져오기 
	@Override
	public List<DeliveryInfoList> getDeliveryInfo(int memberNo) {
		
		return mapper.getDeliveryInfo(memberNo);
	}

	// 기부 물품 정보 가져오기 
	@Override
	public List<DonationProduct> getProductInfo(int memberNo) {
		
		return mapper.getProductInfo(memberNo);		
	}

	// 기부 결제 정보 가져오기 
	@Override
	public List<PaymentInfo> getPaymentInfo(int memberNo) {
		
		return mapper.getPaymentInfo(memberNo);
	}
	
	


}
