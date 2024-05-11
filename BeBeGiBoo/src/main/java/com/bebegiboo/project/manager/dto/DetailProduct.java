package com.bebegiboo.project.manager.dto;

import java.util.List;

import com.bebegiboo.project.donateInfo.dto.DeliveryInfoList;
import com.bebegiboo.project.donateInfo.dto.DonationInfo;
import com.bebegiboo.project.donateInfo.dto.DonationProduct;
import com.bebegiboo.project.donateInfo.dto.DonationRecord;
import com.bebegiboo.project.donateInfo.dto.PaymentInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class DetailProduct {
	
	private int deliNo; 
	private String donatorName; 
	private String phone; 
	private String phone2; 
	private String address; 
	private String pickupDate; 
	private String memo; 
	private int acceptorNo;
	private List<String> productName;

}
