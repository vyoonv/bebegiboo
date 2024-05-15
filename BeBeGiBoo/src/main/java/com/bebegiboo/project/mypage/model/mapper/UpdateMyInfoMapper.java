package com.bebegiboo.project.mypage.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UpdateMyInfoMapper {

	/** 비밀번호 대조 
	 * @param memberNo
	 * @return
	 */
	String checkPw(int memberNo);



	/** 내정보 수정 
	 * @param inputMember
	 * @param paramMap
	 * @return
	 */
	int updateInfo(Map<String, Object> paramMap);


	/** 비밀번호 수정하지 않을 때 
	 * @param paramMap
	 * @return
	 */
	int updateNoPw(Map<String, Object> paramMap);



	/** 회원 탈퇴 
	 * @param memberNo
	 * @return
	 */
	int resign(int memberNo);

	

}
