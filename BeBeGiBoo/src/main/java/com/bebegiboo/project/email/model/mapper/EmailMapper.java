package com.bebegiboo.project.email.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailMapper {

	/** 인증번호 수정 
	 * @param map
	 * @return
	 */
	int updateAuthKey(Map<String, String> map);

	/** 인증번호 삽입 
	 * @param map
	 * @return
	 */
	int insertAuthKey(Map<String, String> map);

	/** 인증번호 확인 
	 * @param map
	 * @return 
	 */
	int checkAuthKey(Map<String, Object> map);

}
