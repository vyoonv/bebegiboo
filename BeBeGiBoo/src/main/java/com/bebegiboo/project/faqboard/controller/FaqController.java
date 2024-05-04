package com.bebegiboo.project.faqboard.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bebegiboo.project.faqboard.model.dto.FaqBoard;
import com.bebegiboo.project.faqboard.model.service.FaqService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("faqBoard")
@Controller
public class FaqController {
	
	private final FaqService service; 
	
	
	@GetMapping("/faqBoard")
	public String faqMain(Model model, 
			@RequestParam(value="cp", required=false, defaultValue="1") int cp
			) {
		
		
		Map<String, Object> map = service.FaqBoardList(cp); 
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("faqList", map.get("faqList")); 
		
		
		return "faqBoard/faqBoard"; 
		
	}
	
	
	

}
