package com.bebegiboo.project.common.filter;

import java.io.IOException;

import com.bebegiboo.project.member.model.dto.Member;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorityFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request; //다운캐스팅
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		
		log.debug("제발 알려줘 : " + member.getAuthority());
		
		if(member != null && member.getAuthority() != 2) {
			log.debug("권한 번호가 2가 아니다");
			
			resp.sendRedirect("/acceptorError");
		}else {
			chain.doFilter(request, response);
		}
	}
}
