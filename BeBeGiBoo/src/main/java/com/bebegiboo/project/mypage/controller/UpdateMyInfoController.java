package com.bebegiboo.project.mypage.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bebegiboo.project.member.model.dto.Member;
import com.bebegiboo.project.mypage.model.service.UpdateMyInfoService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("member/mypage")
@Controller
public class UpdateMyInfoController {
	
	private final UpdateMyInfoService service; 
	
	/** 비밀번호 체크 창 이동 
	 * @return
	 */
	@GetMapping("checkPw")
	private String checkPwPage() {
		
		return "/member/mypage/checkPw"; 
		
	}
	

	/** 비밀번호 체크 
	 * @param loginMember
	 * @param model
	 * @param ra
	 * @return
	 */
	@PostMapping("checkPw")
	private String checkPw( @SessionAttribute("loginMember") Member loginMember,
							@RequestParam("memberPw") String memberPw,
							Model model,
							RedirectAttributes ra) {
		
		int memberNo = loginMember.getMemberNo(); 
		
		loginMember.setMemberNo(memberNo);
		loginMember.setMemberPw(memberPw);
		
		int result = service.checkPw(loginMember); 
		
		String path = null; 
		String message = null; 
		
		String address = loginMember.getAddress();
		
		log.info("address : " + address);
		
		if(address != null) {
			
			String[] arr = address.split("\\^\\^\\^"); 
			
			model.addAttribute("postcode", arr[0]); 
			model.addAttribute("mainAddress", arr[1]); 
			model.addAttribute("detailAddress", arr[2]); 
		}
				
		if(result > 0) {
			
			path = "/member/mypage/updateMyInfo";
			
		}
		else {
			path ="redirect:/member/mypage/checkPw"; 
			message = "비밀번호가 일치하지 않습니다..";
		}
		
		ra.addFlashAttribute("message", message);
		
		return path;
	}
	
	/** 회원 정보 수정 
	 * @param loginMember
	 * @param inputMember
	 * @param address
	 * @param paramMap
	 * @param model
	 * @param ra
	 * @return
	 */
	@PostMapping("updateMyInfo")
	private String updateInfo(@SessionAttribute("loginMember") Member loginMember,
								  Member inputMember, 
								  @RequestParam("address") String[] address,
								  @RequestParam Map<String, Object> paramMap,
								  Model model,
								  RedirectAttributes ra ) {
		
		log.info("address : " + Arrays.toString(address));
		
		int memberNo = loginMember.getMemberNo(); 
		inputMember.setMemberNo(memberNo); 
		
		int result = service.updateInfo(inputMember, address, paramMap); 
		
		log.info("email : " + inputMember.getEmail());
		
		String path = null; 
		String message = null; 
		
		
		if(result > 0) {
			
			loginMember.setEmail(inputMember.getEmail());
			loginMember.setMemberPw(inputMember.getMemberPw());
			loginMember.setPhone(inputMember.getPhone()); 
			loginMember.setAddress(inputMember.getAddress());
			
			String inputAddress = loginMember.getAddress(); 
			log.info("inputAddress : " + inputAddress);
			
			if(inputAddress != null) {
				
				String[] arr = inputAddress.split(","); 
				
				model.addAttribute("postcode", arr[0].trim()); 
				model.addAttribute("mainAddress", arr[1].trim()); 
				model.addAttribute("detailAddress", arr[2].trim()); 
			}
			
			path = ""; 
			message = "수정 완료!"; 
		} else {
			path = ""; 
			message = "수정 실패"; 
		}
		
		ra.addFlashAttribute("message", message);
		
		return "/member/mypage/updateMyInfo"; 
	}
	
	
	/** 회원 탈퇴 
	 * @param memberPw
	 * @param loginMember
	 * @param session
	 * @param ra
	 * @return
	 */
	@PostMapping("resign")
	public String resign( @RequestParam("memberPw") String memberPw,
						  @SessionAttribute("loginMember") Member loginMember,
						  HttpSession session,
						  RedirectAttributes ra) {
		
		int memberNo = loginMember.getMemberNo(); 
		
		int result = service.resign(memberPw, memberNo); 
		
		String message = null; 
		
		if(result > 0) {
			message = "탈퇴되었습니다"; 		
			session.invalidate();
		}
		else {
			message = "비밀번호가 일치하지 않습니다."; 

		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:/";
	}
	



}
