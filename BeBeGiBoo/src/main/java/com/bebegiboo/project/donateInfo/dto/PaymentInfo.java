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
public class PaymentInfo {
	
	private int payNo; 
	private int price; 
	private String method; 
	private int memberNo; 
	private int recordNo; 
	
	private int total; 
	private String payment;
	
	private List<PaymentInfo> paymentList;

}
