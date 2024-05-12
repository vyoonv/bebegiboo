package com.bebegiboo.project.certification.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bebegiboo.project.certification.model.dto.Certification;
import com.bebegiboo.project.certification.model.dto.CertificationImg;
import com.bebegiboo.project.certification.model.service.CertificationService;
import com.bebegiboo.project.member.model.dto.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("certification")
@Controller
public class CertificationController {
	
	private final CertificationService service; 
	
	

	/** 인증신청 폼 화면 이동 
	 * @return
	 */
	@GetMapping("certification-main")
	public String certificationMain() {
		
		return "/certification/certification-main"; 
		
	}
	
	@GetMapping("certification-complete")
	public String certificationComplete() {
		return "/certification/certification-complete";
	}


	/** 인증 신청 폼 제출 
	 * @param memberAddress
	 * @param inputCertification
	 * @param loginMember
	 * @param model
	 * @param ra
	 * @param images
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("certification-complete")
	public String certificationSubmit(@RequestParam("address") String[] memberAddress,
										Certification inputCertification,
										@SessionAttribute("loginMember") Member loginMember,
										Model model, 
										RedirectAttributes ra, 
										HttpSession session,
										@RequestParam("images") List<MultipartFile> images ) throws IllegalStateException, IOException {
		
		log.debug("images : ", images);
		
		inputCertification.setMemberNo(loginMember.getMemberNo());
		
		int result = service.certificationSubmit(inputCertification, memberAddress, images); 
		log.info("memberAddress" + Arrays.toString(memberAddress));
		
		// 주소 구분 없애기 
				if( !inputCertification.getAddress().equals(",,") ) {
					
					String address = String.join(" ", memberAddress);
					inputCertification.setAddress(address);
				} else {
					
					inputCertification.setAddress(null);
				}
				

		log.info("memberAddress" + Arrays.toString(memberAddress));
		
		model.addAttribute("address", memberAddress); 
		session.setAttribute("certification", inputCertification);
		session.setAttribute("address", memberAddress);
		session.setAttribute("images", images);
		
		String path; 
		String message; 
		
		if(result > 0) {
			message = "신청완료"; 
			path = "/certification/certification-complete";    // 완료 화면으로 변경 
		} else {
			message = "신청 실패.."; 
			path = "/certification/certification-main"; 
		}
		
		ra.addFlashAttribute("message", message); 
		
		return path; 
		
	}
	
	
}
