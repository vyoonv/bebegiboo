package com.bebegiboo.project.donationinfo.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bebegiboo.project.donateInfo.dto.DeliveryInfoList;
import com.bebegiboo.project.donateInfo.dto.DonationProduct;
import com.bebegiboo.project.donateInfo.dto.DonationRecord;
import com.bebegiboo.project.donateInfo.dto.PaymentInfo;



@Mapper
public interface DonationInfoMapper {

	/** 기부내역 레코드 조회 
	 * @param memberNo
	 * @return
	 */
	List<DonationRecord> getDonationRecord(int memberNo);

	/** 배송 정보 조회 
	 * @param memberNo
	 * @return
	 */
	List<DeliveryInfoList> getDeliveryInfo(int memberNo);

	/** 기부 물품 내역 조회 
	 * @param memberNo
	 * @return
	 */
	List<DonationProduct> getProductInfo(int memberNo);

	/** 결제 내역 조회 
	 * @param memberNo
	 * @return
	 */
	List<PaymentInfo> getPaymentInfo(int memberNo);



}
