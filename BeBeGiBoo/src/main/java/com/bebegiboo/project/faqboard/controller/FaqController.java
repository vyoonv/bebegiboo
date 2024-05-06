package com.bebegiboo.project.faqboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bebegiboo.project.faqboard.model.dto.FaqBoard;
import com.bebegiboo.project.faqboard.model.service.FaqService;
import com.bebegiboo.project.member.model.dto.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
					@RequestParam(value="cp", required=false, defaultValue="1") int cp  ) {
		
		
		Map<String, Object> map = service.FaqBoardList(cp); 
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("faqList", map.get("faqList")); 
		
		
		return "faqBoard/faqBoard"; 
		
	}
	
	@ResponseBody
	@GetMapping("/getFaqByCategory")
	public List<FaqBoard> getFaqByCategory(@RequestParam("categoryNo") int categoryNo) {
	   
		log.debug("categoryNo : " + categoryNo);  
		
	    List<FaqBoard> faqList = service.getFaqByCategory(categoryNo);
	    
	    log.debug("faqList :" + faqList);
	    
	    return faqList;
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
		
		int result = service.insertFaq(inputFaq); 
		
		String path = null; 
		String message = null; 
		
		if(result > 0) {
			path = "/faqBoard/faqBoard"; 
			message = "업로드 완료!"; 
		} else {
			path = "insertFaq"; 
			message = "작성 실패!"; 
		}
		
		ra.addFlashAttribute("message", message); 
		
		
		return "redirect:" + path; 
	}


	
	/** 수정 페이지로 이동 
	 * @param faqBoard
	 * @param loginMember
	 * @param model
	 * @param ra
	 * @return
	 */
	@GetMapping("/editFaq")
	public String editFaqPage( FaqBoard faqBoard,
						@SessionAttribute("loginMember") Member loginMember,
						Model model,
						RedirectAttributes ra ) {	
		
		faqBoard = service.selectOne(faqBoard.getQNo()); 
		
		String message = null; 
		String path = null; 
		
		if(faqBoard == null) {
			message = "해당 faq가 존재하지 않음"; 
			path = "redirect:/"; 
			
			ra.addFlashAttribute("message", message);
		} else {
			path = "faqBoard/editFaq"; 
			model.addAttribute("faqBoard", faqBoard); 
		}		

		return path; 
	}
	
	
	/** faq 수정 
	 * @param inputFaq
	 * @param loginMember
	 * @param ra
	 * @return
	 */
	@PostMapping("/editFaq")
	public String updateFaq(
							FaqBoard inputFaq,
							@SessionAttribute("loginMember") Member loginMember,
							RedirectAttributes ra	) {
		
		inputFaq.setQNo(inputFaq.getQNo()); 
		inputFaq.setMemberNo(loginMember.getMemberNo()); 
		inputFaq.setCategoryNo(inputFaq.getCategoryNo()); 
		inputFaq.setQuestion(inputFaq.getQuestion()); 
		inputFaq.setAnswer(inputFaq.getAnswer()); 
		
		int result = service.updateFaq(inputFaq); 
		
		String message = null; 
		String path = null; 
		
		if(result > 0) {
			message = "faq 수정 완~!"; 
			path = "faqBoard"; 
		} else {
			message = "faq 수정 실패!"; 
			path = "editFaq"; 
		}
		
		ra.addFlashAttribute("message", message); 
		
		return "redirect:" + path; 
	}
	
	
	
	/** faq 삭제 
	 * @param qNo
	 * @param loginMember
	 * @param ra
	 * @return
	 */
	@PostMapping("/deleteFaq/{qNo}")
	public String faqDelete(@PathVariable("qNo") int qNo,
							@SessionAttribute("loginMember") Member loginMember,
							RedirectAttributes ra) {
		
		
		int result = service.faqDelete(qNo); 
		
		String message = null; 
		
		if(result >0) {
			message="삭제 완료!"; 
		} else {

			message = "삭제 실패!"; 
		}
		
		ra.addFlashAttribute("message", message); 
		
		return "redirect:/faqBoard/faqBoard"; 
	}
	
	
	 

}
