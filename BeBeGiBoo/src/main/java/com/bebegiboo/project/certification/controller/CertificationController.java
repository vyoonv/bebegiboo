package com.bebegiboo.project.certification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("certification")
@Controller
public class CertificationController {
	
	@GetMapping("/certification")
	public String certificationPage() {
		
		return "/certification/certification";
		
	}
	
	
}
