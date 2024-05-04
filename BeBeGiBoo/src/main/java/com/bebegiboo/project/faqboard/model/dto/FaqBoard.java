package com.bebegiboo.project.faqboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FaqBoard {
	
	private int qNo; 
	private String question; 
	private String answer; 
	private int categoryNo; 

	private int memberNo; 
}
