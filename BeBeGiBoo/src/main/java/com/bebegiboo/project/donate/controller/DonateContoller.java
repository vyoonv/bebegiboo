package com.bebegiboo.project.donate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("donation")
public class DonateContoller {

	@GetMapping("")
	public String Donation() {
		return "donation/donation-main";
	}
	
}
