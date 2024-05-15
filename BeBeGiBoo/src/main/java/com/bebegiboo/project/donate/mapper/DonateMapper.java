package com.bebegiboo.project.donate.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bebegiboo.project.donate.dto.DeliveryInfo;
import com.bebegiboo.project.donateInfo.dto.PaymentInfo;

@Mapper
public interface DonateMapper {

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

	/** daily
	 * @param arr
	 * @return
	 */
	int dailyInsert(Map<String, Object> dailyMap);

	/** cloth
	 * @param arr
	 * @return
	 */
	int clothInsert(Map<String, Object> clothMap);

	/** dish
	 * @param arr
	 * @return
	 */
	int dishInsert(Map<String, Object> dishMap);

	/** electronic
	 * @param arr
	 * @return
	 */
	int electronicInsert(Map<String, Object> electronicMap);

	/** toy
	 * @param arr
	 * @return
	 */
	int toyInsert(Map<String, Object> toyMap);

	/** 기부기록정보 삽입
	 * @param memberNo
	 * @return
	 */
	int recordInfo(int memberNo);

	/** 결제 정보 삽입 
	 * @param memberNo
	 * @return
	 */
	int paymentInfo(PaymentInfo payment);

	//int paymentInfo(PaymentInfo payment);


}
