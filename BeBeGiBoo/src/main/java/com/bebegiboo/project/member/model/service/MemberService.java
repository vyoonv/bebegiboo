package com.bebegiboo.project.member.model.service;

import java.util.Map;

import com.bebegiboo.project.member.model.dto.Member;


public interface MemberService {

	/** 아이디 중복 체크 
	 * @param memberId
	 * @return result 
	 */
	int checkId(String memberId);


	/** 이메일 중복 검사 
	 * @param email
	 * @return
	 */
	int checkEmail(String email);

	/**회원 로그인
	 * @param inputMember
	 * @return
	 */
	Member login(Member inputMember);

	/** 휴대폰 번호 중복 검사
	 * @param phoneNum
	 * @return
	 */
	int checkPhone(String phoneNum);


	/** 회원 이름 체크
	 * @param memberName
	 * @return
	 */
	int checkName(String memberName);


	/** inquiry)이메일 + 이름 존재 유무 체크
	 * @param map
	 * @return
	 */
	int checkEmailWithName(Map<String, String> map);

	/**inquiry)이메일에 해당하는 아이디 찾기
	 * @param string
	 * @return
	 */
	String getId(String string);
 
	/**inquiry)이메일로 아이디 보내주기
	 * @param idOrPw
	 * @param email 
	 * @return
	 */
	int sendEmail(String idOrPw, String email);


	/**inquiry)이메일로 새 비밀번호 보내주기
	 * @param map
	 * @return
	 */
	int checkEmailWithId(Map<String, String> map);


	
	/** 회원가입 
	 * @param inputMember
	 * @param memberAddress
	 * @param authority
	 * @return
	 */
	int signup(Member inputMember, String[] memberAddress, int authority);






}

