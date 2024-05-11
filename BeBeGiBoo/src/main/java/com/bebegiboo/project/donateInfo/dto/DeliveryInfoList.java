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
public class DeliveryInfoList {
	
	private int deliNo; 
	private String donatorName; 
	private String phone; 
	private String phone2; 
	private String address; 
	private String pickupDate; 
	private String memo; 
	private int memberNo;
	
	private List<DeliveryInfoList> deliveryList;

}
