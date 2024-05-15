package com.bebegiboo.project.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

	@RequestMapping("/")
	public String mainPage() {
		return "common/main";
	}
	
	@GetMapping("loginError")
	public String loginError(RedirectAttributes ra) {
		
		ra.addFlashAttribute("message", "로그인 후 이용해주세요"); 
		
		return "redirect:/"; 
	}
	
	
	@GetMapping("acceptorError")
	public String authorityError(RedirectAttributes ra) {
		ra.addFlashAttribute("message", "권한이 없습니다");
		
		return "redirect:/";
	}
}
