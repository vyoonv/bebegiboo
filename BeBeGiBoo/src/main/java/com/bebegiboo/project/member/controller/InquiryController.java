package com.bebegiboo.project.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bebegiboo.project.member.model.dto.Member;
import com.bebegiboo.project.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("inquiry")
@Controller
@RequiredArgsConstructor
public class InquiryController {

	private final MemberService service;

	
	
	
	/** 아이디 찾기
	 * @param email
	 * @return
	 */
	
	@PostMapping("idInquiry")
	public String checkEmail(Member inputMember,
			RedirectAttributes ra) {
		String path = "/member/inquiry/idInquiry";
		String message = null;
		Map<String, String> map = new HashMap<>();
		
		if(inputMember.getEmail().isBlank()&&inputMember.getPhone().isBlank()) { //
			
			message="정보를 입력해주세요";
		
		//전화번호에 체크한 경우
		}else if(!inputMember.getPhone().isBlank()){ 
			int result = service.checkName(inputMember.getMemberName());
			if(result == 0) {
				message = "입력한 이름에 해당하는 회원이 존재하지 않습니다";
			}else {
				result = service.checkPhone(inputMember.getPhone());
				if(result == 0) {
					message = "입력한 휴대폰 번호가 정보와 일치하지 않습니다";
				}else {
					message = "입력한 휴대폰 번호로 아이디 정보를 전송했습니다";
					path = "/";
				}
			}
		//이메일에 체크한 경우
		}else if(!inputMember.getEmail().isBlank()) {
			
			map.put("memberName", inputMember.getMemberName());
			map.put("email", inputMember.getEmail());
			
			int result = service.checkEmailWithName(map);
			if(result ==0) {
				message="입력한 정보에 해당하는 회원이 존재하지 않습니다";
			}else {
				result = service.sendEmail("id", inputMember.getEmail());
				if(result == 0) {
					message = "이메일 전송 실패\n다시 시도해주세요";
				}else {
					message = "입력한 이메일로 아이디 정보를 전송했습니다";
					path= "/";
				}
			}
		}
		ra.addFlashAttribute("message", message);
		return "redirect:" + path;
		
	}
	
	/** 비밀번호 찾기
	 * @param inputMember
	 * @param ra
	 * @return
	 */
	@PostMapping("pwInquiry")
	public String pwInquiry(Member inputMember,
			RedirectAttributes ra) {
		String path = "/member/inquiry/pwInquiry";
		String message = null;
		Map<String, String> map = new HashMap<>();
		
		if(inputMember.getPhone().isBlank() && inputMember.getEmail().isBlank()){
						
			message = "정보를 입력해주세요";
			
		}else if(!inputMember.getEmail().isBlank()) {//이메일 체크한 경우
			map.put("memberId", inputMember.getMemberId());
			map.put("email", inputMember.getEmail());
			
			int result = service.checkEmailWithId(map);
			
			if(result == 0) {
				message = "입력한 정보에 해당하는 회원이 존재하지 않습니다";
			}else {
				result = service.sendEmail("pw", inputMember.getEmail());
				
				if(result == 0) {
					message="이메일 전송 실패\n다시 시도해주세요";
				}else {
					message="입력한 이메일로 아이디 정보를 전송했습니다";
					path = "/";
				}
			}
			
			
		}else { //휴대폰 번호에 체크한 경우
			int result = service.checkId(inputMember.getMemberId());
			
			if(result == 0) {
				message="아이디가 존재하지 않습니다";
			}else {
				 result = service.checkPhone(inputMember.getPhone());
				 
				 if(result >0) {
					 message="입력한 휴대폰 번호로 아이디 정보를 전송했습니다";
					 path = "/";
				 }else {
					 message="입력한 휴대폰 번호가 정보와 일치하지 않습니다";
				 }
			}
			
			
		}
		log.debug("message : " + message);
		
		ra.addFlashAttribute("message", message);
		return "redirect:" + path;
	}

	

}
