package com.bebegiboo.project.member.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bebegiboo.project.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	/** 아이디 중복 체크 
	 * @param memberId
	 * @return result 
	 */
	int checkId(String memberId);

	/** 이메일 중복 체크 
	 * @param email
	 * @return result 
	 */
	int checkEmail(String email);

	/** 회원 로그인
	 * @param memberId
	 * @return
	 */
	Member login(String memberId);

	/** 핸드폰 번호 중복 검사
	 * @param phoneNum
	 * @return result
	 */
	int checkPhone(String phoneNum);

	/**idInquiry)회원 이름 체크
	 * @param memberName
	 * @return
	 */
	int checkName(String memberName);

	/** idInquiry)이메일 + 이름 존재 유무 체크
	 * @param map
	 * @return
	 */
	int checkEmailWithName(Map<String, String> map);

	/** inquiry)이메일에 해당하는 아이디 찾기
	 * @param email
	 * @return
	 */
	String getId(String email);

	/** inquiry)암호화한 비밀번호로 업데이트 하기
	 * @param map
	 * @return
	 */
	int updateNewPw(Map<String, String> map);

	/**inquiry)이메일 + 아이디 존재 유무 체크
	 * @param map
	 * @return
	 */
	int checkEmailWithId(Map<String, String> map);

	/** 회원 가입 
	 * @param inputMember
	 * @param authority
	 * @return
	 */
	int signup(Member inputMember);


}
