package com.bebegiboo.project.donateInfo.dto;

import java.util.List;

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
public class DonationInfo {

	
	private List<DonationRecord> recordList;
    private List<DeliveryInfoList> deliveryList;
    private List<DonationProduct> productList;
    private List<PaymentInfo> paymentList;
	
	
}
