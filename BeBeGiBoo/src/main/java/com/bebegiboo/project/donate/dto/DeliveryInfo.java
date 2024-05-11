package com.bebegiboo.project.donate.dto;

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
public class DeliveryInfo {

	private String name;
	private String phone;
	private String phone2;
	private String address;
	private String date;
	private String memo;
	private int memberNo;
}
