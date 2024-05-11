package com.bebegiboo.project.certification.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CertificationImg {

	private int cImgNo; 
	private String cImgPath;
	private String cImgOriginalName;
	private String cImgRename; 
	private int cImgOrder; 
	private int cNo; 
	
	private MultipartFile uploadFile; 
	
}
