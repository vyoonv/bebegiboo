package com.bebegiboo.project.certification.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bebegiboo.project.certification.model.dto.Certification;
import com.bebegiboo.project.certification.model.dto.CertificationImg;


@Mapper
public interface CertificationMapper {

	/** 인증신청 정보 제출 
	 * @param inputCertification
	 * @return
	 */
	int certificationSubmit(Certification inputCertification);

	/** 이미지 업로드 
	 * @param uploadImgs
	 * @return
	 */
	int insertUploadList(List<CertificationImg> uploadImgs);

}
