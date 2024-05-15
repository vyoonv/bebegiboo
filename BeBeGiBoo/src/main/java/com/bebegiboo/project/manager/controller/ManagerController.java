package com.bebegiboo.project.manager.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bebegiboo.project.certification.model.dto.Certification;
import com.bebegiboo.project.donateInfo.dto.DonationProduct;
import com.bebegiboo.project.donateInfo.dto.DonationRecord;
import com.bebegiboo.project.manager.dto.DetailProduct;
import com.bebegiboo.project.manager.service.ManagerService;
import com.bebegiboo.project.member.model.dto.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("manager")
@RequiredArgsConstructor
@Slf4j
public class ManagerController {
	
	private final ManagerService service;
	
	
	

	/** 회원관리 페이지 이동
	 * @return
	 */
	@GetMapping("membership")
	public String memberShip() {
		return "/manager/membership";
	}
	
	/** 기부물품관리 페이지 이동
	 * @return
	 */
	@GetMapping("donationThings")
	public String donationThings() {
		return "/manager/donationThings";
	}
	
	
	/** 회원 목록 조회
	 * @return
	 */
	@ResponseBody
	@GetMapping("selectMemberList")
	public List<Member> selectMemberList() {
		List<Member> memberList = service.selectMemberList();
		
		return memberList;
	}
	
	
	/** 회원정보 수정
	 * @return
	 */
	@PutMapping("update")
	@ResponseBody
	public int update(@RequestBody Member member) {
		log.info("멤버" + member);
		
		int result = service.update(member);
		
		return result;
	}
	
	
	/** 기부물품 목록 조회
	 * @return
	 */
	@ResponseBody
	@PostMapping("selectDonationThings")
	public List<DonationRecord> selectDonationThingsList(@RequestBody int memberNo) {
		List<DonationRecord> donationThingsList = service.selectDonationThingsList(memberNo);
		
		return donationThingsList;
	}
	
	
	/** 기부물품 상세내역 조회
	 * @return
	 */
	@ResponseBody
	@PostMapping("selectDonationDetailThings")
	public List<DetailProduct> selectDonationDetailThingsList(@RequestBody int recordNo) {
		DetailProduct donationDetailThings = service.selectDonationDetailThingsList(recordNo);
		List<DetailProduct> donationDetailThingsList = new ArrayList<DetailProduct>();
		
		donationDetailThingsList.add(donationDetailThings);

		return donationDetailThingsList;
	}
	
	
	/** 피기부자 목록 조회
	 * @return
	 */
	@ResponseBody
	@PostMapping("selectAcceptor")
	public List<Member> selectAcceptorList(@RequestBody int recordNo) {
		List<Member> acceptorList = service.selectAcceptorList(recordNo);
		
		log.info("왜"+acceptorList);
		
		return acceptorList;
		
	}
	
	
	/** 피기부자 등록
	 * @return
	 */
	@ResponseBody
	@PutMapping("connectDonate")
	public int connectDonate(@RequestBody Map<String, Object> connectObj) {
		return service.connectDonate(connectObj);
	}
	
	
	/** 인증신청서 목록 조회 
	 * @param model
	 * @return
	 */
	@GetMapping("certificationInfo")
	public String certificationInfoPage( Model model,
									@RequestParam(value="cp", required=false, defaultValue="1") int cp) {
		
		Map<String, Object> map = service.certificationList(cp); 
		 
		
		log.info("출력좀.." + map);
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("certification", map.get("certificationList"));
			
		return "/manager/certificationInfo"; 
	}
	
	/** 인증신청서 수정 
	 * @param inputInfo
	 * @return
	 */
	@ResponseBody
	@PutMapping("certificationUpdate")
	public int certificationInfoUpdate( @RequestBody Certification inputInfo ) {
		
		int memberNo = inputInfo.getMemberNo(); 
		String address = inputInfo.getAddress(); 
		log.info("address : " + address);
		log.info("memberNo : " + memberNo);
		log.info("inputInfo : "+ inputInfo);
		
		int result = service.infoUpdate(inputInfo, memberNo); 
		
		return result; 
	}
	
	
}
