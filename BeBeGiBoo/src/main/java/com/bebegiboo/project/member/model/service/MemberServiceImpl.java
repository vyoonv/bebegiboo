package com.bebegiboo.project.member.model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.bebegiboo.project.member.model.dto.Member;
import com.bebegiboo.project.member.model.mapper.MemberMapper;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;




/**
 * 
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	

	private final MemberMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;



	/**
	 * 아이디 중복 체크 
	 */

	@Override
	public int checkId(String memberId) {
		
		return mapper.checkId(memberId);



	}



	/**
	 * 이메일 중복 체크 
	 */
	@Override
	public int checkEmail(String email) {
		
		return mapper.checkEmail(email);


	}


	
	
	
	//회원 로그인
	@Override
	public Member login(Member inputMember) {
		log.debug("pw : "+bcrypt.encode("1q2w3e4r"));
		
		Member loginMember = mapper.login(inputMember.getMemberId());
		
		if(loginMember == null) {
			return null;
		}
		if(!bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
			log.debug("pw틀림");
			return null;
		}
		
		
		loginMember.setMemberPw(null);
		return loginMember;
	}




	/**
	 * 회원가입 
	 */
	@Override
	public int signup(Member inputMember, String[] memberAddress, int authority) {
			
		// 주소 입력된 경우 
				if( !inputMember.getAddress().equals(",,") ) {
					String address = String.join("^^^", memberAddress);
					inputMember.setAddress(address);
				} else {
					inputMember.setAddress(null);
				}
				
				// 비밀번호 암호화한 걸 inputMember에 세팅 
				String encPw = bcrypt.encode(inputMember.getMemberPw()); 
				inputMember.setMemberPw(encPw);
				
				inputMember.setAuthority(authority);
				
				return mapper.signup(inputMember);
	}



	//idInquiry)휴대폰 번호 중복 검사
	@Override
	public int checkPhone(String phoneNum) {
		
		return mapper.checkPhone(phoneNum);
	}


	// idInquiry)이름 체크
	@Override
	public int checkName(String memberName) {

		return mapper.checkName(memberName);
	}



	//inquiry) 이메일 존재 유무 확인(이름 +이메일)
	@Override
	public int checkEmailWithName(Map<String, String> map) {
		
		return mapper.checkEmailWithName(map);
	}
	
	//idInquiry)이메일에 해당하는 아이디 찾기
	@Override
	public String getId(String email) {
		
		return mapper.getId(email);
	}
	
	

	private final JavaMailSender mailSender;
	private final TemplateEngine templateEngine;

	//inquiry)이메일로 아이디/비밀번호 보내주기
	@Override
	public int sendEmail(String idOrPw, String email) {
		int result = 1;
		
		try {
			String subject = null;
			String newPw = null;
			String encNewPw = null;
			String memberId = null;
			
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper
				= new MimeMessageHelper(mimeMessage, true, "UTF-8");			
			
			if(idOrPw.equals("id")) {
				subject = "[BEBEGIBO]아이디 찾기: 아이디 정보입니다.";
				memberId = mapper.getId(email);


				helper.setText(loadHtml(memberId,idOrPw), true);
				
			}else {
				subject = "[BEBEGIBOO]비밀번호 찾기: 새로운 비밀번호 정보입니다.";
				newPw = createNewPw();
				log.debug("newPw : "+newPw);
				helper.setText(loadHtml(newPw, idOrPw), true);
				
				//암호화한 비밀번호 업데이트 하기
				encNewPw = bcrypt.encode(newPw);
				
				Map<String, String> map = new HashMap<>();
				map.put("email", email);
				map.put("encNewPw", encNewPw);
				int updateResult = mapper.updateNewPw(map);
				log.debug("updateResult"+updateResult);

			}
			
			helper.setTo(email);
			helper.setSubject(subject);
			helper.addInline("logo", new ClassPathResource("static/images/font-logo.png"));
			
			mailSender.send(mimeMessage);	
			
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return result;
	}

	/** 
	 * @param newIdOrPw
	 * @param idOrPw
	 * @return
	 */
	private String loadHtml(String newIdOrPw, String idOrPw) {
		Context context = new Context();
		
		context.setVariable(idOrPw, newIdOrPw);
		
		return templateEngine.process("member/inquiry/"+idOrPw, context);
	}
	
	
	/** 임시 비밀번호 생성하기(영어 소문자 + 숫자 9자리)
	 * @return
	 */
	public String createNewPw() {
		StringBuilder sb = new StringBuilder();
		Random rd = new Random();
		
		for(int i = 0;i<9;i++) {
			if(rd.nextBoolean()) { //nextBoolean()으로 T/F 랜덤추출->숫자 혹은 영문 랜덤 처리
				sb.append(rd.nextInt(10));
			}else {
				sb.append((char)(rd.nextInt(26)+65));
			}
		}
		
		return sb.toString();
	}



	//아이디와 일치하는 이메일 찾기
	@Override
	public int checkEmailWithId(Map<String, String> map) {
	
		return mapper.checkEmailWithId(map);
	}





}
