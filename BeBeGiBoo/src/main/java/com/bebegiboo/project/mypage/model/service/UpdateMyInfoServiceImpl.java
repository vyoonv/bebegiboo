package com.bebegiboo.project.mypage.model.service;

import java.util.Map;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bebegiboo.project.member.model.dto.Member;
import com.bebegiboo.project.mypage.model.mapper.UpdateMyInfoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UpdateMyInfoServiceImpl implements UpdateMyInfoService{
	
	private final UpdateMyInfoMapper mapper; 
	
	private final BCryptPasswordEncoder bcrypt;
	
	/**
	 * 비밀번호 체크 
	 */
	@Override
	public int checkPw(Member loginMember) {
		

		String originPw = mapper.checkPw(loginMember.getMemberNo()); 
		String inputPw = loginMember.getMemberPw();

		if(!bcrypt.matches(inputPw, originPw)) {
			
			return 0; 
		}
		
		return 1;
	}


	@Override
	public int updateInfo(Member inputMember, String[] address, Map<String, Object> paramMap) {
		
		int result = 0; 
		int memberNo = inputMember.getMemberNo(); 
		paramMap.put("memberNo", memberNo); 
		
		String originPw = mapper.checkPw(inputMember.getMemberNo());  	
		String newPw = (String)paramMap.get("newPw"); 
		
		log.info("originPw : " + originPw);	
		log.info("newPw : " + newPw);	
		
		if(newPw.equals("")) {
			
			// 주소 // 
			if(inputMember.getAddress().equals(",,")) {
				paramMap.put("address", null); 
			}
			
			else {
				String newAddress = String.join("^^^", address); 
				paramMap.put("address", newAddress); 
			}
			
			result = mapper.updateNoPw(paramMap); 
			
		}
		else {	
		
			if(!bcrypt.matches((String)paramMap.get("newPw") ,originPw)) {
				
				log.info(originPw);
				log.info((String)paramMap.get("newPw"));
				
				String encPw = bcrypt.encode((String)paramMap.get("newPw"));
				
				log.info("encPw : " + encPw);
				paramMap.put("encPw", encPw);			
				
				
				// 주소 // 
				if(inputMember.getAddress().equals(",,")) {
					paramMap.put("address", null); 
				}
				
				else {
					String newAddress = String.join("^^^", address); 
					paramMap.put("address", newAddress); 
				}
				
				log.info("paramMap : " + paramMap);
				log.info("email ::: " + inputMember.getEmail());
				log.info("phone ::: " + inputMember.getPhone());
				
				result =  mapper.updateInfo(paramMap); 
					
			} 
			
		}	
		
		return result;
			
		}


	/**
	 * 회원 탈퇴 
	 */
	@Override
	public int resign(String memberPw, int memberNo) {
		
		String originPw = mapper.checkPw(memberNo); 
		
		if( !bcrypt.matches(memberPw, originPw)) {
			return 0; 
		}
		
		return mapper.resign(memberNo);
	}
	
}
