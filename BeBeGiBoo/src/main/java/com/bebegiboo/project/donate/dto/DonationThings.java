package com.bebegiboo.project.donate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DonationThings {

	private String daily;
	private String cloth;
	private String dish;
	private String electronic;
	private String toy;
}
