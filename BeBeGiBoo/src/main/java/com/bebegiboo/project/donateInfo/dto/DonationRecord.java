package com.bebegiboo.project.donateInfo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DonationRecord {
	
	private int recordNo; 
	private String recordDate; 
	private int donatorNo; 
	private int acceptorNo;
	private String acceptorName;
	
	private List<DonationRecord> recordList;

}
