package com.dev.ForecastApiTestJarV2.dto;


import org.jetbrains.annotations.NotNull;

import lombok.Data;

@Data
public class MemberLoginRequestDTO {

	@NotNull
	private String walletAddress;
	
	@NotNull
	private String walletId;
	
	@NotNull
	private Long tokenBalance;
	
	@NotNull
	private Long pointBalance;
	private String email;
}
