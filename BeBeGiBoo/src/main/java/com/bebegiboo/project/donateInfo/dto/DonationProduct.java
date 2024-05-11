package com.bebegiboo.project.donateInfo.dto;

import java.util.List;

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
public class DonationProduct {
	
	private int productNo; 
	private String productName; 
	private int memberNo; 
	private int typeNo; 
	private int recordNo; 
	
	private int boxNo; 
	
	private List<DonationProduct> productList;

}
