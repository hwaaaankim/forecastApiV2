package com.dev.ForecastApiTestJarV2.dto;

import lombok.Data;

@Data
public class MemberLoginRequestDTO {

	private String walletAddress;
	private String walletId;
	private Long tokenBalance;
	private Long pointBalance;
	private String email;
}
