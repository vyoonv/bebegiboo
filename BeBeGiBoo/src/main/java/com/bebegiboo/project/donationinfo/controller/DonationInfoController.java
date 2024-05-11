package com.bebegiboo.project.donationinfo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bebegiboo.project.donateInfo.dto.DeliveryInfoList;
import com.bebegiboo.project.donateInfo.dto.DonationInfo;
import com.bebegiboo.project.donateInfo.dto.DonationProduct;
import com.bebegiboo.project.donateInfo.dto.DonationRecord;
import com.bebegiboo.project.donateInfo.dto.PaymentInfo;
import com.bebegiboo.project.donateInfo.dto.ProductType;
import com.bebegiboo.project.donationinfo.model.service.DonationInfoService;
import com.bebegiboo.project.member.model.dto.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
@RequestMapping("member")
@Controller
public class DonationInfoController {
	
	private final DonationInfoService service; 
	
	
	
	
	@GetMapping("mypage/donationInfo")
	public String donationInfoPage( 
									@SessionAttribute("loginMember") Member loginMember,
									Model model) {
		
		int memberNo = loginMember.getMemberNo(); 
		
		List<DonationRecord> recordList = service.getDonationRecord(memberNo); 
		List<DeliveryInfoList> deliveryList = service.getDeliveryInfo(memberNo); 
		List<DonationProduct> productList = service.getProductInfo(memberNo); 
		List<PaymentInfo> paymentList = service.getPaymentInfo(memberNo); 
		
		DonationInfo donationInfo = DonationInfo.builder()
		        .recordList(recordList)
		        .deliveryList(deliveryList)
		        .productList(productList)
		        .paymentList(paymentList)
		        .build();

		model.addAttribute("record", recordList); 
		model.addAttribute("product", productList);
		model.addAttribute("delivery", deliveryList);
		model.addAttribute("payment", paymentList);
		model.addAttribute("donationInfo", donationInfo); 
		
		
		
		
		log.info("record : " + recordList); 
		log.info("delivery : " + deliveryList ); 
		log.info("product : " + productList );
		log.info("payment : " + paymentList ); 
		log.info("donationInfo : " + donationInfo); 

	
		return "member/mypage/donationInfo";
	}
	
	
	

}
