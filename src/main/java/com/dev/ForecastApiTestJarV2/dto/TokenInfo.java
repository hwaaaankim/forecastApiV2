package com.dev.ForecastApiTestJarV2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenInfo {

	// 인증타입 , Bearer
	private String grantType;
	private String accessToken;
	private String refreshToken;
	private String status;
}
