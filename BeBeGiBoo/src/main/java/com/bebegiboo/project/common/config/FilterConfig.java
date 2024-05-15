package com.bebegiboo.project.common.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bebegiboo.project.common.filter.AuthorityFilter;
import com.bebegiboo.project.common.filter.LoginFilter;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<LoginFilter> loginFilter() {
		
		log.info("loginFilter");
		
		FilterRegistrationBean<LoginFilter> filter 
				= new FilterRegistrationBean<>(); 
		
		filter.setFilter(new LoginFilter());
		
		String[] filteringURL = {"/acceptor/*","/myPage/*", "/certification/*", 
				"/donation/*","/member/mypage/*" ,"/manager/*","/faqBoard/editFaq", "/faqBoard/insertFaq",
				"/editReview/*"}; //실제 주소로 적어야함
	
		filter.setUrlPatterns( Arrays.asList(filteringURL));
		
		filter.setName("loginFilter");
		
		filter.setOrder(1);
		
		return filter; 
	}
	
	@Bean
	public FilterRegistrationBean<AuthorityFilter> AuthorityFilter(){
		
		FilterRegistrationBean<AuthorityFilter> filter 
		= new FilterRegistrationBean<>(); 

		filter.setFilter(new AuthorityFilter());

		
		String[] filteringURL = {"/editReview/*", "/acceptor/*"};
		
		log.info("권한 필터 작동됨");
		filter.setUrlPatterns( Arrays.asList(filteringURL));
		
		filter.setName("AuthorityFilter");
		
		filter.setOrder(2);
		
		return filter; 
	}

}
