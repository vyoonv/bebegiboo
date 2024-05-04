package com.bebegiboo.project.faqboard.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bebegiboo.project.faqboard.model.dto.FaqBoard;
import com.bebegiboo.project.faqboard.model.service.FaqService;
import com.bebegiboo.project.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("faqBoard")
@Controller
public class FaqController {
	
	private final FaqService service; 
	
	
	/** faq 게시판 목록 메인 
	 * @param model
	 * @param cp
	 * @return
	 */
	@GetMapping("/faqBoard")
	public String faqMain(Model model, 
			@RequestParam(value="cp", required=false, defaultValue="1") int cp
			) {
		
		
		Map<String, Object> map = service.FaqBoardList(cp); 
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("faqList", map.get("faqList")); 
		
		
		return "faqBoard/faqBoard"; 
		
	}
	
	/** faq 삽입 화면 
	 * @return
	 */
	@GetMapping("/insertFaq")
	public String insertFaqPage() {
		
		return "/faqBoard/insertFaq"; 
		
	}
	
	/** faq 작성 
	 * @param inputFaq
	 * @param loginMember
	 * @param ra
	 * @return
	 */
	@PostMapping("/insertFaq")
	public String insertFaq(@ModelAttribute FaqBoard inputFaq,
							@SessionAttribute("loginMember") Member loginMember, 
							RedirectAttributes ra
							) {
		
		inputFaq.setCategoryNo(inputFaq.getCategoryNo()); 
		inputFaq.setQuestion(inputFaq.getQuestion()); 
		inputFaq.setAnswer(inputFaq.getAnswer()); 
		inputFaq.setMemberNo(loginMember.getMemberNo()); 
		
		int qNo = service.insertFaq(inputFaq); 
		
		String path = null; 
		String message = null; 
		
		if(qNo > 0) {
			path = "/faqBoard/faqBoard"; 
			message = "업로드 완료!"; 
		} else {
			path = "insertFaq"; 
			message = "작성 실패!"; 
		}
		
		ra.addFlashAttribute("message", message); 
		
		
		return "redirect:" + path; 
	}

	
	

}
