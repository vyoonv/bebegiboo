package com.bebegiboo.project.mypage.model.service;

import java.util.Map;


import com.bebegiboo.project.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

public interface UpdateMyInfoService {

	/** 비밀번호 대조 
	 * @param loginMember
	 * @return
	 */
	int checkPw(Member loginMember);


	/** 내 정보 수정 
	 * @param inputMember
	 * @param address
	 * @param paramMap 
	 * @return
	 */
	int updateInfo(Member inputMember, String[] address, Map<String, Object> paramMap);


	/** 회원 탈퇴 
	 * @param memberPw
	 * @param memberNo
	 * @return
	 */
	int resign(String memberPw, int memberNo);

}
