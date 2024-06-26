package com.bebegiboo.project.manager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.bebegiboo.project.certification.model.dto.Certification;
import com.bebegiboo.project.donateInfo.dto.DonationProduct;
import com.bebegiboo.project.donateInfo.dto.DonationRecord;
import com.bebegiboo.project.manager.dto.DetailProduct;
import com.bebegiboo.project.member.model.dto.Member;

@Mapper
public interface ManagerMapper {

	/** 회원 목록 조회
	 * @return
	 */
	List<Member> selectMemberList();

	/** 회원 정보 수정
	 * @param member
	 * @return
	 */
	int update(Member member);

	/** 기부물품 목록 조회
	 * @return
	 */
	List<DonationRecord> selectDonationThingsList(int memberNo);


	/** 기부물품 상세내역 조회
	 * @param recordNo
	 * @return
	 */
	DetailProduct selectDonationDetailThingsList(int recordNo);

	/** 기부물품 이름들 조회
	 * @param recordNo
	 * @return
	 */
	List<String> selectProductNames(int recordNo);

	/** 피기부자 번호 조회
	 * @param recordNo
	 * @return
	 */
	int selectAcceptorNo(int recordNo);

	/** 피기부자 목록 조회
	 * @return
	 */
	List<Member> selectAcceptorList(int recordNo);

	/** 피기부자 등록
	 * @param connectObj
	 * @return
	 */
	int connectDonate(Map<String, Object> connectObj);

	/** 봉사 인증 신청서 목록 조회 
	 * @param rowBounds 
	 * @return
	 */
	List<Certification> certificationList(RowBounds rowBounds);

	/** 봉사 인증 신청서 정보 수정 
	 * @param inputInfo
	 * @return
	 */
	int infoUpdate(Certification inputInfo);

	/** 리스트카운트
	 * @return
	 */
	int getListCount();


}
