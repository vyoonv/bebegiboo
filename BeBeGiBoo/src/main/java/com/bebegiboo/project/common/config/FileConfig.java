package com.bebegiboo.project.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bebegiboo.project.common.filter.LoginFilter;

import jakarta.servlet.MultipartConfigElement;

@PropertySource("classpath:/config.properties")
@Configuration
public class FileConfig implements WebMvcConfigurer {
	
		// 파일 업로드 임계값
		@Value("${spring.servlet.multipart.file-size-threshold}")
		private long fileSizeThreshold;
		
		// 요청당 파일 최대 크기
		@Value("${spring.servlet.multipart.max-request-size}")
		private long maxRequestSize;
		
		// 개별 파일당 최대 크기
		@Value("${spring.servlet.multipart.max-file-size}")
		private long maxFileSize;
		
		// 임계값 초과 시 임시 저장 폴더 경로
		@Value("${spring.servlet.multipart.location}")
		private String location;
		
		//-----------------------------------------------------------
		
		// 봉사인증 신청 이미지 
		@Value("${certification.resource-handler}")
		private String certificationResourceHandler; 
		
		@Value("${certification.resource-location}")
		private String certificationResourceLocation;
			
		//-----------------------------------------------------------
		//후기 이미지 요청 주소
		@Value("${my.board.resource-handler}")
		private String reviewResourceHandler;
		//후기 이미지 서버 경로
		@Value("${my.board.resource-location}")
		private String reviewResourceLocation;
		//-------------------------------------------
		
		
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
			// 봉사 인증 신청폼 요청 
			registry
			.addResourceHandler(certificationResourceHandler)
			.addResourceLocations(certificationResourceLocation);
			
			//후기 게시글 요청에 따른 경로 설정
			registry
			.addResourceHandler(reviewResourceHandler)
			.addResourceLocations(reviewResourceLocation);
			
		}
	

		/* MultipartResolver 설정 */
		@Bean
		public MultipartConfigElement configElement() {
			// 파일 업로드를 처리하는데 사용되는 MultipartConfigElement를 구성하고 반환
			// 파일 업로드를 위한 구성 옵션을 설정하는데 사용
			// 업로드 파일의 최대 크기, 메모리에서의 임시 저장 경로 등을 설정 가능
			
			MultipartConfigFactory factory = new MultipartConfigFactory();
			
			factory.setFileSizeThreshold(DataSize.ofBytes(fileSizeThreshold));
			
			factory.setMaxFileSize(DataSize.ofBytes(maxFileSize));
			
			factory.setMaxRequestSize(DataSize.ofBytes(maxRequestSize));
			
			factory.setLocation(location);
			
			return factory.createMultipartConfig();
			
		}
		
		
		
		// MultipartResolver 객체를 Bean으로 추가
		// -> 추가 후 위에서 만든 MultipartConfig 자동으로 이용함
		@Bean
		public MultipartResolver multipartResolver() {
			// MultipartResolver : MultipartFile을 처리해주는 해결사..
			// MultipartResolver는 클라이언트로부터 받은 멀티파트 요청을 처리하고,
			// 이 중에서 업로드된 파일을 추출하여 MultipartFile 객체로 제공하는 역할
			
			StandardServletMultipartResolver multipartResolver
				= new StandardServletMultipartResolver();
			
			return multipartResolver;
		}
		


}
