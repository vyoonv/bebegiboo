package com.bebegiboo.project.certification.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bebegiboo.project.certification.model.dto.Certification;
import com.bebegiboo.project.certification.model.dto.CertificationImg;
import com.bebegiboo.project.certification.model.exception.InsertException;
import com.bebegiboo.project.certification.model.mapper.CertificationMapper;
import com.bebegiboo.project.common.util.Utility;

import lombok.RequiredArgsConstructor;

@Transactional(rollbackFor=Exception.class)
@PropertySource("classpath:/config.properties")
@RequiredArgsConstructor
@Service
public class CertificationServiceImpl implements CertificationService{
	
	private final CertificationMapper mapper;
	
	
	@Value("${certification.web-path}")
	private String webPath; 
	
	@Value("${certification.folder-path}")
	private String folderPath; 

	

	/**
	 * 인증 신청 폼 제출 
	 */
	@Override
	public int certificationSubmit(Certification inputCertification, String[] memberAddress,
															List<MultipartFile> images) throws IllegalStateException, IOException {
		
		// 주소 입력된 경우 
		if( !inputCertification.getAddress().equals(",,") ) {
			
			String address = String.join("^^^", memberAddress);
			inputCertification.setAddress(address);
		} else {
			
			inputCertification.setAddress(null);
		}
		
		int result = mapper.certificationSubmit(inputCertification); 
		
		if(result == 0) return 0; 
		
		int cNo = inputCertification.getCNo(); 
	
		List<CertificationImg> uploadImgs = new ArrayList<>(); 
				
		for(int i=0; i<images.size(); i++) {
			
			if( !images.get(i).isEmpty() ) {
				String originalName = images.get(i).getOriginalFilename(); 
				
				
				
				String rename = Utility.fileRename(originalName); 
				
				CertificationImg img = CertificationImg.builder()
									.cImgOriginalName(originalName)
									.cImgRename(rename)
									.cImgPath(webPath)
									.cNo(cNo)
									.cImgOrder(i)
									.uploadFile(images.get(i))
									.build(); 
				
				uploadImgs.add(img);
				
			}
			
		}
		
		result = mapper.insertUploadList(uploadImgs); 
		
		if(result == uploadImgs.size()) {
			for(CertificationImg img : uploadImgs ) {
				img.getUploadFile().transferTo(new File(folderPath+img.getCImgRename()));
			}
		} else {
			
			throw new InsertException("이미지가 정상 삽입되지 않음"); 
		}

		return cNo;
		
		
	} 
	
	
	
	
}
