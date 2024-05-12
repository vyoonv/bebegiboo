package com.bebegiboo.project.common.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bebegiboo.project.common.filter.LoginFilter;


@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<LoginFilter> loginFilter() {
		
		FilterRegistrationBean<LoginFilter> filter = new FilterRegistrationBean<>(); 
		
		filter.setFilter(new LoginFilter());

		String[] filteringURL = {"/mypage/*", "/certification/*", "/donation/**", "/manager/**"};
		
		filter.setUrlPatterns( Arrays.asList(filteringURL));
		
		filter.setName("loginFilter");
		
		filter.setOrder(1);
		
		return filter; 
	}

}
