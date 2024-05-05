package com.bebegiboo.project.certification.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bebegiboo.project.certification.model.mapper.CertificationMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class CertificationServiceImpl implements CertificationService{
	
	private final CertificationMapper mapper; 

}
