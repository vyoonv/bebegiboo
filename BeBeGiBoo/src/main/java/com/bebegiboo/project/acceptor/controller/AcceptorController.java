package com.bebegiboo.project.acceptor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.bebegiboo.project.acceptor.service.AcceptorService;
import com.bebegiboo.project.donateInfo.dto.DonationRecord;
import com.bebegiboo.project.member.model.dto.Member;

import jakarta.mail.Session;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("acceptor")
@RequiredArgsConstructor
public class AcceptorController {
	
	private final AcceptorService service;

	/** 기부물품 신청 메인페이지 이동
	 * @return
	 */
	@GetMapping("main")
	public String acceptorMain() {
		return "/acceptor/acceptor-main";
	}
	
	/** 기부물품 목록 조회
	 * @return
	 */
	@ResponseBody
	@GetMapping("selectproductList")
	public List<DonationRecord> selectproductList() {
		List<DonationRecord> productList = service.selectProduct();

		
		return productList;
	}
	
	/** 위시리스트 삽입
	 * @return
	 */
	@ResponseBody
	@PutMapping("insertWish")
	public int insertWish(@SessionAttribute("loginMember") Member loginMember,
							@RequestBody int recordNo) {
		
		int memberNo = loginMember.getMemberNo();
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("memberNo", memberNo);
		map.put("recordNo", recordNo);
		
		int result = service.insertWish(map);

		
		return result;
	}
}
