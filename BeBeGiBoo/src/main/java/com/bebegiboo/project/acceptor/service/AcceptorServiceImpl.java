package com.bebegiboo.project.acceptor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bebegiboo.project.acceptor.mapper.AcceptorMapper;
import com.bebegiboo.project.donateInfo.dto.DonationRecord;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AcceptorServiceImpl implements AcceptorService{

	private final AcceptorMapper mapper;

	// 기부물품 목록 조회
	@Override
	public List<DonationRecord> selectProduct() {
		List<Integer> recordNo = mapper.selectRecordNo();
		
		List<DonationRecord> productList = new ArrayList<>();
		for(Integer number : recordNo) {
			DonationRecord product = mapper.selectProduct(number);
			List<String> name = mapper.productName(product);
			product.setProductName(name);
			
			productList.add(product);
		}
		

		return productList;
	}

	// 기부 신청목록 삽입
	@Override
	public int insertWish(Map<String, Integer> map) {

		return mapper.insertWish(map);
	}
	
	
}
