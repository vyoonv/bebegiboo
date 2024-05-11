package com.bebegiboo.project.donate.service;

import org.springframework.stereotype.Service;

import com.bebegiboo.project.donate.dto.DeliveryInfo;
import com.bebegiboo.project.donate.dto.DonationThings;
import com.bebegiboo.project.donateInfo.dto.PaymentInfo;

@Service
public interface DonateService {

	/** loginMemberId로 MemberNo 찾기
	 * @param cookieValue
	 * @return
	 */
	int findNo(String cookieValue);

	/** 배송정보 삽입
	 * @param delivery
	 * @return
	 */
	int deliveryInfo(DeliveryInfo delivery);

	/** 기부물건 삽입
	 * @param things
	 * @return
	 */
	int thingsInfo(DonationThings things, int memberNo);

	/** 기부기록정보 삽입
	 * @return
	 */
	int recordInfo(int memberNo);

	/** 결제 정보 삽입 
	 * @param memberNo
	 * @return
	 */
	//PaymentInfo paymentInfo(int memberNo);

	int paymentInfo(PaymentInfo payment);


}
